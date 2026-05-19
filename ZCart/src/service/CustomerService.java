package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import model.Customer;
import repo.DBRepo;

public class CustomerService {
    private DBRepo dbRepo;

    public CustomerService(DBRepo dbRepo) throws IOException
    {
        this.dbRepo = dbRepo;
    }

    private void addCustomer(Customer cust)
    {
        dbRepo.getAddCustomer(cust);
    }
    public void readCustFromFromFile(String filename) throws IOException
    {
        BufferedReader br = new BufferedReader(
            new FileReader(filename)
        );
        String line;
        //String name, long mno, String email, String password
        while((line = br.readLine()) != null)
        {
            if(line.trim().isEmpty())
            {
                continue;
            }
            String[] customer = line.split(" ");
            Customer cust = new Customer(customer[2], Long.parseLong(customer[3]), customer[0], customer[1]);
            addCustomer(cust);
        }
        br.close();
    }

    public List<Customer> getAllCustomers()
    {
        return  dbRepo.getAllCustomers();        
    }

    public Customer addCustomer(String email, String password, String name, Long mobileNo) {
        Customer cust = new Customer(name, mobileNo, email, password);
        dbRepo.getAddCustomer(cust);
        return cust;
    }

    public boolean isAlreadyExists(String email) {
        return dbRepo.chkEmailAlreadyExists(email);
    }

    public String encryptPassword(String password) {
        StringBuilder sb = new StringBuilder();
        for(char ch:password.toCharArray())
        {
            switch (ch) {
                case 'z' -> ch = 'a';
                case 'Z' -> ch = 'A';
                case '9' -> ch = '0';
                default -> ch++;
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    public void addToFile(String line,String fileName) throws IOException {
        BufferedWriter bw = new BufferedWriter(
          new FileWriter(fileName,true)  
        );
        bw.write(line);
        bw.newLine();
        bw.close();
    }

    public boolean login(String email, String password) {
        String ePassword = encryptPassword(password);
        if(ePassword.equals(getEPassword(email)))
            return true;
        return false;
    }

    public String getEPassword(String email)
    {
        return dbRepo.getEPasswordFromEmail(email);
    }

    public Customer getCustomerByEmail(String email) {
        return dbRepo.getCustomerByEmail(email);
    }
}
