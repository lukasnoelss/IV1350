package se.kth.iv1350.repairebike.integration;

import se.kth.iv1350.repairebike.model.RepairOrder;

public class Printer {
    /**
     * Prints a repair order to System.out.
     *
     * @param repairOrder The repair order to print.
     */
    public void printRepairOrder(RepairOrder repairOrder) {
        System.out.println("=== REPAIR ORDER ===");
        System.out.println("ID: " + repairOrder.getId());
        System.out.println("Phone: "
                + repairOrder.getCustomerPhone());
        System.out.println("Bike: "
                + repairOrder.getBikeSerialNo());
        System.out.println("Problem: "
                + repairOrder.getCustomersProblemDescription());
        System.out.println("State: " + repairOrder.getState());
        System.out.println("Diagnostic results: "
                + repairOrder.getDiagnosticResults());
        System.out.println("Repair tasks: "
                + repairOrder.getRepairTasks());
        System.out.println("====================");
    }
}
