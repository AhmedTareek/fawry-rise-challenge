import decorator.*;
import enums.ProductName;

import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Product cheese = new BaseProduct(ProductName.Cheese, 2.5, 10);
        cheese = new ShippingDecorator(cheese, 1.5); // Adding shipping cost for cheese
        cheese = new ExpirationDecorator(cheese,createExpirationDate(2025,7,1)); // Adding expiration date for cheese

        Product tv = new BaseProduct(ProductName.Tv, 500.0, 10);
        tv = new ShippingDecorator(tv, 20.0, 1.75); // Adding shipping cost for TV

        Product biscuits = new BaseProduct(ProductName.Biscuits, 1.5, 15);
        biscuits = new ShippingDecorator(biscuits, 5,0.2); // Adding shipping cost for biscuits
        biscuits = new ExpirationDecorator(biscuits, createExpirationDate(2026,1,1)); // Adding expiration date for biscuits

        Product mobile = new BaseProduct(ProductName.Mobile, 300.0, 10);
        mobile = new ShippingDecorator(mobile, 10.0, 0.3); // Adding shipping cost for mobile

        Product mobileScratchCard = new BaseProduct(ProductName.MobileScratchCard, 5.0, 2);

        // this cart got expired cheese
        System.out.println("Cart with expired cheese:");
        Cart cart = new Cart();
        cart.addProduct(cheese, 0.75);
        cart.addProduct(tv, 1.0);
        cart.addProduct(mobileScratchCard,1);
        cart.addProduct(biscuits, 2.0);
        cart.processCart(1000);

        System.out.println("\n-----------------------------------\n");


        // this cart got insufficient balance
        System.out.println("Cart with insufficient balance:");
        Cart cart2 = new Cart();
        cart2.addProduct(tv, 1.0);
        cart2.addProduct(mobile, 1.0);
        cart2.addProduct(mobileScratchCard, 1);
        cart2.addProduct(biscuits, 2.0);
        cart2.processCart(20);

        System.out.println("\n-----------------------------------\n");

        System.out.println("Empty cart:");
        Cart emptyCart = new Cart();
        emptyCart.processCart(1000);

        System.out.println("\n-----------------------------------\n");

        // this cart got no shipping items
        System.out.println("Cart with no shipping items:");
        Cart cart3 = new Cart();
        cart3.addProduct(mobileScratchCard, 1);
        cart3.processCart(100);

        System.out.println("\n-----------------------------------\n");

        // Successful Cart
        System.out.println("Successful cart ");
        Cart cart4 = new Cart();
        cart4.addProduct(tv, 1.0);
        cart4.addProduct(mobile, 1.0);
        cart4.addProduct(mobileScratchCard, 1);
        cart4.addProduct(biscuits, 2.0);
        cart4.processCart(1000);


        // cart with insufficient stock
        System.out.println("\n-----------------------------------\n");
        System.out.println("Cart with insufficient stock:");
        Cart cart5 = new Cart();
        cart5.addProduct(cheese, 15); // Adding more than available stock
        cart5.addProduct(tv, 1.0);
        cart5.processCart(1000);





    }
    private static Date createExpirationDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); // Month is 0-based, so subtract 1
        return calendar.getTime();
    }
}