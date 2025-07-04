package addOnsActions;

import decorator.Product;
import enums.AddOns;

public class BaseAction implements AddOnAction {

    @Override
    public double execute(Product product, double quantity) {
        double basePrice = product.getAddOns().getOrDefault(AddOns.BasePrice, 0.0);
        return basePrice * quantity;
    }
}
