package se.kth.iv1350.repairebike.view;

import se.kth.iv1350.repairebike.controller.Controller;
import se.kth.iv1350.repairebike.dto.CustomerDTO;
import se.kth.iv1350.repairebike.model.RepairOrder;
import java.util.List;

/**
 * The application's view. Simulates user interaction with
 * hard-coded method calls to the controller.
 */
public class View {
    private Controller controller;

    /**
     * Creates a new View.
     *
     * @param controller The controller used for all operations.
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * Simulates the entire Repair Electric Bike basic flow.
     */
    public void runFakeExecution() {
        System.out.println("--- findCustomer ---");
        CustomerDTO customer = controller.findCustomer("0701234567");
        if (customer != null) {
            System.out.println("Found customer: "
                    + customer.getName()
                    + ", " + customer.getEmail()
                    + ", bike: " + customer.getBikeSerialNo());
        }

        System.out.println("\n--- createRepairOrder ---");
        controller.createRepairOrder(
                "Battery not charging",
                "0701234567",
                "BIKE-001");
        System.out.println("Repair order created.");

        System.out.println("\n--- findAllRepairOrders ---");
        List<RepairOrder> orders = controller.findAllRepairOrders();
        for (RepairOrder order : orders) {
            System.out.println("Order ID: " + order.getId()
                    + ", State: " + order.getState());
        }

        System.out.println("\n--- addDiagnosticResult ---");
        controller.addDiagnosticResult(1,
                "Battery cells degraded");
        System.out.println("Diagnostic result added.");

        System.out.println("\n--- addRepairTask ---");
        controller.addRepairTask(1,
                "Replace battery pack");
        System.out.println("Repair task added.");

        System.out.println("\n--- findRepairOrder ---");
        RepairOrder found = controller.findRepairOrder("0701234567");
        if (found != null) {
            System.out.println("Found order ID: "
                    + found.getId()
                    + ", State: " + found.getState());
        }

        System.out.println("\n--- acceptRepairOrder ---");
        controller.acceptRepairOrder(1);
    }
}