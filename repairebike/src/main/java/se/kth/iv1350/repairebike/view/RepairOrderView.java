package se.kth.iv1350.repairebike.view;

import se.kth.iv1350.repairebike.dto.RepairOrderDTO;

/**
 * Displays repair order updates to System.out.
 * Notifies technicians and receptionists when a
 * repair order has been updated.
 */
public class RepairOrderView extends RepairOrderObserverHandler {

        /**
         * Prints the updated repair order to System.out.
         *
         * @param repairOrder The updated repair order.
         * @throws Exception if printing fails.
         */
        @Override
        protected void doHandleRepairOrderUpdate(RepairOrderDTO repairOrder)
                        throws Exception {
                System.out.println("=== REPAIR ORDER UPDATE ===");
                System.out.println("ID: " + repairOrder.getId());
                System.out.println("State: " + repairOrder.getState());
                System.out.println("Phone: " + repairOrder.getCustomerPhone());
                System.out.println("Bike: " + repairOrder.getBikeSerialNo());
                System.out.println("Problem: "
                                + repairOrder.getCustomersProblemDescription());
                System.out.println("Diagnostic results: "
                                + repairOrder.getDiagnosticResults());
                System.out.println("Repair tasks: "
                                + repairOrder.getRepairTasks());
                System.out.println("===========================");
        }

        /**
         * Handles errors that occur when printing a repair order update.
         * Prints an error message to System.out.
         *
         * @param e The exception that was thrown.
         */
        @Override
        protected void handleErrors(Exception e) {
                System.out.println("Could not display repair order update: "
                                + e.getMessage());
        }
}