package model;

public class ATM
{
    private int atmid;
    private double totAmount;

    public ATM(int atmid, double totAmount) {
        this.atmid = atmid;
        this.totAmount = totAmount;
    }

    public int getAtmid() {
        return atmid;
    }

    public void setAtmid(int atmid) {
        this.atmid = atmid;
    }

    public double getTotAmount() {
        return totAmount;
    }

    public void setTotAmount(double totAmount) {
        this.totAmount = totAmount;
    }
}


