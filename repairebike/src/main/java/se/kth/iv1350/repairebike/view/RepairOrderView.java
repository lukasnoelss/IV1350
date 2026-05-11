package se.kth.iv1350.repairebike.view;

import se.kth.iv1350.repairebike.dto.RepairOrderDTO;
import se.kth.iv1350.repairebike.model.RepairOrderObserver;

/**
 * Displays repair order updates to System.out.
 * Notifies technicians and receptionists when a
 * repair order has been updated.
 */
public class RepairOrderView implements RepairOrderObserver {

    /**
     * Called when a repair order has been updated.
     * Prints the updated repair order to System.out.
     *
     * @param repairOrder The updated repair order.
     */
    @Override
    public void repairOrderUpdated(RepairOrderDTO repairOrder) {
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
}