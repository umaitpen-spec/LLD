package model;

import java.time.LocalDateTime;

public class Email {
    private int emailId;
    private User senderId;
    private User receiverId;
    private String subject;
    private String message;
    private LocalDateTime timestamp;
    private Status status;
    private static int email = 1;
    
    public Email(User senderId, User receiverId, String subject, String message, Status status) {
        this.emailId = email++;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.subject = subject;
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
    public Email(int emailId, User senderId, User receiverId, String subject, String message, 
        int statusId, LocalDateTime dateTime) {
         this.emailId = emailId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.subject = subject;
        this.message = message;
        this.status = Status.getValue(statusId);
        this.timestamp = dateTime;
    }
    public int getEmailId() {
        return emailId;
    }
    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }
    public static int getEmail() {
        return email;
    }
    public static void setEmail(int email) {
        Email.email = email;
    }
    public User getSenderId() {
        return senderId;
    }
    public void setSenderId(User senderId) {
        this.senderId = senderId;
    }
    public User getReceiverId() {
        return receiverId;
    }
    public void setReceiverId(User receiverId) {
        this.receiverId = receiverId;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
   
    @Override
    public String toString() {
        return "Email [EmailId= " +  emailId + ", senderId=" + senderId.getEmail() + ", receiverId=" + receiverId.getEmail() + ", subject=" + subject + ", message="
                + message + ", status= " + status +", timestamp=" + timestamp +"]";
    }
    
}
