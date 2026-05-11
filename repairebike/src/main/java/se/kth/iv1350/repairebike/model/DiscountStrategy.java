package se.kth.iv1350.repairebike.model;

/**
 * Strategy interface for discount calculation.
 * Implementations define different discount policies
 * that can be applied to a repair order price.
 */
public interface DiscountStrategy {

    /**
     * Calculates the discounted price.
     *
     * @param price The original price.
     * @return The price after discount.
     */
    double getDiscount(double price);
}