package se.kth.iv1350.repairebike.startup;

import se.kth.iv1350.repairebike.controller.Controller;
import se.kth.iv1350.repairebike.integration.CustomerRegistry;
import se.kth.iv1350.repairebike.integration.Printer;
import se.kth.iv1350.repairebike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairebike.view.RepairOrderLogger;
import se.kth.iv1350.repairebike.view.RepairOrderView;
import se.kth.iv1350.repairebike.view.View;

/**
 * Contains the main method. Starts the application.
 */
public class Main {

    /**
     * The application's entry point.
     *
     * @param args The command line arguments, not used.
     */
    public static void main(String[] args) {
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry repairOrderRegistry = RepairOrderRegistry.getInstance();
        Printer printer = new Printer();

        Controller controller = new Controller(
                customerRegistry, repairOrderRegistry, printer);

        controller.addObserver(new RepairOrderView());
        controller.addObserver(new RepairOrderLogger("repairorder.log"));

        View view = new View(controller);
        view.runFakeExecution();
    }
}