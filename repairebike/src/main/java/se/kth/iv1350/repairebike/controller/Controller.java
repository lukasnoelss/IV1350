package se.kth.iv1350.repairebike.controller;

import se.kth.iv1350.repairebike.dto.RepairOrderDTO;
import se.kth.iv1350.repairebike.dto.CustomerDTO;
import se.kth.iv1350.repairebike.integration.CustomerRegistry;
import se.kth.iv1350.repairebike.integration.Printer;
import se.kth.iv1350.repairebike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairebike.model.RepairOrder;
import java.util.List;

/**
 * The application's controller. Handles all calls from the view
 * and delegates work to the model and integration layers.
 */
public class Controller {
    private CustomerRegistry customerRegistry;
    private RepairOrderRegistry repairOrderRegistry;
    private Printer printer;
    private int nextRepairOrderId = 1;

    /**
     * Creates a new Controller.
     *
     * @param customerRegistry    The registry used to find customers.
     * @param repairOrderRegistry The registry used to handle repair orders.
     * @param printer             The printer used to print repair orders.
     */
    public Controller(CustomerRegistry customerRegistry,
            RepairOrderRegistry repairOrderRegistry,
            Printer printer) {
        this.customerRegistry = customerRegistry;
        this.repairOrderRegistry = repairOrderRegistry;
        this.printer = printer;
    }

    /**
     * Finds a customer by phone number.
     *
     * @param phoneNumber The phone number to search for.
     * @return The found customer, or null if not found.
     */
    public CustomerDTO findCustomer(String phoneNumber) {
        return customerRegistry.findCustomer(phoneNumber);
    }

    /**
     * Creates a new repair order and stores it.
     *
     * @param problemDescr  The customer's problem description.
     * @param customerPhone The customer's phone number.
     * @param bikeSerialNo  The bike's serial number.
     */
    public void createRepairOrder(String problemDescr,
            String customerPhone,
            String bikeSerialNo) {
        RepairOrder repairOrder = new RepairOrder(
                nextRepairOrderId++, problemDescr,
                customerPhone, bikeSerialNo);
        repairOrderRegistry.storeRepairOrder(
                repairOrder.toDTO());
    }

    /**
     * Returns all repair orders.
     *
     * @return A list of all repair orders.
     */
    public List<RepairOrderDTO> findAllRepairOrders() {
        return repairOrderRegistry.findAllRepairOrders();
    }

    /**
     * Adds a diagnostic result to the specified repair order.
     *
     * @param repairOrderId  The id of the repair order.
     * @param diagTaskResult The diagnostic result to add.
     */
    public void addDiagnosticResult(int repairOrderId,
            String diagTaskResult) {
        RepairOrderDTO orderDTO = repairOrderRegistry
                .findRepairOrderById(repairOrderId);
        RepairOrder repairOrder = new RepairOrder(orderDTO);
        repairOrder.addDiagnosticResult(diagTaskResult);
        repairOrderRegistry.updateRepairOrder(
                repairOrder.toDTO());
    }

    /**
     * Adds a repair task to the specified repair order.
     *
     * @param repairOrderId The id of the repair order.
     * @param repairTask    The repair task to add.
     */
    public void addRepairTask(int repairOrderId,
            String repairTask) {
        RepairOrderDTO orderDTO = repairOrderRegistry
                .findRepairOrderById(repairOrderId);
        RepairOrder repairOrder = new RepairOrder(orderDTO);
        repairOrder.addRepairTask(repairTask);
        repairOrderRegistry.updateRepairOrder(
                repairOrder.toDTO());
    }

    /**
     * Finds a repair order by customer phone number.
     *
     * @param phoneNumber The phone number to search for.
     * @return The found repair order, or null if not found.
     */
    public RepairOrderDTO findRepairOrder(String phoneNumber) {
        return repairOrderRegistry
                .findRepairOrderByPhoneNumber(phoneNumber);
    }

    /**
     * Accepts the specified repair order and prints it.
     *
     * @param repairOrderId The id of the repair order to accept.
     */
    public void acceptRepairOrder(int repairOrderId) {
        RepairOrderDTO orderDTO = repairOrderRegistry
                .findRepairOrderById(repairOrderId);
        RepairOrder repairOrder = new RepairOrder(orderDTO);
        repairOrder.accept();
        RepairOrderDTO updatedDTO = repairOrder.toDTO();
        repairOrderRegistry.updateRepairOrder(updatedDTO);
        printer.printRepairOrder(updatedDTO);
    }
}