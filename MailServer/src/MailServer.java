
import java.util.Scanner;

public class MailServer {
    Scanner sc = new Scanner(System.in);
    MailView mailVw = new MailView(sc);
    DBrepo dBrepo = new DBrepo();
    public void start()
    {
        while (true) { 
            System.out.println("Enter the option of ur choice");
            System.out.println("1.Register User");
            System.out.println("2.Login User");
            System.out.println("0.Exit");
            int choice  = util.chkUser("",sc);
            switch (choice) {
                case 1:
                    mailVw.registerUser();
                    break;
                case 2:
                    mailVw.loginUser();
                    break;
                case 0:
                    System.exit(0);
                default:
                    throw new AssertionError();
            }
        }
    }
}
