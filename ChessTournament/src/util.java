
import java.util.Scanner;

public class util {

    static int chkInteger(String msg, Scanner sc) {
        while (true) { 
            System.out.println(msg);
            if(sc.hasNextInt())
            {
                int num = sc.nextInt();
                return num;
            }
            else
                System.err.println("Enter Only Numbers!!!!");
        }
    }

}
