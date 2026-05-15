package repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Account;
import model.TransactionHistory;

public class AccountRepo {
    private Map<Integer,Account> accList = new HashMap<>();
    private Map<Integer,TransactionHistory> accHistory = new HashMap<>();

    public void addAccount(int id,Account account)
    {
        accList.put(id,account);
    }

    public Account getAccountByID(int id)
    {
        return accList.getOrDefault(id, null);
    }

    public List<Account> getAllAccounts()
    {
        return new ArrayList<>(accList.values());
    }

    public void addTransactionHistory(TransactionHistory tHistory)
    {
        accHistory.put(tHistory.getAccNo(),tHistory);
    }

    public List<TransactionHistory> getAllTransaction(int accID)
    {
        return new ArrayList<>(accHistory.values());
    }
}
