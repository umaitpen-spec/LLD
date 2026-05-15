package model;

import Enum.AccountStatus;
import Enum.AccountType;

public class SavingsAccount extends Account {

    public SavingsAccount(Customer customer, AccountStatus accStatus)
    {
        super(customer,500,AccountStatus.ACTIVE,AccountType.SAVINGS);
    }
    // @Override
    // public Account createAccount(int accNum,Customer customer) {
    //     Account acc = new Account(accNum,customer,500,AccountStatus.ACTIVE,AccountType.SAVINGS);
    //     System.out.println("Savings Account Created Successfully");
    //     return acc;
}

