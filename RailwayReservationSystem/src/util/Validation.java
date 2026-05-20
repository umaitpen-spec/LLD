package util;

import java.util.Scanner;

public class Validation {

    public static int chkInteger(String msg,Scanner sc)
    {
        while (true) { 
            System.out.println(msg);
            if(sc.hasNextInt())
            {
                return sc.nextInt();
            }
            else
            {
                System.out.println("Enter only Digits");
                sc.nextLine();
            }
        }
    }
}
