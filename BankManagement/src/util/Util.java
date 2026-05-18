package util;

import java.util.Scanner;

public class Util
{
    public static boolean chkStringNull(String str)
    {
         return str == null || str.isEmpty();
    }

    public static boolean chlAge18(int age)
    {
        return age >= 18;
    }

    public static boolean chkVAlidMobileNumber(String mobnumber)
    {
        return mobnumber != null && 
            mobnumber.matches("\\d+") &&
            mobnumber.length() == 10;
    }

    public static boolean validateEmail(String email)
    {
        String regex = "^[A-Za-z0-9_.-]+@[A-Za-z0-9_.-]+$";
        return email != null && email.matches(regex);
    }

    public static int chkInteger(String msg,Scanner sc)
    {
        int value = 0;
        while (true) { 
            System.out.print(msg);
            if(sc.hasNextInt())
            {
                value = sc.nextInt();
                return value;
            }
            else
            {
                System.out.println("Enter only numbers");
                sc.next();
            }
        }        
    }
    public static double chkDouble(String msg,Scanner sc)
    {
        double value = 0;
        while (true) { 
            System.out.print(msg);
            if(sc.hasNextDouble())
            {
                value = sc.nextDouble();
                return value;
            }
            else
            {
                System.out.println("Enter only numbers");
                sc.next();
            }
        }        
    }
}