package se.kth.iv1350.repairebike.integration;

import se.kth.iv1350.repairebike.model.RepairOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all communication with the repair order database.
 */
public class RepairOrderRegistry {
    private List<RepairOrder> repairOrders = new ArrayList<>();

    /**
     * Stores a new repair order.
     * 
     * @param repairOrder The repair order to store.
     */
    public void storeRepairOrder(RepairOrder repairOrder) {
        repairOrders.add(repairOrder);
    }

    /**
     * Finds all repair orders.
     * 
     * @return A list of all repair orders.
     */
    public List<RepairOrder> findAllRepairOrders() {
        return repairOrders;
    }

    /**
     * Finds a repair order by its id.
     * Returns null if not found.
     *
     * @param repairOrderId The id to search for.
     * @return The found repair order, or null if not found.
     */
    public RepairOrder findRepairOrderById(int repairOrderId) {
        for (RepairOrder order : repairOrders) {
            if (order.getId() == repairOrderId) {
                return order;
            }
        }
        return null;
    }

    /**
     * Finds a repair order by customer phone number.
     * Returns null if not found.
     *
     * @param phoneNumber The phone number to search for.
     * @return The found repair order, or null if not found.
     */
    public RepairOrder findRepairOrderByPhoneNumber(
            String phoneNumber) {
        for (RepairOrder order : repairOrders) {
            if (order.getCustomerPhone().equals(phoneNumber)) {
                return order;
            }
        }
        return null;
    }

    /**
     * Updates an existing repair order in the registry.
     *
     * @param updatedRepairOrder The updated repair order.
     */
    public void updateRepairOrder(RepairOrder updatedRepairOrder) {
        for (int i = 0; i < repairOrders.size(); i++) {
            if (repairOrders.get(i).getId() == updatedRepairOrder.getId()) {
                repairOrders.set(i, updatedRepairOrder);
                return;
            }
        }
    }

}
