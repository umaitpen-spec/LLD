package view;

import Enum.AccountStatus;
import Enum.AccountType;
import Enum.TransactionType;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import model.Account;
import model.Customer;
import model.TransactionHistory;
import service.AccountService;
import service.CustomerService;
import util.Util;

public class AccountView {
    AccountService accService;
    CustomerService custSrc;
    Scanner sc;
    public AccountView(Scanner sc, AccountService accService,CustomerService custSrc) {
        this.sc = sc;
        this.accService = accService;
        this.custSrc = custSrc;
    }

    public void start()
    {
        System.out.println("Enter the option");
        System.out.println("1.Create Account");
        System.out.println("2.View Account Detail");
        System.out.println("3.Check Balance");
        System.out.println("4.Deposit money");
        System.out.println("5.WithDraw money");
        System.out.println("6.Transfer money");
        System.out.println("7.Transaction History");
        System.out.println("8.Block Account");
        System.out.println("9.UnBlock Account");
        System.out.println("10.Total Balance");
        System.out.println("0.Return to Main menu");
        int choice = Util.chkInteger("", sc);
        switch (choice) {
                case 1:
                    createAccount();                    
                    break;
                case 2:
                    viewAllAccount();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    depositMoney();
                    break;
                case 5:
                    withDrawMoney();
                    break;
                case 6:
                    TransferMoney();
                    break;
                case 7:
                    getTransactionHistory();
                    break;
                case 8:
                    blockAccount();
                    break;
                case 9:
                    unBlockAccount();
                    break;
                case 10:
                    getTotBankBalance();
                    break;
                case 0:
                    return;
                default:
                    System.err.println("Wrong choice!");
                    sc.nextLine();
                    return;
            }
    }

    private void getTotBankBalance() {     
        double totBalance = accService.getTotBankBalance();
        System.out.println("Total Balance:" + totBalance);
    }

    private void unBlockAccount() {
        String msg = "Enter the Account Number u want to Block:";
        int accId = Util.chkInteger(msg, sc);
        Account acc = accService.getAccountById(accId);
        if(acc == null)
            System.out.println("Account not Valid");
        else
        {
            accService.unBlockAccountlockAccount(acc);
            System.out.println("Account UnBlocked");
        }
    }

    private void blockAccount() {
        String msg = "Enter the Account Number u want to Block:";
        int accId = Util.chkInteger(msg, sc);
        Account acc = accService.getAccountById(accId);
        if(acc == null)
            System.out.println("Account not Valid");
        else
        {
            accService.blockAccount(acc);
            System.out.println("Account Blocked");
        }
    }

    private void TransferMoney() {
        String msg = "Enter the FROM Account Number u want to Transfer:";
        int fromAccID = Util.chkInteger(msg, sc);
        Account fromAacc = accService.getAccountById(fromAccID);
        if(fromAacc == null)
            System.out.println("From Account Doesnot exists");
        else if(fromAacc.getAccStatus() == AccountStatus.BLOCKED)
                System.out.println(fromAacc.getAccountNum() + " Account Blocked");
        else
        {
            msg = "Enter the TO Account Number u want to Deposit:";
            int toAccID = Util.chkInteger(msg, sc);
            Account toAcc = accService.getAccountById(toAccID);
            if(toAcc == null)
                System.out.println("To Account Doesnot exists");
            else if(toAcc.getAccStatus() == AccountStatus.BLOCKED)
                System.out.println(toAcc.getAccountNum() + " Account Blocked");
            else
            {
                msg = "Enter the amount to WithDraw from "+ fromAacc.getCustomer().getName()+ " account:";
                int transferAmt = Util.chkInteger(msg, sc);
                if(transferAmt < 0)
                    System.out.println("Amount cannot be negative");
                else if(transferAmt > fromAacc.getBalance())
                    System.out.println("Low Balance");
                else
                {
                    double currBal = accService.withDrawMoney(fromAacc, transferAmt);
                    currBal = accService.depositMoney(toAcc, transferAmt);
                    TransactionHistory tHistory = new TransactionHistory(fromAccID,
                     LocalDate.now(),
                     TransactionType.WITHDRAW, 
                     transferAmt);
                    accService.addTransactionHistory(tHistory);
                     tHistory = new TransactionHistory(toAccID,
                     LocalDate.now(),
                     TransactionType.DEPOSIT, 
                     transferAmt);
                    accService.addTransactionHistory(tHistory);
                    System.out.println("Amount Transfered!!!");
                }
            }
        }        
    }

    public void createAccount() {
        String msg = "Enter the customer ID to create account:";
        int custID = Util.chkInteger(msg, sc);
        Customer cust = custSrc.getCustomerById(custID);
        if(cust == null)
            System.out.println("Customer Id Invalid");
        else
        {
            msg  = "Do u want to create 1.Saving/2.Current Account:";           
            AccountType accType = null;
            while(accType == null)
            {
                int choice = Util.chkInteger(msg, sc);
                switch (choice) {
                    case 1:
                        accType = AccountType.SAVINGS;
                        break;
                    case 2:
                        accType = AccountType.CURRENT;
                        break;
                    default:
                        System.err.println("Wrong choice!");
                        sc.nextLine();
                }
            }
            accService.createAccount(cust,accType);
        }
       
    }

    public void viewAllAccount() {
        List<Account> accList = accService.getAllAccounts();
        if(accList.isEmpty())
            System.err.println("N0 accounts");
        else
        {
            String str = String.format("%-10s %-15s %5s %15s", "AccountID" ,"CustomerName" , "Balance" , "Status");
            System.out.println(str);
            for(Account acc: accList)
                System.out.println(acc);
        }
    }

    public void checkBalance() {
        String msg = "Enter the Account ID to Check Balance:";
        int accID = Util.chkInteger(msg, sc);
        double balance = accService.checkBalance(accID);
        if(balance == -1)
            System.err.println("Account Not Valid");
        else
            System.out.println("Balance: "+ balance);
    }

    public void depositMoney()
    {
        String msg = "Enter the Account ID to Deposit Money:";
        int accID = Util.chkInteger(msg, sc);
        Account acc = accService.getAccountById(accID);
        if(acc == null)
            System.out.println("Account Doesnot exists");
        else
        {
            msg = "Enter the amount to deposit in "+acc.getCustomer().getName()+" Account:";
            double amount = Util.chkDouble(msg, sc);
            if(amount < 0)
                System.out.println("Amount cannot be negative");
            else
            {
                double currBal = accService.depositMoney(acc, amount);
                TransactionHistory tHistory = new TransactionHistory(accID,
                     LocalDate.now(),
                     TransactionType.DEPOSIT, 
                     amount);
                accService.addTransactionHistory(tHistory);
                System.out.println("Amount Deposited. Availabale balance:"+currBal);
                
            }
        }
    }

    public void withDrawMoney()
    {
        String msg = "Enter the Account ID to WithDraw Money:";
        int accID = Util.chkInteger(msg, sc);
        Account acc = accService.getAccountById(accID);
        if(acc == null)
            System.out.println("Account Doesnot exists");
        else
        {
            msg = "Enter the amount to WithDraw from "+ acc.getCustomer().getName()+ " account:";
            double amount = Util.chkDouble(msg, sc);
            if(amount < 0)
                System.out.println("Balance cannot be negative");
            else if(amount > acc.getBalance())
                System.out.println("Low Balance");
            else
            {
                double currBal = accService.withDrawMoney(acc, amount);
                TransactionHistory tHistory = new TransactionHistory(accID,
                     LocalDate.now(),
                     TransactionType.WITHDRAW, 
                     amount);
                accService.addTransactionHistory(tHistory);
                System.out.println("Amount WithDrawn. Availabale balance:"+currBal);
            }
        }
    }

    private void getTransactionHistory() {
        String msg = "Enter the account number to get the TransactionHistory:";
        int accID = Util.chkInteger(msg, sc);
        Account acc = accService.getAccountById(accID);
        if(acc == null)
            System.out.println("Account Doesnot exists");
        else
        {
            String str = String.format("%-15s %-10s %-15s %-7s","AccountNumber" , "Date" ,"Transaction" ,"Amount");
            System.out.println(str);
            List<TransactionHistory> tList = accService.getAllTransactionHistory(accID);
            for(TransactionHistory tHistory:tList)
                System.out.println(tHistory);
        }
    }
}
