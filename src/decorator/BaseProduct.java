package decorator;

import java.util.HashMap;

import enums.*;


public class BaseProduct implements Product {
    private final ProductName name;
    private final double price;
    private final HashMap<AddOns, Double> addOns;
    private double quantity;

    public BaseProduct(ProductName name, double price, double quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.addOns = new HashMap<>();
        this.addOns.put(AddOns.BasePrice, price);
    }

    @Override
    public HashMap<AddOns, Double> getAddOns() {
        return addOns;
    }

    @Override
    public String getName() {
        return name.toString();
    }

    @Override
    public double getBasePrice() {
        return price;
    }

    @Override
    public double getQuantity() {
        return quantity;
    }

    @Override
    public boolean setQuantity(double quantity) {
        if (quantity < 0) {
            return false;
        }
        this.quantity = quantity;
        return true;
    }

    @Override
    public String toString() {
        return String.format("decorator.Product: %s, Price: %.2f,Available Quantity: %.2f", name, price, quantity);
    }
}
