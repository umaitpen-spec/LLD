package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import model.Inventory;
import repo.DBRepo;

public class InventoryService {
    private DBRepo dbRepo;

    public InventoryService(DBRepo dbRepo) throws IOException
    {
        this.dbRepo = dbRepo;
    }

    private void addCustomer(Inventory inven)
    {
        dbRepo.getAddInventory(inven);
    }
    public void readInvenFromFromFile(String filename) throws IOException
    {
        BufferedReader br = new BufferedReader(
            new FileReader(filename)
        );
        String line;
        //String category, String brand, String model, double price, int stock) 
        while((line = br.readLine()) != null)
        {
            if(line.trim().isEmpty())
            {
                continue;
            }
            String[] inventory = line.split(" ");
            inventory[3] = inventory[3].replace(",", "");
            Inventory cust = new Inventory(inventory[0], 
                inventory[1], 
                inventory[2], 
                Double.parseDouble(inventory[3]),
                Integer.parseInt(inventory[4]));
            addCustomer(cust);
        }
        br.close();
    }
    
    public List<Inventory> getAllInventory()
    {
        return dbRepo.getAllInventory();
    }

    public void editAllInventory() {
        
    }
}
