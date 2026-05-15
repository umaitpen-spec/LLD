package model;

import Enum.TransactionType;
import java.time.LocalDate;

public class TransactionHistory {
    private int accNo;
    private LocalDate tDate;
    private TransactionType tType;
    private double amount;
    public TransactionHistory(int accNo, LocalDate tDate, TransactionType tType, double amount) {
        this.accNo = accNo;
        this.tDate = tDate;
        this.tType = tType;
        this.amount = amount;
    }
    public int getAccNo() {
        return accNo;
    }
    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }
    public LocalDate gettDate() {
        return tDate;
    }
    public void settDate(LocalDate tDate) {
        this.tDate = tDate;
    }
    public TransactionType gettType() {
        return tType;
    }
    public void settType(TransactionType tType) {
        this.tType = tType;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        String str = String.format("%-15s %-10s %-15s %-7s",accNo , tDate ,tType ,amount);
        return str;
    }
    
}
