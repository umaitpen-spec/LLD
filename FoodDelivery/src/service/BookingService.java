package service;

import enumeration.orderStatus;
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

    public void makeBooking(Customer cust, Restaurent rest, int time) {
        Location custLoc = cust.getLocation();
        Location restLoc = rest.getRestLocation();
        List<DeleiveryExceutive> deList = dBrepo.getDeList();
        LocalTime orderTime  = LocalTime.of(time, 0);
        Booking newBooking = new Booking(cust.getCustomerId(),
                restLoc,
                null,
                orderTime ,
                0,
                orderStatus.BOOKED);
        
        boolean isCombined = false;
        Booking comBooking = null;
        DeleiveryExceutive assginedDE = null;
        for(DeleiveryExceutive de:deList)
        {
            Booking bk = canCombineBooking(de,orderTime,custLoc);
            if(bk != null)
            {
                isCombined = true;
                comBooking = bk;
                assginedDE = de;
                break;
            }
        }

        if(assginedDE == null)
        {
            for(DeleiveryExceutive de:deList)
            {
                if(isAvailable(de,orderTime) && 
                (assginedDE == null || de.getdCharge() <  assginedDE.getdCharge()))
                {
                    assginedDE = de;
                }
            }
        }

        if(assginedDE == null)
        {
            System.out.println("No DeleiveryExceutive Available!");
            return;
        }

        double totCost = newBooking.getTotCost();
        newBooking.setTotCost(isCombined?totCost+5:totCost+50);

        assginedDE.getDeliveryList().add(newBooking);
        cust.getOrderList().add(newBooking);
        assginedDE.setCurrLocation(custLoc);

        if(!isCombined)
            assginedDE.setAllowance(assginedDE.getAllowance()+5);

        System.out.println("Output Booking ID: " + newBooking.getBookingId());
        //System.out.println("Available Executives: " + availableExecutiveNames);
        //System.out.println("Delivery Charge Earned: " + deliveryChargeDetails);
        System.out.print("Allotted Delivery Executive: " + assginedDE.getDeName());
    }

    private Booking canCombineBooking(DeleiveryExceutive de, LocalTime orderTime,Location cLocation) {
        int orderCount = 0;
        Booking latestBooking = null;
        for(Booking bk: de.getDeliveryList())
        {
            if(isSameLocation(de.getCurrLocation(),cLocation) && isWithinTime(bk.getOrderTime(),orderTime,15))
            {
                orderCount++;
                if(latestBooking == null ||
                    bk.getOrderTime().isAfter(orderTime) )
                    latestBooking = bk;
            }                
        }
        if(orderCount > 0 && orderCount < 5)
            return latestBooking;
        return null;
    }

    private boolean isSameLocation(Location currLocation, Location cLocation) {
        return currLocation != null && cLocation != null && currLocation == cLocation;
    }

    private boolean isWithinTime(LocalTime oldTime, LocalTime newTime,int min) {
        long diff = ChronoUnit.MINUTES.between(oldTime, newTime);
        if(diff > 0 && diff < min)
            return true;
        return false;
    }

    private boolean isAvailable(DeleiveryExceutive de, LocalTime orderTime) {
        for(Booking bk:de.getDeliveryList())
        {
            if(isWithinTime(bk.getOrderTime(), orderTime, 30))
                return false;
        }
        return true;
    }
}
