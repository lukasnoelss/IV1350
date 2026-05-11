package se.kth.iv1350.repairebike.model;

import se.kth.iv1350.repairebike.dto.RepairOrderDTO;

/**
 * Observer interface for repair order updates.
 * Implemented by all classes that want to be notified
 * when a repair order changes.
 */
public interface RepairOrderObserver {

    /**
     * Called when a repair order has been updated.
     *
     * @param repairOrder The updated repair order.
     */
    void repairOrderUpdated(RepairOrderDTO repairOrder);
}