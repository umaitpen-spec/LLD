package Util;

import java.util.Scanner;

public class Validation {
    public static String validateEmail(Scanner sc)
    {
        while(true)
        {
            System.out.print("Enter the Email:");
            String email = sc.next();
            String regex = "^[0-9a-zA-Z]+@[0-9a-zA-Z]+[.][0-9a-zA-z]+$";
            if(email.matches(regex))
                return email;
            System.out.println("Enter Valid Email!!!");
        }        
    }

    public static String validatePassword(Scanner sc) {
        while(true)
        {
            System.out.print("Enter the Password:");
            String password = sc.next();
            System.out.print("Retype the Password:");
            String repassword = sc.next();
            if(password.equals(repassword))
            {
                return password;
            }
            else
                System.out.println("Password doesn't match!");
        }   
   }

    public static String validateName(Scanner sc) {
        while(true)
        {
            System.out.print("Enter the Name:");
            String name = sc.next();
            if(name != null)
                return name;
            System.out.println("Enter Valid Name!!!");
        } 
    }

    public static long validateMobile(Scanner sc) {
        while(true)
        {
            System.out.print("Enter the MobileNumber:");
            String mno = sc.next();
            String regex = "\\d{10}";
            if(mno.matches(regex))
                return Long.parseLong(mno);
            System.out.println("Enter Valid MobileNumber!!!");
        } 
    }
}
