import java.util.Scanner;
import repo.DbRepo;
import service.SeatService;
import service.TicketService;
import view.SeatView;
import view.TicketView;

public class Railway {
    public void start()
    {
        Scanner sc = new Scanner(System.in);
        
        DbRepo dbRepo = new DbRepo(2,2,10);

        SeatService seatService = new SeatService(dbRepo);
        TicketService tkSrc = new TicketService(dbRepo,seatService);

        TicketView tkVw = new TicketView(tkSrc,sc);
        SeatView seatVw = new SeatView(seatService,sc);

        while(true)
        {
            System.out.println("Selct the option u want");
            System.out.println("1.Print all Available Ticket");
            System.err.println("2.Print all Booked Ticket");
            System.err.println("3.Make Booking");
            System.err.println("4.Cancel Booking");
            System.err.println("5.Print Number Of Booked Tickets");
           
            System.err.println("0.Exit");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    seatVw.printAllAvailableTicket();
                    break;
                case 2:
                    seatVw.printAllBookeddTicket();
                    break;
                case 3:
                    tkVw.makeBooking();
                    break;
                case 4:
                    tkVw.cancelBooking();
                    break;
                case 5:
                    tkVw.getTotTicketCount();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Wrong Choice!!!");
            }
        }
    }
}
