
import java.util.Scanner;

public class util {
    static Scanner sc  = new Scanner(System.in);
    public static int chkInteger(String msg)
    {
        while(true)
        {
            System.out.println(msg);
            if(sc.hasNextInt())
            {
                int num = sc.nextInt();
                return  num;
            }
            else
                System.out.println("Enter Only Number");
        }
    }
}
