package se.kth.iv1350.repairebike.view;

import se.kth.iv1350.repairebike.dto.RepairOrderDTO;
import se.kth.iv1350.repairebike.model.RepairOrderObserver;

/**
 * Abstract base class for repair order observers.
 * Uses the Template Method design pattern to define the skeleton
 * of the observer algorithm, deferring error handling and the
 * actual update logic to subclasses.
 */
public abstract class RepairOrderObserverHandler implements RepairOrderObserver {

    /**
     * Called when a repair order has been updated.
     * This is the template method entry point defined in the
     * RepairOrderObserver interface. Delegates to
     * handleRepairOrderUpdate() which wraps execution in a try-catch.
     *
     * @param repairOrder The updated repair order.
     */
    @Override
    public void repairOrderUpdated(RepairOrderDTO repairOrder) {
        handleRepairOrderUpdate(repairOrder);
    }

    private void handleRepairOrderUpdate(RepairOrderDTO repairOrder) {
        try {
            doHandleRepairOrderUpdate(repairOrder);
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    /**
     * Performs the actual handling of a repair order update.
     * Subclasses must implement this method to define what
     * happens when a repair order is updated.
     *
     * @param repairOrder The updated repair order.
     * @throws Exception if anything goes wrong during handling.
     */
    protected abstract void doHandleRepairOrderUpdate(RepairOrderDTO repairOrder)
            throws Exception;

    /**
     * Handles errors that occur during repair order update handling.
     * Subclasses must implement this to define how errors are reported.
     *
     * @param e The exception that was thrown.
     */
    protected abstract void handleErrors(Exception e);
}