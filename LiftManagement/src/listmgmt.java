
import DBRepo.DBRepo;
import java.util.Scanner;
import service.FloorService;
import service.LiftService;
import view.LiftView;

public class listmgmt {
    public void start()
    {
        Scanner sc = new Scanner(System.in);
        DBRepo dbRepo = new DBRepo();
        FloorService flSrc = new FloorService(10,dbRepo);
        LiftService lSrc = new LiftService(5,dbRepo, flSrc);        
        
        LiftView lvw = new LiftView(sc, lSrc);        
       
        while (true) { 
            System.out.println("Enter ur option:");
            System.out.println("1.Display Lift Position");
            System.out.println("2.Assign Lift");
            System.out.println("3.Restrict Lift");
            System.out.println("4.Assign Lift By Least Number of Stops");
            System.out.println("5.Assign Capacity to Lifts");
            System.out.println("6.Add Lift Under Maintenance");
            System.out.println("0.Exit");
            int choice = sc.nextInt();
            switch (choice) {
            case 1:
                lSrc.displayLiftDetails();
                break;
            case 2:
                lvw.assignLift();
                break;
            case 3:
                lvw.restrictLift();
                break;
            case 4:
                lvw.assignLiftByLeastStops();
                break;
            case 5:
                lvw.addCapacityToLifts();
                break;
            case 6:
                lvw.addLiftUnderMaintenance();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Wrong Choice!");
        }
        }        
    }
}
