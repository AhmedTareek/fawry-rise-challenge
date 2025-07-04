package addOnsActions;

import decorator.Product;
import enums.AddOns;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class AddOnProcessor {
    private final Map<AddOns, AddOnAction> actions = new EnumMap<>(AddOns.class);

    public AddOnProcessor() {
        actions.put(AddOns.ShippingPrice, new ShippingAction());
        actions.put(AddOns.BasePrice, new BaseAction());
    }

    public HashMap<AddOns, Double> processAddOns(Product product, double quantity) {
        HashMap<AddOns, Double> results = new HashMap<>();
        for (AddOns addOn : product.getAddOns().keySet()) {
            AddOnAction action = actions.get(addOn);
            if (action != null) {
                results.put(addOn, action.execute(product, quantity));
            }
        }
        results.put(AddOns.Weight, product.getAddOns().getOrDefault(AddOns.Weight, 1.0) * quantity);
        return results;
    }
}