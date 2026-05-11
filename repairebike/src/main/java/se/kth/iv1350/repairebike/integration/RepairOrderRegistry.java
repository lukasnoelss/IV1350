package se.kth.iv1350.repairebike.integration;

import se.kth.iv1350.repairebike.dto.RepairOrderDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all communication with the repair order database.
 * Implemented as a Singleton to ensure only one instance exists,
 * guaranteeing a single consistent list of repair orders.
 */
public class RepairOrderRegistry {
    private static RepairOrderRegistry instance;
    private List<RepairOrderDTO> repairOrders = new ArrayList<>();

    /**
     * Private constructor to prevent external instantiation.
     */
    private RepairOrderRegistry() {
    }

    /**
     * Returns the single instance of RepairOrderRegistry.
     * Creates it if it does not yet exist.
     *
     * @return The single RepairOrderRegistry instance.
     */
    public static RepairOrderRegistry getInstance() {
        if (instance == null) {
            instance = new RepairOrderRegistry();
        }
        return instance;
    }

    /**
     * Stores a new repair order.
     *
     * @param repairOrder The repair order to store.
     */
    public void storeRepairOrder(RepairOrderDTO repairOrder) {
        repairOrders.add(repairOrder);
    }

    /**
     * Finds all repair orders.
     *
     * @return A list of all repair orders.
     */
    public List<RepairOrderDTO> findAllRepairOrders() {
        return new ArrayList<>(repairOrders);
    }

    /**
     * Finds a repair order by its id.
     *
     * @param repairOrderId The id to search for.
     * @return The found repair order.
     * @throws DatabaseFailureException If the database cannot be called.
     */
    public RepairOrderDTO findRepairOrderById(int repairOrderId) {
        if (repairOrderId == 0) {
            throw new DatabaseFailureException(repairOrderId);
        }
        for (RepairOrderDTO order : repairOrders) {
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
    public RepairOrderDTO findRepairOrderByPhoneNumber(
            String phoneNumber) {
        for (RepairOrderDTO order : repairOrders) {
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
    public void updateRepairOrder(RepairOrderDTO updatedRepairOrder) {
        for (int i = 0; i < repairOrders.size(); i++) {
            if (repairOrders.get(i).getId() == updatedRepairOrder.getId()) {
                repairOrders.set(i, updatedRepairOrder);
                return;
            }
        }
    }

    /**
     * Resets the singleton instance. Used only for unit testing.
     */
    public static void resetInstance() {
        instance = null;
    }

    /**
     * Returns the number of repair orders for a given customer.
     *
     * @param customerPhone The customer's phone number.
     * @return The number of repair orders for that customer.
     */
    public int countRepairOrdersByPhone(String customerPhone) {
        int count = 0;
        for (RepairOrderDTO order : repairOrders) {
            if (order.getCustomerPhone().equals(customerPhone)) {
                count++;
            }
        }
        return count;
    }
}