package se.kth.iv1350.repairebike.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the RepairOrder class.
 */
public class RepairOrderTest {
    private RepairOrder order;

    @BeforeEach
    public void setUp() {
        order = new RepairOrder(1, "Battery broken",
                "0701234567", "BIKE-001");
    }

    @Test
    public void testInitialStateIsNewlyCreated() {
        assertEquals("NewlyCreated", order.getState());
    }

    @Test
    public void testAcceptChangesStateToAccepted() {
        order.accept();
        assertEquals("Accepted", order.getState());
    }

    @Test
    public void testAddDiagnosticResultStoresResult() {
        order.addDiagnosticResult("Battery cells degraded");
        assertTrue(order.getDiagnosticResults()
                .contains("Battery cells degraded"));
    }

    @Test
    public void testAddRepairTaskStoresTask() {
        order.addRepairTask("Replace battery pack");
        assertTrue(order.getRepairTasks()
                .contains("Replace battery pack"));
    }
}