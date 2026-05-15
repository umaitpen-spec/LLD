import model.Bike;
import model.Car;
import model.Vechile;
import model.VechileType;
import service.ParkingService;

import java.util.Scanner;

public class ParkingLot {
    static void main() {
        Scanner sc = new Scanner(System.in);
        ParkingService pservice = new ParkingService(3,3);
        Vechile v;
        while(true)
        {
            displayInput();
            int choice = sc.nextInt();
            switch (choice)
            {
                case 1:
                    pservice.allotVechile(VechileType.CAR);
                    break;
                case 2:
                    pservice.allotVechile(VechileType.BIKE);
                    break;
                case 3:
                    pservice.displayParkingDetails();
                    break;
                case 4:
                    pservice.displayTicketDetails();
                    break;
                case 5:
                    System.out.println("Enter Ticket no:");
                    int ticketId = sc.nextInt();
                    pservice.makePayment(ticketId);
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Invalid vechile Type");
                    System.exit(0);

            }
        }
    }
    public static void displayInput()
    {
        System.out.println("Enter the Vechile Type");
        System.out.println("1.Car");
        System.out.println("2.Bike");
        System.out.println("3.Display Slot Details");
        System.out.println("4.Display Ticket Details");
        System.out.println("5.Make Payment");
        System.out.println("6.Exit");
    }

}
