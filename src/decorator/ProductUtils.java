package decorator;

public class ProductUtils {

    public static boolean isShippable(Product product) {
        if (product == null) {
            return false;
        }
        return product instanceof ShippingDecorator || (product instanceof ProductDecorator &&
                isShippable(((ProductDecorator) product).product));
    }

    public static boolean isExpirable(Product product) {
        return product instanceof ExpirationDecorator ||
                (product instanceof ProductDecorator &&
                        isExpirable(((ProductDecorator) product).product));
    }

    public static boolean isExpired(Product product) {
        if (product instanceof ExpirationDecorator) {
            return ((ExpirationDecorator) product).isExpired();
        } else if (product instanceof ProductDecorator) {
            return isExpired(((ProductDecorator) product).product);
        }
        return false;
    }

    public static ShippingDecorator getShippingDecorator(Product product) {
        if (product instanceof ShippingDecorator) {
            return (ShippingDecorator) product;
        } else if (product instanceof ProductDecorator) {
            return getShippingDecorator(((ProductDecorator) product).product);
        }
        return null;
    }

    public static ExpirationDecorator getExpirationDecorator(Product product) {
        if (product instanceof ExpirationDecorator) {
            return (ExpirationDecorator) product;
        } else if (product instanceof ProductDecorator) {
            return getExpirationDecorator(((ProductDecorator) product).product);
        }
        return null;
    }

}
