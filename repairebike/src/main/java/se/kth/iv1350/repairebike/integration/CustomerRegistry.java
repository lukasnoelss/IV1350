package se.kth.iv1350.repairebike.integration;

import se.kth.iv1350.repairebike.dto.CustomerDTO;

/**
 * Handles all communication with the customer database.
 */
public class CustomerRegistry {

    /**
     * Finds a customer by phone number.
     *
     * @param phoneNumber The phone number to search for.
     * @return The found customer.
     * @throws CustomerNotFoundException If no customer with the
     *                                   given phone number exists.
     */
    public CustomerDTO findCustomer(String phoneNumber)
            throws CustomerNotFoundException {
        if ("0701234567".equals(phoneNumber)) {
            return new CustomerDTO("Lukas Noel",
                    "lukas@gmail.com",
                    "BIKE-001");
        }
        throw new CustomerNotFoundException(phoneNumber);
    }
}