package enums;

public enum AddOns {

    BasePrice("Base Price"),
    ShippingPrice("Shipping Price"),
    Weight("Weight");

    private final String displayName;

    AddOns(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }



}
