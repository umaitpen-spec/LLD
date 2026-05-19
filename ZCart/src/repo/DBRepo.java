package repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Cart;
import model.Customer;
import model.Inventory;

public class DBRepo {
    private Map<String,Customer> custList = new HashMap<>();
    private Map<String,Inventory> invList = new HashMap<>();
    private Map<Customer,List<Cart>> cartItems = new HashMap<>();
    
    public void getAddCustomer(Customer cust)
    {
        custList.put(cust.getEmail(),cust);
    }

    public void getAddInventory(Inventory inven)
    {
        invList.put(inven.getModel(),inven);
    }

    public List<Customer> getAllCustomers()
    {
        return new ArrayList<>(custList.values());
    }

    public List<Inventory> getAllInventory()
    {
        return new ArrayList<>(invList.values());
    }

    public boolean chkEmailAlreadyExists(String email) {
        return (custList.containsKey(email));
    }

    public String getEPasswordFromEmail(String email) {
        if(chkEmailAlreadyExists(email))
        {
            return custList.get(email).getPassword();
        }
        return null;
    }

    public Inventory getInventoryByCatProd(String category,String product) {

        Inventory inven =  invList.get(product);
        if(inven != null && inven.getCategory().equals(category))
            return inven;
        return null;
    }

    public Customer getCustomerByEmail(String email) {
        return custList.get(email);
    }

    public List<Cart> getCartByCustomer(Customer customer)
    {
        return cartItems.getOrDefault(customer,new ArrayList<>());
    }    

    public void addToCart(Customer customer, Cart cart) {
        List<Cart> carts = cartItems.getOrDefault(customer, new ArrayList<>());
        carts.add(cart);
        cartItems.put(customer,carts);
    }

    public void reduceInventory(List<Cart> cartList) {
        for(Cart cart:cartList)
        {
            for (Map.Entry<Inventory, Integer> entry : cart.getsList().entrySet()) {
                Inventory inventory = entry.getKey();
                int quantity = entry.getValue();
                Inventory availableInventory = invList.get(inventory.getModel());
                if(availableInventory != null) {
                    availableInventory.setStock(availableInventory.getStock() - quantity);
                }
            }       
        }
    }

    public void addInventory(List<Cart> cartList) {
        for(Cart cart:cartList)
        {
            for (Map.Entry<Inventory, Integer> entry : cart.getsList().entrySet()) {
                Inventory inventory = entry.getKey();
                int quantity = entry.getValue();
                Inventory availableInventory = invList.get(inventory.getModel());
                if(availableInventory != null) {
                    availableInventory.setStock(availableInventory.getStock() + quantity);
                }
            }       
        }
    }
}
