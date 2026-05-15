import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.*;

public class ATMService {
    Scanner sc = new Scanner(System.in);
    BankAccount bankAccount ;
    BankAccount currCustomer = null;
    ATM atm = null;
    List<BankAccount> custList = new ArrayList<>();
    ATMService()
    {
        custList.add(new BankAccount(1,1,123,20000));
        custList.add(new BankAccount(2,2,345,25000));
        atm = new ATM(1,100000);
    }
    int custId;
    public boolean login()
    {
        System.out.println("Enter the UserName:");
        int id = sc.nextInt();
        System.out.println("Enter the PIN no");
        int pin = sc.nextInt();
        currCustomer = custList.stream()
                .filter(a->a.getCustId() == id
                 && a.getPin() == pin).findFirst().orElse(null);
        if(currCustomer == null) {
            System.out.println("Wrong Credentials!.");
            return false;
        }
        else
            return true;
    }
    public void checkBalance()
    {
        System.out.println("Your current Balance:"+currCustomer.getTotBalance());
    }
    public void withDraw()
    {
        System.out.println("Enter the amount to WithDraw");
        double amt = sc.nextDouble();
        if(currCustomer.getTotBalance() < amt)
            System.out.println("You dont have enough balance");
        else if(atm.getTotAmount() < amt)
            System.out.println("ATM doesnt have enough balance. Sorry for thr Inconvenience.");
        else if(amt <= 0)
        {
            System.err.println("Amount cannot be negative or Zero!");
        }
        else
        {
            currCustomer.setTotBalance(currCustomer.getTotBalance() - amt);
            System.out.println("Amount Withdrawn Successfully");
        }
    }

    public void deposit() {
        System.out.println("Enter the amount to deposit:");
        double amt = sc.nextDouble();
        if(amt <= 0)
        {
            System.err.println("Amount cant be zero or negative");
        }
        else {
            currCustomer.setTotBalance(currCustomer.getTotBalance() + amt);
            System.out.println("Amount Deposited Successfully!");
        }
    }
}

class BankAccount
{
    private int accNo;
    private int custId;
    private int pin;
    private double totBalance;

    public BankAccount(int accNo, int custId, int pin, double totBalance) {
        this.accNo = accNo;
        this.custId = custId;
        this.pin = pin;
        this.totBalance = totBalance;
    }

    public int getAccNo() {
        return accNo;
    }

    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getTotBalance() {
        return totBalance;
    }

    public void setTotBalance(double totBalance) {
        this.totBalance = totBalance;
    }
}