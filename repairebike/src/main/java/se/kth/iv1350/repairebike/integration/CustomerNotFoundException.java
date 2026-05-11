package se.kth.iv1350.repairebike.integration;

/**
 * Thrown when a search is made for a phone number
 * that does not exist in the customer registry.
 */
public class CustomerNotFoundException extends Exception {

    /**
     * Creates a new instance with the phone number that was not found.
     *
     * @param phoneNumber The phone number that could not be found.
     */
    public CustomerNotFoundException(String phoneNumber) {
        super("No customer found with phone number: " + phoneNumber);
    }
}