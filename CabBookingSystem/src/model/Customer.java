package model;

public class Customer {
    private int custId;
    private String name;
    private String password;
    private int pass;
    private int age;
    private static int customer = 1;
    public Customer(int custId, String name, String password,int pass, int age) {
        this.custId = custId;
        this.name = name;
        this.password = password;
        this.pass = pass;
        this.age = age;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getCustId() {
        return custId;
    }
    public void setCustId(int custId) {
        this.custId = custId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPass() {
        return pass;
    }
    public void setPass(int pass) {
        this.pass = pass;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    
}
