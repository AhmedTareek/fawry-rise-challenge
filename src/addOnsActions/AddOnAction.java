package addOnsActions;

import decorator.Product;

public interface AddOnAction {
    double execute(Product product, double quantity);
}
