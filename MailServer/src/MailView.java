
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.text.View;

import model.Email;
import model.Status;
import model.User;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class MailView{

    Scanner sc;
    User currUser = null;
    private Map<String,User> userList = new HashMap<>();
    private List<Email> emailList = new ArrayList<>();
    public MailView(Scanner sc) {
        this.sc = sc;
    }

    public void registerUser() {
        System.out.println("Enter the deatils to Register");
        System.out.print("Enter Email(UserName):");
        String name = sc.next();
        System.out.print("Enter Password:");
        String email = sc.next();
        User user = new User(name, email);
        userList.put(user.getEmail(),user);
        System.out.println("User Registered Sucsessfully!!!");
    }

    public void loginUser() {
        System.out.print("Enter the UserName:");
        currUser = null;
        String email = sc.next();
        if(userList.get(email) == null)
            System.out.println("UserName Does Not exist");
        else
        {
            System.err.print("Enter The Password:");
            String password = sc.next();
            if(password.equals(userList.get(email).getPassword()))
            {
                System.out.println("User Logged In Sucessfully!!");
                currUser = userList.get(email);
                startLoginUser();
            }
            else
                System.out.println("Wrong Password!!!");
        }
    }

    public void startLoginUser()
    {
        while (true) { 
            System.out.println("Select one of the Option");
            System.out.println("1.Send Email");
            System.out.println("2.View Inbox");
            System.out.println("3.View Sent Mails");
            System.out.println("4.Mark a mail as Read");
            System.out.println("5.Mark a mail as Unread.");
            System.out.println("6.Delete a mail from Inbox");
            System.out.println("7.Search");
            System.out.println("0.Return to main menu");
            int choice = util.chkUser("", sc);
            switch (choice) {
                case 1:
                    sendEmail();
                    break;
                case 2:
                    viewInbox();
                    break;  
                case 3:
                    viewSendMail();
                    break;
                case 4:
                    markEmailRead();
                    break;
                case 5:
                    markEmailUnread();
                    break;
                case 6:
                    deleteEmail();
                    break;
                case 7:
                    search();
                    break;
                case 0:
                    currUser = null;
                    return;
                default:
                    System.out.println("Wrong choice!!!");
            }
        }        
    }

    private void viewInbox() {
        System.out.println("My Emails");
        List<Email> inbox = emailList.stream()
                            .filter(a->a.getReceiverId().equals(currUser))
                            .collect(Collectors.toList());
        for(Email email:inbox)
            System.out.println(email);
        
    }

    private void markEmailRead() {
        viewInbox();        
        int num = util.chkUser("Enter the emailId to want to mark as read:", sc);
        Email email = emailList.stream()
                        .filter(a->a.getEmailId() == num)
                        .findFirst().get();
        email.setStatus(Status.READ);
    }

    private void viewSendMail() {
        System.out.println("My Emails");
        List<Email> inbox = emailList.stream()
                            .filter(a->a.getSenderId().equals(currUser))
                            .collect(Collectors.toList());
        for(Email email:inbox)
            System.out.println(email);
    }

    private void markEmailUnread() {
        viewInbox();        
        int num = util.chkUser("Enter the emailId to want to mark as UNread:", sc);
        Email email = emailList.stream()
                        .filter(a->a.getEmailId() == num)
                        .findFirst().get();
        email.setStatus(Status.UNREAD);
    }

    private void deleteEmail() {
        viewInbox();        
        int num = util.chkUser("Enter the emailId to want to Delete:", sc);
        Email email = emailList.stream()
                        .filter(a->a.getEmailId() == num)
                        .findFirst().get();
        email.setStatus(Status.DELETE);
    }

    private void search() {
        String msg = "Enter how to want to Search 1.Subject 2.Sender 3.Keyword in message:";
        int choice = util.chkUser(msg, sc);
        switch (choice) {
            case 1:
                subjectSearch();
                break;
            case 2:
                senderSearch();
                break;
            case 3:
                messageSearch();
                break;
            default:
                System.out.println("Wrong choice!!!");
        }
    }
    private void sendEmail() {
        User rUser = null;
        while (rUser == null) { 
            System.out.print("Enter receiver email:");
            String remail = sc.next();
            rUser = userList.get(remail);
        }
        
        System.out.print("Enter the subject:");
        String subject = sc.next();
        System.out.print("Enter the message:");
        String message = sc.next();
        Email email = new Email(currUser, rUser, subject, message, Status.SENT);
        emailList.add(email);
        System.out.println("Email sent Successfully");
    }

    private void subjectSearch() {
        System.out.print("Enter the text u want to search in subject:");
        String str = sc.next();
        List<Email> inbox = emailList.stream()
                            .filter(a->(a.getReceiverId().equals(currUser) 
                            && a.getSubject().contains(str)) )
                            .collect(Collectors.toList());
        List<Email> sent = emailList.stream()
                            .filter(a->(a.getSenderId().equals(currUser)  
                            && a.getSubject().contains(str)))
                            .collect(Collectors.toList());
        inbox.addAll(sent);
        for(Email email:inbox)
            System.out.println(email);
    }

    private void senderSearch() {
       System.out.print("Enter the text u want to search in sender:");
        String str = sc.next();
        List<Email> inbox = emailList.stream()
                            .filter(a->(a.getReceiverId().equals(currUser) 
                            && a.getSenderId().getEmail().contains(str)) )
                            .collect(Collectors.toList());
       
        for(Email email:inbox)
            System.out.println(email);
    }

    private void messageSearch() {
        System.out.print("Enter the text u want to search in Message:");
        String str = sc.next();
        List<Email> inbox = emailList.stream()
                            .filter(a->(a.getReceiverId().equals(currUser) 
                            && a.getMessage().contains(str)) )
                            .collect(Collectors.toList());
        List<Email> sent = emailList.stream()
                            .filter(a->(a.getSenderId().equals(currUser)  
                            && a.getMessage().contains(str)))
                            .collect(Collectors.toList());
        inbox.addAll(sent);
        for(Email email:inbox)
            System.out.println(email);
    }
}
