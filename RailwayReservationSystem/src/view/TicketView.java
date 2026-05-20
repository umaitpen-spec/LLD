package view;

import enumeration.BerthType;
import enumeration.Gender;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Passenger;
import service.TicketService;
import util.Validation;

public class TicketView {

    private TicketService ticketService;
    Scanner sc;

    public TicketView(TicketService tkSrc, Scanner sc) {
        this.ticketService = tkSrc;
        this.sc = sc;
    }

    public void makeBooking() {
        String msg = "Enter the number of passenger u want to book";
        int n = Validation.chkInteger(msg, sc);
        if(n > 5)
        {
            System.out.println("U can book ticket for (max)5 member");
            return;
        }
        List<Passenger> passList = new ArrayList<>();
        int childCount = 0;
        for(int i=0;i<n;i++)
        {
            System.out.print("Enter the Passenger Name:");
            String name = sc.next();
            msg = "Enter the Passenger Age:";
            int age = Validation.chkInteger(msg, sc);
            if(age < 5)
                childCount++;
            msg = "Enter the Passenger Gender(1.Male/2.Female):";
            int gender = Validation.chkInteger(msg, sc);
            msg = "Enter the Passenger Berth Preference:1.Lower/2.Middle/3.Upper/0.No Preference";
            int bf = Validation.chkInteger(msg, sc);
            sc.nextLine();
            BerthType berthType = BerthType.fromValue(bf);
            Gender genderType = Gender.fromValue(gender);
            Passenger passenger = new Passenger(name, 
                age,
                genderType,
                berthType,
                null
            );
            passList.add(passenger);
        }
        msg = ticketService.makeBooking(passList,childCount);
        System.out.println(msg);
        if(!msg.equals("No Seats Available!!"))
            System.out.println("Total Tickets Booked : "+ ticketService.getTotalSeatsFilled());
    }

    public void  cancelBooking() {
        String msg = "Enter the ticket Number u want to cancel:";
        int tkId = Validation.chkInteger(msg, sc);
        msg = ticketService.cancelBooking(tkId);
        System.out.println(msg);
    }

    public void getTotTicketCount() {
         System.out.println("Total Tickets Booked : "+ ticketService.getTotalSeatsFilled());
    }

}
