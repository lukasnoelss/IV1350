package se.kth.iv1350.repairebike.model;

/**
 * A discount strategy that applies no discount.
 * Used as the default strategy when no discount is applicable.
 */
public class NoDiscount implements DiscountStrategy {

    /**
     * Returns the original price unchanged.
     *
     * @param price The original price.
     * @return The original price with no discount applied.
     */
    @Override
    public double getDiscount(double price) {
        return price;
    }
}