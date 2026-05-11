package se.kth.iv1350.repairebike.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.repairebike.dto.CustomerDTO;
import se.kth.iv1350.repairebike.integration.CustomerRegistry;
import se.kth.iv1350.repairebike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairebike.integration.Printer;
import se.kth.iv1350.repairebike.dto.RepairOrderDTO;
import java.util.List;
import se.kth.iv1350.repairebike.integration.CustomerNotFoundException;

/**
 * Tests for the Controller class.
 */
public class ControllerTest {
    private Controller controller;

    @BeforeEach
    public void setUp() {
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        Printer printer = new Printer();
        controller = new Controller(
                customerRegistry, repairOrderRegistry, printer);
    }

    @Test
    public void testFindExistingCustomerReturnsCustomer()
            throws CustomerNotFoundException {
        CustomerDTO result = controller.findCustomer("0701234567");
        assertNotNull(result);
    }

    @Test
    public void testFindNonExistingCustomerThrowsException() {
        assertThrows(CustomerNotFoundException.class, () -> {
            controller.findCustomer("0000000000");
        });
    }

    @Test
    public void testCreateRepairOrderIncreasesOrderCount() {
        controller.createRepairOrder(
                "Battery broken", "0701234567", "BIKE-001");
        List<RepairOrderDTO> orders = controller.findAllRepairOrders();
        assertEquals(1, orders.size());
    }

    @Test
    public void testAddDiagnosticResultStoresResult() {
        controller.createRepairOrder(
                "Battery broken", "0701234567", "BIKE-001");
        controller.addDiagnosticResult(1,
                "Battery cells degraded");
        RepairOrderDTO order = controller.findRepairOrder("0701234567");
        assertTrue(order.getDiagnosticResults()
                .contains("Battery cells degraded"));
    }

    @Test
    public void testAddRepairTaskStoresTask() {
        controller.createRepairOrder(
                "Battery broken", "0701234567", "BIKE-001");
        controller.addRepairTask(1, "Replace battery pack");
        RepairOrderDTO order = controller.findRepairOrder("0701234567");
        assertTrue(order.getRepairTasks()
                .contains("Replace battery pack"));
    }

    @Test
    public void testAcceptRepairOrderChangesStateToAccepted() {
        controller.createRepairOrder(
                "Battery broken", "0701234567", "BIKE-001");
        controller.acceptRepairOrder(1);
        RepairOrderDTO order = controller.findRepairOrder("0701234567");
        assertEquals("Accepted", order.getState());
    }

    @Test
    public void testFindRepairOrderReturnsNullIfNotFound() {
        RepairOrderDTO result = controller.findRepairOrder("0000000000");
        assertNull(result);
    }
}