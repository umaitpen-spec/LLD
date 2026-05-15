package Factory.AccountFactory;

import Enum.AccountStatus;
import Enum.AccountType;
import model.Account;
import model.CurrentAccount;
import model.Customer;
import model.SavingsAccount;

public class  AccountFactory {
    public static Account createAccount(Customer cust, AccountStatus accountStatus, AccountType accType){
        switch (accType) {
            case SAVINGS:
                return new SavingsAccount(cust,accountStatus);
            case CURRENT :
                return new CurrentAccount(cust,accountStatus);
            default:
                return null;
        }
    }
}
