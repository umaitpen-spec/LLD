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
            return "Ticket Not Valid";
        for(Passenger passenger:ticket.getPassList())
        {
            Seat cancelledSeat = passenger.getSeat();
            if(cancelledSeat == null)
                continue;
            if(null == cancelledSeat.getSeatType()) {
                Seat freedRACSeat = moveRACToCF(cancelledSeat);
                if(freedRACSeat != null)
                    moveWTToRAC(freedRACSeat,passenger);
            } else switch (cancelledSeat.getSeatType()) {
                case WT -> cancelledSeat.setBooked(false);
                case RAC -> moveWTToRAC(cancelledSeat,passenger);
                default -> {
                    Seat freedRACSeat = moveRACToCF(cancelledSeat);
                    if(freedRACSeat != null)
                        moveWTToRAC(freedRACSeat,passenger);
                }
            }
        }
        return "Ticket cancelled!!";
    }
    

    private void moveWTToRAC(Seat racSeat,Passenger passenger) {
        //remove RAC
        racSeat.getPassenger().remove(passenger);
        if(racSeat.getPassenger() == null)
            racSeat.setBooked(false);

        //get WT  free seat
        Seat wtSeat = getFirstBookedSeat(dbRepo.getAllWTSeat());

        if(wtSeat == null)
            return;

        //move wt passenger to RAC
        Passenger pass = wtSeat.getPassenger().remove(0);
        Seat rac = getFirstFreeSeat(dbRepo.getAllRACSeat());
        if(rac.getPassenger() == null)
            rac.setPassenger(new ArrayList<>());
        rac.getPassenger().add(pass);
        
    }

    private Seat moveRACToCF(Seat CFSeat) {
        //Remove CF
        CFSeat.getPassenger().remove(0);

        Seat seat = getFirstBookedSeat(dbRepo.getAllRACSeat());
        if(seat == null)
            return null;
        Passenger passrac = seat.getPassenger().remove(0);

        Seat setseat = getFirstFreeSeat(dbRepo.getAllCBSeat());

        if(setseat.getPassenger() == null)
            setseat.setPassenger(new ArrayList<>());
        seat.getPassenger().add(passrac);

        return CFSeat;
    }

    private Seat getFirstBookedSeat(List<Seat> allSeat) {
        for(Seat seat:allSeat)
            if(seat.isBooked())
                return seat;
        return null;
    }

    private Seat getFirstFreeSeat(List<Seat> allSeat) {
        for(Seat seat:allSeat)
            if(!seat.isBooked())
                return seat;
        return null;
    }
}
