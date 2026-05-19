package view;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import model.Inventory;
import service.InventoryService;
import service.ShoppingService;

public class InventoryView {
    InventoryService invenSrc;
    ShoppingService shoppingsrc;
    Scanner sc;
    public InventoryView(InventoryService invenSrc,Scanner sc,ShoppingService shoppingsrc)
    {
        this.invenSrc = invenSrc;
        this.sc = sc;
        this.shoppingsrc = shoppingsrc;
    }

    public void addInventoryList(String fileName) throws IOException
    {
        System.out.println("Creating Inventory list...");
        invenSrc.readInvenFromFromFile(fileName);
        System.out.println("Inventory Added Sucessfully!");
    }

    public void printAllInventory()
    {
        List<Inventory> inveList = invenSrc.getAllInventory();
        String str = String.format("%-7s %-10s %-15s %-10s %-7s"
        ,"Category","Brand","Model","Price","Stock");
        System.out.println(str);
        System.out.println("--------------------------------------------------");
        for(Inventory inven:inveList)
            System.out.println(inven);
    }

    void viewAdminMenu() {
        System.out.println("Enter ur option");
        System.out.println("1.View All Inventory");
        System.out.println("2.Edit Inventory");
        System.out.println("0.Exit");
        int choice = sc.nextInt();
            switch(choice) {
                case 1:
                    printAllInventory();
                    break;  
                case 2:
                    editAllInventory();
                    break;                
                case 0:
                    return;
                default:
                    System.out.println("Wrong Choice!");
            }
    }

    private void editAllInventory() {
        System.out.println("Select from the list of Category u want to edit");
        List<String> catList = shoppingsrc.getCategory();
        int len = catList.size();
        for(int i=1;i<=len;i++)
        {
            System.out.println(i+"."+catList.get(i-1));
        }
        int choice = sc.nextInt();
        if(choice >=1 && choice <= len){
            System.out.println("Select the product u want to edit");
            String category = catList.get(choice-1);
            List<String> prodList = shoppingsrc.getProduct(category);
            len = prodList.size();
            for(int i=1;i<=len;i++)
            {
                System.out.println(i+"."+prodList.get(i-1));
            }
            int choice1 = sc.nextInt();
            String product = prodList.get(choice1-1);
            System.out.println("U want to 1.Add 2.Remove");
            int choice3 = sc.nextInt();
            switch (choice3) {
                case 1:
                    
                    break;
                case 2:
                    break;
                default:
                    throw new AssertionError();
            }

        }
        else
            System.out.println("Wrong Choice!");
    }
}
