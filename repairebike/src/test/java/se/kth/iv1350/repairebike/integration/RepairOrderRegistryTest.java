package se.kth.iv1350.repairebike.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.repairebike.model.RepairOrder;
import java.util.List;

/**
 * Tests for the RepairOrderRegistry class.
 */
public class RepairOrderRegistryTest {
    private RepairOrderRegistry registry;
    private RepairOrder order;

    @BeforeEach
    public void setUp() {
        registry = new RepairOrderRegistry();
        order = new RepairOrder(1, "Battery broken",
                "0701234567", "BIKE-001");
    }

    @Test
    public void testStoreRepairOrderIncreasesSize() {
        registry.storeRepairOrder(order);
        List<RepairOrder> orders = registry.findAllRepairOrders();
        assertEquals(1, orders.size());
    }

    @Test
    public void testFindRepairOrderByIdReturnsCorrectOrder() {
        registry.storeRepairOrder(order);
        RepairOrder found = registry.findRepairOrderById(1);
        assertEquals(1, found.getId());
    }

    @Test
    public void testFindRepairOrderByIdReturnsNullIfNotFound() {
        RepairOrder found = registry.findRepairOrderById(99);
        assertNull(found);
    }

    @Test
    public void testFindRepairOrderByPhoneReturnsCorrectOrder() {
        registry.storeRepairOrder(order);
        RepairOrder found = registry.findRepairOrderByPhoneNumber("0701234567");
        assertNotNull(found);
    }

    @Test
    public void testUpdateRepairOrderUpdatesState() {
        registry.storeRepairOrder(order);
        order.accept();
        registry.updateRepairOrder(order);
        RepairOrder updated = registry.findRepairOrderById(1);
        assertEquals("Accepted", updated.getState());
    }
}