import java.util.Scanner;

public class CabView {

    Scanner sc;
    CabService cabService;
    public CabView(Scanner sc, CabService cSRc) {
        this.sc = sc;
        this.cabService = cSRc;
    }

    
    public void driverLogin() {
        System.out.println("Enter UserNAme");
        String name = sc.next();
        System.out.println("Enter Password");
        String password = sc.next();
        cabService.driverLogin(name,password);
    }

    public void CustomerLogin() {
        // TODO Auto-generated method stub
        System.out.println("Enter UserNAme");
        String name = sc.next();
        System.out.println("Enter Password");
        String password = sc.next();
        cabService.custLogin(name,password);
    }

    public void AdminLogin() {
        // TODO Auto-generated method stub
        System.out.println("Enter UserNAme");
        String name = sc.next();
        System.out.println("Enter Password");
        String password = sc.next();
        cabService.adminLogin(name,password);
    }

}
