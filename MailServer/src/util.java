import java.util.Scanner;

public class util {

    public static int chkUser(String msg,Scanner sc) {
        while (true) { 
            System.out.println(msg);  
            if(sc.hasNextInt())
            {
                int num = sc.nextInt();
                return num;
            } 
            else
                System.out.println("Wrong Choice!");
        }
    }

}
