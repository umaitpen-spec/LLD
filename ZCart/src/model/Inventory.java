package model;

public class Inventory {
    private String category;
    private String brand; 
    private String model ;
    private double price ;
    private int stock;

    
    public Inventory(String category, String brand, String model, double price, int stock) {
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.stock = stock;
    }
    
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        String str = String.format("%-7s %-10s %-15s %-10s %-7s"
        ,getCategory(),getBrand(),getModel(),getPrice(),getStock());
        return str;
    }
    
    
}
