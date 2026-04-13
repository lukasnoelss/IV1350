package se.kth.iv1350.repairebike.integration;

import se.kth.iv1350.repairebike.dto.CustomerDTO;

/**
 * Handles all communication with the customer database.
 */
public class CustomerRegistry {
    /**
     * Finds a customer by phone number.
     * Returns null if no customer is found.
     *
     * @param phoneNumber The phone number to search for.
     * @return The found customer, or null if not found.
     */
    public CustomerDTO findCustomer(String phoneNumber) {
        if ("0701234567".equals(phoneNumber)) {
            return new CustomerDTO("Lukas Noel",
                    "lukas@gmail.com",
                    "BIKE-001");
        }
        return null;
    }
}
