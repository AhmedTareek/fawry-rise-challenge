package decorator;

import java.util.HashMap;

import enums.AddOns;

public interface Product {
    String getName();

    double getBasePrice();

    double getQuantity();

    boolean setQuantity(double quantity);

    HashMap<AddOns, Double> getAddOns();
}
