package se.kth.iv1350.repairebike.model;

/**
 * A discount strategy that gives 10% off every third repair order.
 * Rewards loyal customers who return for repeated repairs.
 */
public class LoyaltyDiscount implements DiscountStrategy {
    private static final double DISCOUNT_RATE = 0.10;

    /**
     * Calculates the discounted price by applying a 10% loyalty discount.
     *
     * @param price The original price.
     * @return The price after applying the 10% loyalty discount.
     */
    @Override
    public double getDiscount(double price) {
        return price * (1 - DISCOUNT_RATE);
    }
}