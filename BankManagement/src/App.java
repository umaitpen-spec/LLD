
import java.util.Scanner;
import repo.AccountRepo;
import repo.CustomerRepo;
import service.AccountService;
import service.CustomerService;
import view.AccountView;
import view.CustomerView;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Bank Management System");
        Scanner sc = new Scanner(System.in);
        
        CustomerRepo custRepo = new CustomerRepo();
        CustomerService custSrc = new CustomerService(custRepo);
        CustomerView custVw = new CustomerView(sc,custSrc);
        AccountRepo accRepo = new AccountRepo();
        AccountService accService = new AccountService(custSrc,accRepo);
        AccountView accVw = new AccountView(sc,accService,custSrc);
       
        while (true) { 
            System.out.println("Select the service you want");
            System.out.println("1.Customer");
            System.out.println("2.Account");
            System.out.println("0.Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    custVw.start();
                    break;                
                case 2:
                    accVw.start();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.err.println("Wrong choice!");
            }
        }
    }
}
