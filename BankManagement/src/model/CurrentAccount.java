package model;

import Enum.AccountStatus;
import Enum.AccountType;

public class CurrentAccount extends Account{

    public CurrentAccount(Customer customer, AccountStatus accStatus)
    {
        super(customer,0,AccountStatus.ACTIVE,AccountType.CURRENT);
    }
}
