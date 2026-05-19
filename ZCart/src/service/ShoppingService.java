package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import model.Cart;
import model.Customer;
import model.Inventory;
import repo.DBRepo;

public class ShoppingService {

    DBRepo dbRepo;
    public ShoppingService(DBRepo dbRepo)
    {
        this.dbRepo = dbRepo;
    }

    public List<String> getCategory() {
        List<Inventory> invenList = dbRepo.getAllInventory();
        HashSet<String> catSet =  new HashSet<>();
        for(Inventory inven:invenList)
        {
            catSet.add(inven.getCategory());
        }
        return new ArrayList<>(catSet);
    }

    public List<String> getProduct(String category) {
        List<Inventory> invenList = dbRepo.getAllInventory();
        HashSet<String> productSet =  new HashSet<>();
        for(Inventory inven :invenList)
        {
            if(inven.getCategory().equals(category))
                productSet.add(inven.getModel());
        }
        return new ArrayList<>(productSet);
    }

    public boolean addToCart(Customer customer,String category, String product, int qty) {
        //private Map<Customer,List<Cart>> cartItems = new HashMap<>();
        Inventory inventory = dbRepo.getInventoryByCatProd(category, product);
        if(qty <=0 || qty > inventory.getStock())
            return false;
        Map<Inventory, Integer> sList = new HashMap<>();
        sList.put(inventory, qty);

        double totCost= inventory.getPrice() * qty;
        Cart cart = new Cart(customer,sList,totCost);
        dbRepo.addToCart(customer,cart);
        return true;
    }    

    public List<Cart> getCartByCustomer(Customer customer) {
        return dbRepo.getCartByCustomer(customer);
    }

    public void reduceInventory(List<Cart> cartList) {
        dbRepo.reduceInventory(cartList);
    }

    public void addInventory(List<Cart> cartList) {
        dbRepo.addInventory(cartList);
    }
}
