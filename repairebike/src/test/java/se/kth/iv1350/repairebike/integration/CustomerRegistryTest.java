package se.kth.iv1350.repairebike.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.repairebike.dto.CustomerDTO;

/**
 * Tests for the CustomerRegistry class.
 */
public class CustomerRegistryTest {
    private CustomerRegistry registry;

    @BeforeEach
    public void setUp() {
        registry = new CustomerRegistry();
    }

    @Test
    public void testFindExistingCustomerReturnsCustomer() {
        CustomerDTO result = registry.findCustomer("0701234567");
        assertNotNull(result);
    }

    @Test
    public void testFindNonExistingCustomerReturnsNull() {
        CustomerDTO result = registry.findCustomer("0000000000");
        assertNull(result);
    }
}