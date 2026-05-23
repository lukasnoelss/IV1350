package se.kth.iv1350.repairebike.view;

import se.kth.iv1350.repairebike.controller.Controller;
import se.kth.iv1350.repairebike.dto.CustomerDTO;
import se.kth.iv1350.repairebike.dto.RepairOrderDTO;
import se.kth.iv1350.repairebike.integration.CustomerNotFoundException;
import se.kth.iv1350.repairebike.integration.DatabaseFailureException;
import se.kth.iv1350.repairebike.util.ErrorLogger;

import java.util.List;

/**
 * The application's view. Simulates user interaction with
 * hard-coded method calls to the controller.
 */
public class View {
    private Controller controller;
    private ErrorLogger logger;

    /**
     * Creates a new View.
     *
     * @param controller The controller used for all operations.
     */
    public View(Controller controller) {
        this.controller = controller;
        this.logger = new ErrorLogger("error.log");
    }

    /**
     * Simulates the entire Repair Electric Bike basic flow,
     * including exception handling for error scenarios.
     */
    public void runFakeExecution() {
        System.out.println("\n--- addDiagnosticResult ---");
        try {
            controller.addDiagnosticResult(1, "Battery cells degraded");
            System.out.println("Diagnostic result added.");
        } catch (DatabaseFailureException e) {
            System.out.println("ERROR: Could not reach database. Please try again later.");
            logger.log("Database failure when calling addDiagnosticResult", e);
        }

        System.out.println("\n--- addRepairTask ---");
        try {
            controller.addRepairTask(1, "Replace battery pack");
            System.out.println("Repair task added.");
        } catch (DatabaseFailureException e) {
            System.out.println("ERROR: Could not reach database. Please try again later.");
            logger.log("Database failure when calling addRepairTask", e);
        }

        System.out.println("\n--- findRepairOrder ---");
        RepairOrderDTO found = controller.findRepairOrder("0701234567");
        if (found != null) {
            System.out.println("Found order ID: "
                    + found.getId()
                    + ", State: " + found.getState());
        }

        System.out.println("\n--- acceptRepairOrder ---");
        try {
            controller.acceptRepairOrder(1);
        } catch (DatabaseFailureException e) {
            System.out.println("ERROR: Could not reach database. Please try again later.");
            logger.log("Database failure when calling acceptRepairOrder", e);
        }

        System.out.println("\n--- database failure simulation ---");
        try {
            controller.addDiagnosticResult(0, "This should fail");
        } catch (DatabaseFailureException e) {
            System.out.println("ERROR: Could not reach database. Please try again later.");
            logger.log("Database failure when calling addDiagnosticResult", e);
        }
    }
}