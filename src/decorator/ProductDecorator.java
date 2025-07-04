package decorator;

import java.util.HashMap;
import enums.AddOns;

public abstract class ProductDecorator implements Product {
    protected final Product product;

    public ProductDecorator(Product product) {
        this.product = product;
    }

    @Override
    public HashMap<AddOns, Double> getAddOns() {
        return product.getAddOns();
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public double getBasePrice() {
        return product.getBasePrice();
    }

    @Override
    public double getQuantity() {
        return product.getQuantity();
    }

    @Override
    public boolean setQuantity(double quantity) {
        return product.setQuantity(quantity);
    }
}
