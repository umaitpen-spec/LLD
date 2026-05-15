package model;

import Enum.AccountStatus;
import Enum.AccountType;

public abstract class Account
{
    private static int accountNumber = 2000;
    private int accountNum;
    private Customer customer;
    private double balance;
    private AccountStatus accStatus;
    private AccountType accountType;
    public Account(Customer customer, double balance, AccountStatus accStatus,AccountType accType) {
        this.accountNum = accountNumber++;
        this.customer = customer;
        this.balance = balance;
        this.accStatus = accStatus;
        this.accountType = accType;
    }
    public int getAccountNum() {
        return accountNum;
    }
    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public AccountStatus getAccStatus() {
        return accStatus;
    }
    public void setAccStatus(AccountStatus accStatus) {
        this.accStatus = accStatus;
    }
    @Override
    public String toString() {
        String str = String.format("%-10s %-15s %5s %15s", accountNum ,customer.getName() , balance , accStatus);
        return str;
    }
    public AccountType getAccountType() {
        return accountType;
    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    
}