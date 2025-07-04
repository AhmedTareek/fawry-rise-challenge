package decorator;

import shipping.Shippable;
import enums.AddOns;

public class ShippingDecorator extends ProductDecorator implements Shippable {
    private final double shippingCostByKiloGram;
    private final double weight;

    // this is used for items sold by unit, like books or electronics
    // and weight here is the units weight in kilograms
    public ShippingDecorator(Product product, double shippingCostByKiloGram, double weight) {
        super(product);
        this.shippingCostByKiloGram = shippingCostByKiloGram;
        this.weight = weight;
        product.getAddOns().put(AddOns.Weight, weight);
        product.getAddOns().put(AddOns.ShippingPrice, shippingCostByKiloGram);
    }

    // this is used for items sold by weight, like fruits or vegetables
    public ShippingDecorator(Product product, double shippingCostByKiloGram) {
        super(product);
        this.shippingCostByKiloGram = shippingCostByKiloGram;
        this.weight = 1.0;
        product.getAddOns().put(AddOns.ShippingPrice, shippingCostByKiloGram);
        product.getAddOns().put(AddOns.Weight, weight);
    }

    public double getShippingCostByKiloGram() {
        return shippingCostByKiloGram * weight;
    }

    @Override
    public String toString() {
        return product.toString() + " with shipping cost: " + getShippingCostByKiloGram();
    }

    @Override
    public double getWeight() {
        return weight;
    }


}
