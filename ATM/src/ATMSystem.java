import java.util.Scanner;

public class ATMSystem {
    static void main() {
        int count = 3;
        ATMService atmService = new ATMService();
        Scanner sc = new Scanner(System.in);
        boolean status = false;
        while(count-- > 0) {
            status = atmService.login();
            if(status)
                break;
        }
        if(status) {
            while (true) {
                displayOptions();
                int choice = sc.nextInt();
                switch(choice)
                {
                    case 1:
                        atmService.checkBalance();
                        break;
                    case 2:
                        atmService.withDraw();
                        break;
                    case 3:
                        atmService.deposit();
                        break;
                    case 4:
                        System.exit(0);
                }
            }
        }
        else
        {
            System.out.println("You exceeded the try limit. Your account is temporarily blocked.");
            System.exit(0);
        }
    }

    static void displayOptions()
    {
        System.out.println("Select one of the below options");
        System.out.println("1.Check balance");
        System.out.println("2.WithDraw");
        System.out.println("3.Deposit");
        System.out.println("4.Exit");
    }
}
