package model;

public class User {
    private int userId;
    private String email;
    private String password;
    private static int user = 1;

    public User(String email,String password) {
        this.userId = user++;
        this.email = email;
        this.password = password;
    }

    public User(int userId,String email,String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public static int getUser() {
        return user;
    }


    public static void setUser(int user) {
        User.user = user;
    }


    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
   
   
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
}
