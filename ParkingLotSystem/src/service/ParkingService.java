package service;

import model.*;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingService {
    List<ParkingSlot> plist = new ArrayList<>();
    List<Vechile> vlist = new ArrayList<>();
    List<Ticket> tlist = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    private int carSlots;
    private int bikeSlots;

    public ParkingService(int carSlots,int bikeSlots)
    {
        this.carSlots = carSlots;
        this.bikeSlots = bikeSlots;
        for(int i=1;i<=carSlots;i++)
            plist.add(new ParkingSlot(i,1, VechileType.CAR,true));
        for(int i=1;i<=carSlots;i++)
            plist.add(new ParkingSlot(i,2, VechileType.CAR,true));
        for(int i=1;i<=bikeSlots;i++)
            plist.add(new ParkingSlot(i,1, VechileType.BIKE,true));
        for(int i=1;i<=bikeSlots;i++)
            plist.add(new ParkingSlot(i,2, VechileType.BIKE,true));
    }

    public void allotVechile(VechileType vechileType) {
        ParkingSlot slot = null;
        Vechile vechile = null;
        Ticket ticket = null;
        if(vechileType == VechileType.CAR)
        {
            System.out.println("Enter the Car No");
            String vNo = sc.nextLine();
            vechile = new Car(vNo) ;
            for(int i=1;i<=carSlots;i++)
            {
                int finalI = i;
                slot = plist.stream()
                        .filter(a->a.getType() == VechileType.CAR
                        && a.getSlotId() == finalI).findFirst().orElse(null);

                if(slot != null && slot.isAvailable())
                {
                    slot.setAvailable(false);
                    slot.setVechile(new Car(vNo));
                    break;
                }
            }

        }
        else if (vechileType == VechileType.BIKE)
        {
            System.out.println("Enter the Bike No");
            String vNo = sc.nextLine();

            for(int i=1;i<=bikeSlots;i++)
            {
                int finalI = i;
                slot = plist.stream()
                        .filter(a->a.getType() == VechileType.BIKE
                                && a.getSlotId() == finalI).findFirst().orElse(null);

                if(slot != null && slot.isAvailable())
                {
                    slot.setAvailable(false);
                    slot.setVechile(new Bike(vNo));
                    break;
                }
            }
        }
        if(slot != null)
        {
            ticket = new Ticket(tlist.size(),vechile,slot);
            tlist.add(ticket);
        }
    }

    public void displayParkingDetails() {
        System.out.println("Car Parking Slots Details");
        List<ParkingSlot> cList = plist.stream()
                .filter(a->a.getType() == VechileType.CAR)
                .toList();
        System.out.printf("%-10s %-10s %-10s %-10s%n","SlotNo","FloorNo","Available","CarNo");

        for(ParkingSlot slot:cList)
        {
           System.out.printf("%-10s %-10s %-10s %-10s%n",slot.getSlotId(),slot.getFloor(),
                    ((slot.isAvailable())?"Yes":"No"),(slot.getVechile() == null?"-":slot.getVechile().getVno()));

//            System.out.print(slot.getSlotId()+"  ");
//            System.out.print("Floor:"+slot.getFloor()+"  ");
//            System.out.print("Available:"+((slot.isAvailable())?"Yes":"No")+"  ");
//            System.out.println("CarNo:"+(slot.getVechile() == null?"-":slot.getVechile().getVno()+"  "));
        }
        List<ParkingSlot> bList = plist.stream()
                .filter(a->a.getType() == VechileType.BIKE)
                .toList();

        System.out.println("Bike Parking Slots Details");
        for(ParkingSlot slot:bList)
        {
            System.out.printf("%-10s %-10s %-10s %-10s%n",slot.getSlotId(),slot.getFloor(),
                    ((slot.isAvailable())?"Yes":"No"),(slot.getVechile() == null?"-":slot.getVechile().getVno()));
        }
    }

    public void displayTicketDetails() {
        System.out.println("Ticket Details:");
        System.out.printf("%-10s %-10s %-10s %-10s %-20s %-20s %-10s%n",
                "TicketNo","VechileNo","Slot","FloorNo","INDate&Time",
                "OUTDate&Time","Amount");

        for(Ticket ticket: tlist)
        {
            System.out.println(ticket);
        }
    }

    public void makePayment(int ticketId) {
        Ticket ticket = tlist.stream()
                        .filter(a->a.getId() == ticketId)
                                .findFirst().orElse(null);
        if(ticket != null) {
            ticket.setOutTime(ticket.getInTime().plusHours(3));
            ticket.setTotAmount(ChronoUnit.HOURS.between(ticket.getInTime(), ticket.getOutTime()) * 100);
            System.out.println("Total Amount to be paid:" + ticket.getTotAmount()
                    );
        }
    }
}
