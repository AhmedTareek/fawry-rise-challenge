import addOnsActions.AddOnProcessor;
import decorator.Product;
import decorator.ProductUtils;
import enums.AddOns;
import shipping.Shippable;
import shipping.ShippingService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart {
    private final List<CartItem<Product, Double>> products;
    private double totalShippingCost = 0;
    private double totalBasePrice = 0;
    private double totalWeight = 0;
    private final AddOnProcessor addOnProcessor = new AddOnProcessor();

    public Cart() {
        this.products = new ArrayList<>();
    }

    public boolean processCart(double customerBalance) {
        List<Shippable> shippableList = new ArrayList<>();
        StringBuilder checkoutSummary = new StringBuilder();
        if (products.isEmpty()) {
            System.out.println("Cart is empty. Please add products before checkout.");
            return false;
        }
        for (CartItem<Product, Double> pair : products) {
            Product product = pair.first();
            double quantity = pair.second();
            if (ProductUtils.isExpired(product)) {
                System.out.println("Product " + product.getName() + " is expired and cannot be checked out.");
                return false;
            }
            HashMap<AddOns, Double> itemCheckoutInfo = addOnProcessor.processAddOns(product, quantity);
            if (itemCheckoutInfo.containsKey(AddOns.ShippingPrice)) {
                shippableList.add(ProductUtils.getShippingDecorator(product));
            }
            totalBasePrice += itemCheckoutInfo.get(AddOns.BasePrice);
            totalShippingCost += itemCheckoutInfo.getOrDefault(AddOns.ShippingPrice, 0.0);
            if (itemCheckoutInfo.containsKey(AddOns.ShippingPrice)) {
                totalWeight += itemCheckoutInfo.get(AddOns.Weight);
            }
            if (totalBasePrice + totalShippingCost > customerBalance) {
                System.out.println("Insufficient balance for checkout.");
                return false;
            }
            checkoutSummary.append(printItemCheckoutInfo(itemCheckoutInfo, quantity, product) + "\n");
        }
        System.out.println(checkoutSummary);
        System.out.println("-----------------------------------");
        System.out.printf("Total Base Price: %.2f EGP\n", totalBasePrice);
        if (totalShippingCost > 0) {
            System.out.printf("Total Shipping Cost: %.2f EGP\n", totalShippingCost);
        }
        if (totalWeight > 0) {
            System.out.printf("Total Weight: %.4f Kg\n", totalWeight);
        }
        System.out.printf("Total Amount: %.2f EGP\n", totalBasePrice + totalShippingCost);
        System.out.println("-----------------------------------");
        System.out.println("Paid Amount: " + customerBalance + " EGP");
        System.out.println("Change: " + (customerBalance - (totalBasePrice + totalShippingCost)) + " EGP");
        // send shippableList for shipping
        ShippingService.shipItems(shippableList);
        return true;
    }

    private String printItemCheckoutInfo(HashMap<AddOns, Double> itemCheckoutInfo, double quantity, Product product) {
        StringBuilder result = new StringBuilder();
        result.append(product.getName()).append(" X").append(quantity).append("\n");
        for (AddOns addOn : AddOns.values()) {
            if (itemCheckoutInfo.containsKey(addOn)) {
                double value = itemCheckoutInfo.get(addOn);
                switch (addOn) {
                    case BasePrice:
                        result.append(String.format("Price: %.2f EGP\n", value));
                        break;
                    case ShippingPrice:
                        result.append(String.format("Shipping Fee: %.2f EGP\n", value));
                        result.append(String.format("Weight: %.4f Kg\n", itemCheckoutInfo.get(AddOns.Weight)));
                        break;
                    default:
                        break;
                }
            }
        }
        return result.toString();
    }

    /**
     * The quantity represents the amount of the product to be added to the cart.
     * For items sold by weight (e.g., fruits or vegetables), it reflects the weight.
     * For items sold by unit (e.g., electronics or books), it reflects the number of units.
     */
    public boolean addProduct(Product product, double quantity) {
        if (product.getQuantity() < quantity) {
            System.out.println("Not enough stock for product: " + product.getName());
            return false; // Not enough stock.
        }
        product.setQuantity(product.getQuantity() - quantity);
        products.add(new CartItem<>(product, quantity));
        return true;
    }
}


record CartItem<T, U>(T first, U second) {
}

