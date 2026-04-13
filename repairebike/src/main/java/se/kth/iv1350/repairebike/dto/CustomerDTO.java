package se.kth.iv1350.repairebike.dto;

/**
 * Carries customer data between layers.
 */

public class CustomerDTO {
    private String name;
    private String email;
    private String bikeSerialNo;

    /**
     * Creates a new CustomerDTO.
     *
     * @param name         The customer's name.
     * @param email        The customer's email.
     * @param bikeSerialNo The customer's bike serial number.
     */

    public CustomerDTO(String name, String email, String bikeSerialNo) {
        this.name = name;
        this.email = email;
        this.bikeSerialNo = bikeSerialNo;
    }

    /**
     * Returns the customer's name.
     *
     * @return The customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the customer's email.
     *
     * @return The customer's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the customer's bike serial number.
     *
     * @return The customer's bike serial number.
     */
    public String getBikeSerialNo() {
        return bikeSerialNo;
    }
}