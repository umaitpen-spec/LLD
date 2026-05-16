package service;

import enumeration.orderStatus;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import model.Booking;
import model.Customer;
import model.DeleiveryExceutive;
import model.Location;
import model.Restaurent;
import repo.DBrepo;

public class BookingService {

    DBrepo dBrepo;
    public BookingService(DBrepo dBrepo) {
        this.dBrepo = dBrepo;
    }

    public void makeBooking(Customer cust, Restaurent rest, Location loc,int time) {
        Location cusLocation = loc;
        Location restLocation = rest.getRestLocation();
        List<DeleiveryExceutive> deList = dBrepo.getDeList();
        LocalTime orderTime = LocalTime.of(time,0);
        List<DeleiveryExceutive> dbList =  dBrepo.getDeList();
        DeleiveryExceutive assignedDe = null;
        Booking newBooking = new Booking(cust.getCustomerId(),
        cusLocation,LocalDate.now(),
        orderTime,
        0,
        orderStatus.BOOKED);

        Booking combinedBooking = null;
        boolean isCombinedBooking = false;
        for(DeleiveryExceutive de:dbList)
        {
            combinedBooking = canMergeBooking(cusLocation,orderTime,de);
            if(combinedBooking != null)
            {
                isCombinedBooking = true;
                assignedDe = de;
                break;
            }
        }
        if(assignedDe == null)
        {
            for(DeleiveryExceutive de:dbList)
            {
                if(isAvailable(de,orderTime) && 
                (assignedDe == null || de.getdCharge() < assignedDe.getdCharge()))
                    assignedDe = de;
            }
        }

        if(assignedDe == null)
        {
            System.out.println("No Deleivery Executives Available");
            return;
        }
        assignedDe.getDeliveryList().add(newBooking);
        cust.getOrderList().add(newBooking);
        assignedDe.setCurrLocation(cusLocation);

        if(isCombinedBooking)
        {
            assignedDe.setdCharge(assignedDe.getdCharge()+5);
        }
        else
        {
            assignedDe.setdCharge(assignedDe.getdCharge()+50);
            assignedDe.setAllowance(assignedDe.getAllowance()+10);
        }

        newBooking.setTotCost(isCombinedBooking?5:50);

        String availableExecutive = getAvailableExcecutives(deList,cusLocation,orderTime);
        String delChargeEarned = getdelCahrgeEarned(deList);

        System.out.println("Booking ID:"+newBooking.getBookingId());
        System.out.println("Available Executives:" + availableExecutive);
        System.out.println("Deleivery Charges Earned:" + delChargeEarned);
        System.out.println("Alloted Delivery Excecutive:"+ assignedDe.getDeName());
    }

    private Booking canMergeBooking(Location cusLocation, LocalTime orderTime,DeleiveryExceutive de) {
       
        int totOrderCount = 0;
        Booking combinedBooking = null;
       
        List<Booking> bookingList = de.getDeliveryList();
        for(Booking bk:bookingList)
        {
            if(isSameLocation(bk.getDestinationPt(),cusLocation) && 
                withInminutes(bk.getOrderTime(), orderTime, 15))
            {
                totOrderCount++;
                if(combinedBooking == null || 
                    bk.getOrderTime().isAfter(combinedBooking.getOrderTime())
                )
                {
                    combinedBooking = bk;
                }

            }
        }
            if(totOrderCount > 0 && totOrderCount < 5)
                return combinedBooking;        
        
            return null;
    }

    private boolean withInminutes(LocalTime oldTime,LocalTime newTime,int min)
    {
        long diff = ChronoUnit.MINUTES.between(oldTime, newTime);
        if(diff > 0 && diff < min)
            return true;
        return false;
    }

    private boolean isSameLocation(Location sLoc,Location dLoc)
    {
        return (sLoc != null && dLoc != null && sLoc == dLoc);
    }
    
    private boolean isAvailable(DeleiveryExceutive de,LocalTime time)
    {
        for(Booking bk:de.getDeliveryList())
        {
            if(isTripInProgress(bk.getOrderTime(),time))
                return false;
        }
        return true;
    }

    private boolean isTripInProgress(LocalTime oldTime, LocalTime newTime) {
        long diff = ChronoUnit.MINUTES.between(oldTime, newTime);
        if(diff >0 && diff < 30)
            return true;
        return false;
    }

    private String getAvailableExcecutives(List<DeleiveryExceutive> deList, 
        Location destination, LocalTime orderTime)
    {
        String names = "";
        for(DeleiveryExceutive de:deList)
        {
            if(isAvailable(de, orderTime))
                names += names.isEmpty()?de.getDeName():","+de.getDeName();

        }
        return names.isEmpty()?"None":names;
    }

    private String getdelCahrgeEarned(List<DeleiveryExceutive> deList) {
        String names = "";
        for(DeleiveryExceutive de:deList)
        {
            names += names.isEmpty()?"":",";
            names += de.getDeName()+","+de.getdCharge();
        }
        return names.isEmpty()?"None":names;
    }
}
