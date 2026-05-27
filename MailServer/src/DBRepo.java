
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Email;
import model.User;


public class DBRepo{
    Connection conn = null;
    public DBRepo() throws  SQLException
    {
        conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/mail_server",
            "devuser",
            "StrongPassword123!"
        );
    }

    public void insertUser(User user) throws SQLException
    {
        String insertStmt = "insert into users (user_name,password) values(?,?);";
        PreparedStatement ps = conn.prepareStatement(insertStmt);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());
        ps.executeUpdate();
    }

    public void insertEmail(Email email) throws SQLException
    {
        try{
        String insertstmt = "insert into email values(?,?,?,?,?,?,?);";
        PreparedStatement ps = conn.prepareStatement(insertstmt);
        ps.setInt(1, email.getEmailId() );
        ps.setInt(2, email.getSenderId().getUserId());
        ps.setInt(3, email.getReceiverId().getUserId());
        ps.setString(4, email.getSubject());
        ps.setString(5, email.getMessage());
        ps.setInt(6, email.getStatus().getValue());
        ps.setTimestamp(7, java.sql.Timestamp.valueOf(email.getTimestamp()));
        ps.executeUpdate();
     }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> getUser() throws SQLException
    {
        String getUser = "select * from users";
        PreparedStatement ps = conn.prepareStatement(getUser);
        ResultSet rs = ps.executeQuery();

        List<User> userList = new ArrayList<>();

        while(rs.next())
        {
            User user = new User(
                rs.getInt("user_id"),
                rs.getString("user_name"),
                rs.getString("password")
            );
            userList.add(user);
        }
        return userList;
    }

    public List<Email> getEmail() throws SQLException
    {
        List<Email> emailList = new ArrayList<>();
        try{
        String getEmail = "select * from email";
        PreparedStatement ps = conn.prepareStatement(getEmail);
        ResultSet rs = ps.executeQuery();
            
        List<User> userList = getUser();

        while(rs.next())
        {

            int sendID  = rs.getInt("senderId");
            int receiverId = rs.getInt("receiverId");
            User sender = userList.stream().filter(a->a.getUserId() == sendID)
                .findFirst().get();
            User receiver = userList.stream().filter(a->a.getUserId() == receiverId)
                .findFirst().get();

            Email email = new Email(rs.getInt("emailId"), 
                sender, 
                receiver,
                rs.getString("subject"),
                rs.getString("message"), 
                rs.getInt("status"),
                rs.getTimestamp("timestamp").toLocalDateTime());

            emailList.add(email);                
        }
    }
    catch(Exception ex)
    {
        
    }
        return emailList;
    }

    public void updateEmailStatus(int emailId,int statusId) throws SQLException{
        String sqlUpdate = "update email SET status = ? where emailid = ?";
        PreparedStatement ps = conn.prepareStatement(sqlUpdate);
        ps.setInt(1, statusId);
        ps.setInt(2, emailId);
        ps.executeUpdate();
    }
}
