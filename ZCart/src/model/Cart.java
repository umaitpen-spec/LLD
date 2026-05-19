package model;

import java.util.Map;

public class Cart {
    private Customer customer;
    Map<Inventory,Integer> sList;
    double totCost;
    
    public Cart(Customer customer, Map<Inventory, Integer> sList, double totCost) {
        this.customer = customer;
        this.sList = sList;
        this.totCost = totCost;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Map<Inventory, Integer> getsList() {
        return sList;
    }
    public void setsList(Map<Inventory, Integer> sList) {
        this.sList = sList;
    }
    public double getTotCost() {
        return totCost;
    }
    public void setTotCost(double totCost) {
        this.totCost = totCost;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Inventory, Integer> entry : sList.entrySet()) {
            Inventory inventory = entry.getKey();
            int quantity = entry.getValue();
            double cost = inventory.getPrice() * quantity;
            sb.append(String.format("%-10s %-10s %-10s", inventory.getModel(), quantity, cost));
        }
        return sb.toString();
    }
    
    
}
