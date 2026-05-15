package service;

import Enum.AccountStatus;
import Enum.AccountType;
import Factory.AccountFactory.AccountFactory;
import java.util.List;
import model.Account;
import model.Customer;
import model.TransactionHistory;
import repo.AccountRepo;

public class AccountService {
    AccountRepo accRepo;
    CustomerService custSrc;
    public AccountService(CustomerService custSrc,AccountRepo accRepo) {
        this.custSrc = custSrc;
        this.accRepo = accRepo;
    }

    public void createAccount(Customer cust,AccountType accType) {       
            Account acc = AccountFactory.createAccount(cust,AccountStatus.ACTIVE,accType);
            accRepo.addAccount(acc.getAccountNum(),acc);
            System.out.println(accType + " Account Created Successfully");
            System.out.println(acc);        
    }

    public List<Account> getAllAccounts()
    {
        return accRepo.getAllAccounts();
    }

    public double checkBalance(int accID) 
    {
        Account account = accRepo.getAccountByID(accID);
        if(account == null)
            return -1;
        return account.getBalance();
    }

   
    public double depositMoney(Account acc,int balance)
    {
        acc.setBalance(balance + acc.getBalance());
        return acc.getBalance();
    }

    public double withDrawMoney(Account acc,int balance)
    {
        acc.setBalance(acc.getBalance() - balance);
        return acc.getBalance();
    }

    public Account getAccountById(int accID)
    {
        return accRepo.getAccountByID(accID);
    }

    public void addTransactionHistory(TransactionHistory tHistory)
    {
        accRepo.addTransactionHistory(tHistory);
    }

    public List<TransactionHistory> getAllTransactionHistory(int accID)
    {
        return accRepo.getAllTransaction(accID);
    }

    public void blockAccount(Account acc) {
        acc.setAccStatus(AccountStatus.BLOCKED);
        accRepo.addAccount(acc.getAccountNum(),acc);
    }

    public void unBlockAccountlockAccount(Account acc) {
        acc.setAccStatus(AccountStatus.ACTIVE);
        accRepo.addAccount(acc.getAccountNum(),acc);
    }

    public double getTotBankBalance() {
        int totBalance = 0;
        for(Account acc: accRepo.getAllAccounts())
            totBalance += acc.getBalance();
        return totBalance;
    }
}
