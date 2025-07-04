package addOnsActions;

import decorator.Product;
import enums.AddOns;

public class ShippingAction implements AddOnAction {

    @Override
    public double execute(Product product, double quantity) {
        double weight = product.getAddOns().getOrDefault(AddOns.Weight, 1.0);
        return product.getAddOns().getOrDefault(AddOns.ShippingPrice, 0.0) * weight * quantity;
    }
}
