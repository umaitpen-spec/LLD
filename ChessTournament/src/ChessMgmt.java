
import java.util.Scanner;

public class ChessMgmt {

    Scanner sc = new Scanner(System.in);
    ChessVw chessvw = new ChessVw(sc);
   
    public void start() {
        System.out.println("Enter the option");
        System.out.println("1.Create Player");
        System.out.println("2.Register Player to Tournament");
        System.out.println("3.Create Rounds");
        System.out.println("4.Record Match Result");
        System.out.println("5.Display All Matches Player");
        System.out.println("6.Declare Winner");
        int choice = util.chkInteger("",sc);
        switch (choice) {
            case 1:
                chessvw.createPlayer();
                break;
            case 2:
                chessvw.RegisterPlayer();
                break;
            case 3:
                chessvw.createRounds();
                break;
            case 4:
                chessvw.recordResult();
                break;
            case 5:
                chessvw.displayAllMatchesByPlayer();
                break;
            case 6:
                chessvw.declareWinner();
                break;
            case 0:
                System.out.println("Bye");
                System.exit(0);
            default:
                System.out.println("Wrong Choice!!!");
        }
    }
}