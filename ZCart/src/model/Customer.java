package model;

public class Customer {
    private String name;
    private long mno;
    private String email;
    private String password;

    
    public Customer(String name, long mno, String email, String password) {
        this.name = name;
        this.mno = mno;
        this.email = email;
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getMno() {
        return mno;
    }
    public void setMno(long mno) {
        this.mno = mno;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        String str = String.format("%-15s %-10s %-10s %-10s"
            ,email
            ,password
            ,name
            ,""+mno
            );
        return str;
    }
    // @Override
    // public String toString() {
    //     return "Customer [name=" + name + ", mno=" + mno + ", email=" + email + ", password=" + password + "]";
    // }

    


    
}
