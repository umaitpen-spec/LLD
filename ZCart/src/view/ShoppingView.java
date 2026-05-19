package view;

import java.util.List;
import java.util.Scanner;
import model.Cart;
import model.Customer;
import repo.DBRepo;
import service.ShoppingService;

public class ShoppingView {

    ShoppingService shoppingsrc;
    DBRepo dbRepo;
    Scanner sc;
    public ShoppingView(ShoppingService shoppingService,Scanner sc,DBRepo dbRepo)
    {
        this.shoppingsrc = shoppingService;
        this.sc = sc;
        this.dbRepo = dbRepo;
    }

    public void start(Customer currCustomer)
    {
        System.out.println("Select the category u want to shop");
        List<String> catList = shoppingsrc.getCategory();
        int len = catList.size();
        for(int i=1;i<=len;i++)
        {
            System.out.println(i+"."+catList.get(i-1));
        }
        int choice = sc.nextInt();
        if(choice >=1 && choice <= len){
            doShopping(catList,choice,currCustomer);
        }
        else
            System.out.println("Wrong Choice!");
    }

    public void doShopping(List<String> catList, int choice,Customer currCustomer) {
        System.out.println("Select the product u want to Shop");
        String category = catList.get(choice-1);
        List<String> prodList = shoppingsrc.getProduct(category);
        int len = prodList.size();
        for(int i=1;i<=len;i++)
        {
            System.out.println(i+"."+prodList.get(i-1));
        }
        choice = sc.nextInt();
        String product = prodList.get(choice-1);
        if(choice >= 1 && choice <= len)
        {
            System.out.print("Enter the Quantity:");
            int qty = 0;
            qty = sc.nextInt();
            if(shoppingsrc.addToCart(currCustomer,category,product,qty))
                System.out.println("Product Added Successfully");
            else
                System.out.println("Out of Stock!!!");
            doContinueOrCheckOption(currCustomer);
        }
        else
            System.out.println("Wrong Choice!");
    }

    private void doContinueOrCheckOption(Customer currCustomer) {
        System.out.println("Do u want to 1.continue Shopping or 2.checkOut");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                start(currCustomer);
                break;
            case 2:
                checkout(currCustomer);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void checkout(Customer currCustomer) {
        System.out.println("Thank You for Shopping with Us");
        viewCart(currCustomer);
    }

    private void viewCart(Customer currCustomer) {
        double totAmount = 0;
        List<Cart> cartList = shoppingsrc.getCartByCustomer(currCustomer);
        shoppingsrc.reduceInventory(cartList);
        System.out.println("Hi, "+currCustomer.getName());
        String str = String.format("%-10s %-10s %-10s","Product","Quantity","Cost");
        System.out.println(str);
        for(Cart cart:cartList)
        {
            System.out.println(cart);
            totAmount += cart.getTotCost();
        }
        System.out.println("Total Amount:"+totAmount);
    }
}
