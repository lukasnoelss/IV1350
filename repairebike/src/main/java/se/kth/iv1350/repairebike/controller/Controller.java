package se.kth.iv1350.repairebike.controller;

import se.kth.iv1350.repairebike.dto.RepairOrderDTO;
import se.kth.iv1350.repairebike.dto.CustomerDTO;
import se.kth.iv1350.repairebike.integration.CustomerNotFoundException;
import se.kth.iv1350.repairebike.integration.CustomerRegistry;
import se.kth.iv1350.repairebike.integration.DatabaseFailureException;
import se.kth.iv1350.repairebike.integration.Printer;
import se.kth.iv1350.repairebike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairebike.model.LoyaltyDiscount;
import se.kth.iv1350.repairebike.model.RepairOrder;
import se.kth.iv1350.repairebike.model.RepairOrderObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * The application's controller. Handles all calls from the view
 * and delegates work to the model and integration layers.
 */
public class Controller {
        private CustomerRegistry customerRegistry;
        private RepairOrderRegistry repairOrderRegistry;
        private Printer printer;
        private List<RepairOrderObserver> observers;
        private int nextRepairOrderId = 1;

        /**
         * Creates a new Controller.
         *
         * @param customerRegistry    The registry used to find customers.
         * @param repairOrderRegistry The registry used to handle repair orders.
         * @param printer             The printer used to print repair orders.
         */
        public Controller(CustomerRegistry customerRegistry,
                        RepairOrderRegistry repairOrderRegistry,
                        Printer printer) {
                this.customerRegistry = customerRegistry;
                this.repairOrderRegistry = repairOrderRegistry;
                this.printer = printer;
                this.observers = new ArrayList<>();
        }

        /**
         * Adds an observer to be notified when a repair order is updated.
         *
         * @param observer The observer to add.
         */
        public void addObserver(RepairOrderObserver observer) {
                observers.add(observer);
        }

        /**
         * Finds a customer by phone number.
         *
         * @param phoneNumber The phone number to search for.
         * @return The found customer.
         * @throws CustomerNotFoundException If no customer with the
         *                                   given phone number exists.
         */
        public CustomerDTO findCustomer(String phoneNumber)
                        throws CustomerNotFoundException {
                return customerRegistry.findCustomer(phoneNumber);
        }

        /**
         * Creates a new repair order and stores it.
         * Applies a loyalty discount if this is the customer's
         * third or multiple-of-three repair order.
         *
         * @param problemDescr  The customer's problem description.
         * @param customerPhone The customer's phone number.
         * @param bikeSerialNo  The bike's serial number.
         * @return The price of the repair order after any discount.
         */
        public double createRepairOrder(String problemDescr,
                        String customerPhone,
                        String bikeSerialNo) {
                RepairOrder repairOrder = new RepairOrder(
                                nextRepairOrderId++, problemDescr,
                                customerPhone, bikeSerialNo);
                repairOrderRegistry.storeRepairOrder(repairOrder.toDTO());
                int orderCount = repairOrderRegistry
                                .countRepairOrdersByPhone(customerPhone);
                if (orderCount % 3 == 0) {
                        repairOrder.setDiscountStrategy(new LoyaltyDiscount());
                }
                addObserversTo(repairOrder);
                return repairOrder.getPrice();
        }

        /**
         * Returns all repair orders.
         *
         * @return A list of all repair orders.
         */
        public List<RepairOrderDTO> findAllRepairOrders() {
                return repairOrderRegistry.findAllRepairOrders();
        }

        /**
         * Adds a diagnostic result to the specified repair order.
         *
         * @param repairOrderId  The id of the repair order.
         * @param diagTaskResult The diagnostic result to add.
         * @throws DatabaseFailureException If the database cannot be reached.
         */
        public void addDiagnosticResult(int repairOrderId,
                        String diagTaskResult) throws DatabaseFailureException {
                RepairOrderDTO orderDTO = repairOrderRegistry
                                .findRepairOrderById(repairOrderId);
                RepairOrder repairOrder = new RepairOrder(orderDTO);
                addObserversTo(repairOrder);
                repairOrder.addDiagnosticResult(diagTaskResult);
                repairOrderRegistry.updateRepairOrder(repairOrder.toDTO());
        }

        /**
         * Adds a repair task to the specified repair order.
         *
         * @param repairOrderId The id of the repair order.
         * @param repairTask    The repair task to add.
         * @throws DatabaseFailureException If the database cannot be reached.
         */
        public void addRepairTask(int repairOrderId,
                        String repairTask) throws DatabaseFailureException {
                RepairOrderDTO orderDTO = repairOrderRegistry
                                .findRepairOrderById(repairOrderId);
                RepairOrder repairOrder = new RepairOrder(orderDTO);
                addObserversTo(repairOrder);
                repairOrder.addRepairTask(repairTask);
                repairOrderRegistry.updateRepairOrder(repairOrder.toDTO());
        }

        /**
         * Finds a repair order by customer phone number.
         *
         * @param phoneNumber The phone number to search for.
         * @return The found repair order, or null if not found.
         */
        public RepairOrderDTO findRepairOrder(String phoneNumber) {
                return repairOrderRegistry
                                .findRepairOrderByPhoneNumber(phoneNumber);
        }

        /**
         * Accepts the specified repair order and prints it.
         *
         * @param repairOrderId The id of the repair order to accept.
         * @throws DatabaseFailureException If the database cannot be reached.
         */
        public void acceptRepairOrder(int repairOrderId) throws DatabaseFailureException {
                RepairOrderDTO orderDTO = repairOrderRegistry
                                .findRepairOrderById(repairOrderId);
                RepairOrder repairOrder = new RepairOrder(orderDTO);
                addObserversTo(repairOrder);
                repairOrder.accept();
                RepairOrderDTO updatedDTO = repairOrder.toDTO();
                repairOrderRegistry.updateRepairOrder(updatedDTO);
                printer.printRepairOrder(updatedDTO);
        }

        /**
         * Adds all registered observers to the given repair order.
         *
         * @param repairOrder The repair order to add observers to.
         */
        private void addObserversTo(RepairOrder repairOrder) {
                for (RepairOrderObserver observer : observers) {
                        repairOrder.addObserver(observer);
                }
        }
}