package decorator;

import java.util.Date;

public class ExpirationDecorator extends ProductDecorator {
    private final Date expirationDate;

    public ExpirationDecorator(Product product, Date expirationDate) {
        super(product);
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return product.toString() + " (Expires on: " + expirationDate + ")";
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public boolean isExpired() {
        Date currentDate = new Date();
        return currentDate.after(expirationDate);

    }
}
