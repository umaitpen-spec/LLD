package model;

import Enum.Roles;

public class Customer
{
    private int customerID;
    private String username;
    private int age;
    private String mobileNumber;
    private String email;
    private String address;
    private Roles role;
    public Customer(int customerID, String name, int age, String mobileNumber, String email, String address,Roles role) {
        this.customerID = customerID;
        this.username = name;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.address = address;
        this.role = role;
    }
    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public String getName() {
        return username;
    }
    public void setName(String name) {
        this.username = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Roles getRole() {
        return role;
    }
    public void setRole(Roles role) {
        this.role = role;
    }
    @Override
    public String toString() {
        String cust = String.format("%-10s %-10s %-5s %-15s %-15s %-20s",customerID,username,age,mobileNumber,email,address);
        return cust;
        // return "Customer username=" + username + "| age=" + age + "| mobileNumber=" + mobileNumber + "| email=" + email
        //         + "| address=" + address + "";
    }
    
    
}