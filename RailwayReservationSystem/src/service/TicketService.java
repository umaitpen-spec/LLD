package service;

import enumeration.BerthType;
import enumeration.Gender;
import enumeration.SeatType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Passenger;
import model.Seat;
import model.Ticket;
import repo.DbRepo;

public class TicketService {

    DbRepo dbRepo;
    SeatService seatSrc;
    public TicketService(DbRepo dbRepo,SeatService seatSrc) {
        this.dbRepo = dbRepo;
        this.seatSrc = seatSrc;
    }

    public String makeBooking(List<Passenger> passList,int childCount) {
        int cap = passList.size();
        List<Seat> availCBList = seatSrc.getAllAvailableCBSeat();
        List<Seat> availRACList = seatSrc.getAllAvailableRACSeat();
        List<Seat> availWTList = seatSrc.getAllAvailableWTSeat();        
        if(availCBList.size() >= cap)
        {
            return makeCBBooking(passList,availCBList,childCount);
        }
        else if(availRACList.size() >= cap)
        {
            return makeRACBooking(passList,availRACList);
        }
        else if(availWTList.size() >= cap)
        {
            return makeWTBooking(passList,availWTList);
        }
        else
            return "No Seats Available!!";        
    }

    private String makeCBBooking(List<Passenger> passList, List<Seat> availCBList,int childCount) {
        List<Seat> lowerBerthCB = availCBList.stream()
                .filter(a->a.getSeatType() == SeatType.CFLOWER)
                .collect(Collectors.toList());
        List<Seat> middleBerthCB = availCBList.stream()
                .filter(a->a.getSeatType() == SeatType.CFMIDDLE)
                .collect(Collectors.toList());
        List<Seat> upperBerthCB = availCBList.stream()
                .filter(a->a.getSeatType() == SeatType.CFUPPER)
                .collect(Collectors.toList());        

        List<Seat> upperBerthSide = availCBList.stream()
                .filter(a->a.getSeatType() == SeatType.SIDEUPPER)
                .collect(Collectors.toList());
        upperBerthCB.addAll(upperBerthSide);
        for(Passenger passenger:passList)
        {
            if(passenger.getAge() < 5)
            {
                continue;
            }
            if(passenger.getBerthPreference() == BerthType.LOWER || 
            (childCount > 0  && passenger.getGender() == Gender.FEMALE) ||
            passenger.getAge() >= 60 ||
            (upperBerthCB.size() <= 0 && middleBerthCB.size() <= 0)
            )
            {
                Seat bseat = null;
                for(Seat seat:lowerBerthCB)
                {
                    seat.setBooked(true);
                    List<Passenger> pList = new ArrayList<>();
                    pList.add(passenger);
                    seat.setPassenger(pList);
                    passenger.setSeat(seat);
                    if(childCount > 0 && passenger.getGender() == Gender.FEMALE)
                        childCount--;
                    bseat = seat;
                    break;
                }
                if(bseat != null)
                    lowerBerthCB.remove(bseat);
            }
            else if(passenger.getBerthPreference() == BerthType.MIDDLE || upperBerthCB.size() <= 0)
            {
                Seat bseat = null;
                for(Seat seat:middleBerthCB)
                {
                    seat.setBooked(true);
                    List<Passenger> pList = new ArrayList<>();
                    pList.add(passenger);
                    seat.setPassenger(pList);
                    passenger.setSeat(seat);
                    bseat = seat;
                    break;
                }
                if(bseat != null)
                    middleBerthCB.remove(bseat);
            }
            else if(passenger.getBerthPreference() == BerthType.UPPER 
             || passenger.getBerthPreference() == BerthType.N0PREFERENCE)
            {
                Seat bseat = null;
                for(Seat seat:upperBerthCB)
                {
                    seat.setBooked(true);
                    List<Passenger> pList = new ArrayList<>();
                    pList.add(passenger);
                    seat.setPassenger(pList);
                    passenger.setSeat(seat);
                    bseat = seat;
                    break;
                }
                if(bseat != null)
                    upperBerthCB.remove(bseat);
            }
        }
        Ticket ticket = new Ticket(passList);
        dbRepo.getTkList().put(ticket.getTicketID(),ticket);
        displayTickets(ticket);
        return "Ticket Booked Successfully!! All Confirmed";
    }

    private String makeRACBooking(List<Passenger> passList, List<Seat> availRACList) {
        
        for(Passenger passenger:passList)
        {              
            Seat bseat = null;
            for(Seat seat:availRACList)
            {
                if(seat.getPassenger() == null)
                    seat.setPassenger(new ArrayList<>());
                seat.getPassenger().add(passenger);
                if(seat.getPassenger().size() == 2)
                    seat.setBooked(true);
                passenger.setSeat(seat);
                bseat = seat;
                break;
            }   
            if(bseat != null && bseat.isBooked())
                    availRACList.remove(bseat);         
        }
        Ticket ticket = new Ticket(passList);
        dbRepo.getTkList().put(ticket.getTicketID(),ticket);
        displayTickets(ticket);
        return "RAC Ticket Booked Successfully!! ";
    }

    private String makeWTBooking(List<Passenger> passList, List<Seat> availWTList) {
        
        for(Passenger passenger:passList)
        {
            Seat bseat = null;
            for(Seat seat:availWTList)
            {
                seat.setBooked(true);
                List<Passenger> pList = new ArrayList<>();
                pList.add(passenger);
                passenger.setSeat(seat);
                seat.setPassenger(pList);
                bseat = seat;
                break;
            }
            if(bseat != null)
                    availWTList.remove(bseat);  
        }
        Ticket ticket = new Ticket(passList);
        dbRepo.getTkList().put(ticket.getTicketID(),ticket);
        displayTickets(ticket);
        return "Wating List is given";
    }

    private void displayTickets(Ticket ticket) {
        System.out.println("Booked Tickets. Tickets ID:"+ticket.getTicketID());

        for(Passenger passenger:ticket.getPassList())
            System.out.println(passenger);
    }

    public int getTotalSeatsFilled() {
        return dbRepo.getTkList().size();
    }

    public String cancelBooking(int tkId) {
        Ticket ticket = dbRepo.getTicketById(tkId);
        if(ticket == null)
            return "Ticket not found";

        for(Passenger passenger:ticket.getPassList())
        {
            Seat cancelledSeat = passenger.getSeat();
            if(cancelledSeat == null)
                continue;

            SeatType cancelledSeatType = cancelledSeat.getSeatType();
            removePassengerFromSeat(passenger, cancelledSeat);
            passenger.setSeat(null);

            if(cancelledSeatType == SeatType.RAC)
            {
                moveWTToRAC(cancelledSeat);
            }
            else if(cancelledSeatType == SeatType.WT)
            {
                cancelledSeat.setBooked(false);
            }
            else
            {
                Seat freedRACSeat = moveRACToConfirmed(cancelledSeat);
                if(freedRACSeat != null)
                    moveWTToRAC(freedRACSeat);
            }
        }
        dbRepo.removeTicket(ticket);
        return "Ticket Cancelled";
    }

    private Seat moveRACToConfirmed(Seat confirmedSeat) {
        Seat racSeat = getFirstSeatWithPassenger(dbRepo.getAllRACSeat());
        if(racSeat == null)
            return null;

        Passenger racPassenger = racSeat.getPassenger().remove(0);
        assignPassengerToConfirmedSeat(racPassenger, confirmedSeat);
        updateRACSeatStatus(racSeat);
        return racSeat;
    }

    private void moveWTToRAC(Seat racSeat) {
        Seat wtSeat = getFirstSeatWithPassenger(dbRepo.getAllWTSeat());
        if(wtSeat == null)
            return;

        Passenger wtPassenger = wtSeat.getPassenger().remove(0);
        if(racSeat.getPassenger() == null)
            racSeat.setPassenger(new ArrayList<>());
        racSeat.getPassenger().add(wtPassenger);
        wtPassenger.setSeat(racSeat);
        updateRACSeatStatus(racSeat);
        clearSeatIfEmpty(wtSeat);
    }

    private Seat getFirstSeatWithPassenger(List<Seat> seats) {
        for(Seat seat:seats)
        {
            if(seat.getPassenger() != null && !seat.getPassenger().isEmpty())
                return seat;
        }
        return null;
    }

    private void assignPassengerToConfirmedSeat(Passenger passenger, Seat seat) {
        List<Passenger> pList = new ArrayList<>();
        pList.add(passenger);
        seat.setPassenger(pList);
        seat.setBooked(true);
        passenger.setSeat(seat);
    }

    private void removePassengerFromSeat(Passenger passenger, Seat seat) {
        if(seat.getPassenger() != null)
        {
            seat.getPassenger().remove(passenger);
            clearSeatIfEmpty(seat);
        }
        else
        {
            seat.setBooked(false);
        }
    }

    private void clearSeatIfEmpty(Seat seat) {
        if(seat.getPassenger() == null || seat.getPassenger().isEmpty())
        {
            seat.setPassenger(null);
            seat.setBooked(false);
        }
    }

    private void updateRACSeatStatus(Seat seat) {
        if(seat.getPassenger() == null || seat.getPassenger().isEmpty())
        {
            seat.setPassenger(null);
            seat.setBooked(false);
            return;
        }
        seat.setBooked(seat.getPassenger().size() == 2);
    }
}
