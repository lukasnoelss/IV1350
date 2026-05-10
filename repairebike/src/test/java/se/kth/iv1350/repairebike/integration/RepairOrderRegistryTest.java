package se.kth.iv1350.repairebike.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.repairebike.dto.RepairOrderDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests for the RepairOrderRegistry class.
 */
public class RepairOrderRegistryTest {
    private RepairOrderRegistry registry;
    private RepairOrderDTO order;

    @BeforeEach
    public void setUp() {
        registry = new RepairOrderRegistry();
        order = new RepairOrderDTO(1, "0701234567",
                "BIKE-001", "Battery broken",
                "NewlyCreated", new ArrayList<>(),
                new ArrayList<>());
    }

    @Test
    public void testStoreRepairOrderIncreasesSize() {
        registry.storeRepairOrder(order);
        List<RepairOrderDTO> orders = registry.findAllRepairOrders();
        assertEquals(1, orders.size());
    }

    @Test
    public void testFindRepairOrderByIdReturnsCorrectOrder() {
        registry.storeRepairOrder(order);
        RepairOrderDTO found = registry.findRepairOrderById(1);
        assertEquals(1, found.getId());
    }

    @Test
    public void testFindRepairOrderByIdReturnsNullIfNotFound() {
        RepairOrderDTO found = registry.findRepairOrderById(99);
        assertNull(found);
    }

    @Test
    public void testFindRepairOrderByPhoneReturnsCorrectOrder() {
        registry.storeRepairOrder(order);
        RepairOrderDTO found = registry.findRepairOrderByPhoneNumber("0701234567");
        assertNotNull(found);
    }

    @Test
    public void testUpdateRepairOrderUpdatesState() {
        registry.storeRepairOrder(order);
        RepairOrderDTO updated = new RepairOrderDTO(1,
                "0701234567", "BIKE-001",
                "Battery broken", "Accepted",
                new ArrayList<>(), new ArrayList<>());
        registry.updateRepairOrder(updated);
        RepairOrderDTO found = registry.findRepairOrderById(1);
        assertEquals("Accepted", found.getState());
    }

    @Test
    public void testFindRepairOrderByPhoneReturnsNullIfNotFound() {
        RepairOrderDTO found = registry.findRepairOrderByPhoneNumber("0000000000");
        assertNull(found);
    }
}