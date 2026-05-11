package se.kth.iv1350.repairebike.model;

import se.kth.iv1350.repairebike.dto.RepairOrderDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a repair order for an electric bike.
 */
public class RepairOrder {
    private int id;
    private LocalDate date;
    private String customerPhone;
    private String bikeSerialNo;
    private String customersProblemDescription;
    private String state;
    private List<String> diagnosticResults;
    private List<String> repairTasks;
    private List<RepairOrderObserver> observers;
    private DiscountStrategy discountStrategy;
    private static final double BASE_PRICE = 500.0;

    /**
     * Creates a new RepairOrder with state NewlyCreated.
     *
     * @param id            The unique id of this repair order.
     * @param problemDescr  The customer's problem description.
     * @param customerPhone The customer's phone number.
     * @param bikeSerialNo  The bike's serial number.
     */
    public RepairOrder(int id, String problemDescr,
            String customerPhone, String bikeSerialNo) {
        this.id = id;
        this.date = LocalDate.now();
        this.customersProblemDescription = problemDescr;
        this.customerPhone = customerPhone;
        this.bikeSerialNo = bikeSerialNo;
        this.state = "NewlyCreated";
        this.diagnosticResults = new ArrayList<>();
        this.repairTasks = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.discountStrategy = new NoDiscount();
    }

    /**
     * Creates a RepairOrder from a RepairOrderDTO.
     * Used to restore a domain object for business logic.
     *
     * @param dto The DTO containing the repair order data.
     */
    public RepairOrder(RepairOrderDTO dto) {
        this.id = dto.getId();
        this.date = LocalDate.now();
        this.customerPhone = dto.getCustomerPhone();
        this.bikeSerialNo = dto.getBikeSerialNo();
        this.customersProblemDescription = dto.getCustomersProblemDescription();
        this.state = dto.getState();
        this.diagnosticResults = new ArrayList<>(dto.getDiagnosticResults());
        this.repairTasks = new ArrayList<>(dto.getRepairTasks());
        this.observers = new ArrayList<>();
        this.discountStrategy = new NoDiscount();
    }

    /**
     * Adds an observer to be notified when this repair order changes.
     *
     * @param observer The observer to add.
     */
    public void addObserver(RepairOrderObserver observer) {
        observers.add(observer);
    }

    /**
     * Sets the discount strategy for this repair order.
     *
     * @param discountStrategy The discount strategy to apply.
     */
    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    /**
     * Returns the price after applying the current discount strategy.
     *
     * @return The discounted price.
     */
    public double getPrice() {
        return discountStrategy.getDiscount(BASE_PRICE);
    }

    /**
     * Adds a diagnostic result to this repair order.
     *
     * @param diagTaskResult The diagnostic result to add.
     */
    public void addDiagnosticResult(String diagTaskResult) {
        diagnosticResults.add(diagTaskResult);
        notifyObservers();
    }

    /**
     * Adds a repair task to this repair order.
     *
     * @param repairTask The repair task to add.
     */
    public void addRepairTask(String repairTask) {
        repairTasks.add(repairTask);
        notifyObservers();
    }

    /**
     * Changes the state of this repair order to Accepted.
     */
    public void accept() {
        this.state = "Accepted";
        notifyObservers();
    }

    /**
     * Notifies all observers that this repair order has been updated.
     */
    private void notifyObservers() {
        RepairOrderDTO dto = toDTO();
        for (RepairOrderObserver observer : observers) {
            observer.repairOrderUpdated(dto);
        }
    }

    /**
     * Returns the repair order id.
     *
     * @return The repair order id.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the current state.
     *
     * @return The current state.
     */
    public String getState() {
        return state;
    }

    /**
     * Returns the customer's phone number.
     *
     * @return The customer's phone number.
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Returns the bike serial number.
     *
     * @return The bike serial number.
     */
    public String getBikeSerialNo() {
        return bikeSerialNo;
    }

    /**
     * Returns the problem description.
     *
     * @return The problem description.
     */
    public String getCustomersProblemDescription() {
        return customersProblemDescription;
    }

    /**
     * Returns the list of diagnostic results.
     *
     * @return The list of diagnostic results.
     */
    public List<String> getDiagnosticResults() {
        return new ArrayList<>(diagnosticResults);
    }

    /**
     * Returns the list of repair tasks.
     *
     * @return The list of repair tasks.
     */
    public List<String> getRepairTasks() {
        return new ArrayList<>(repairTasks);
    }

    /**
     * Converts this RepairOrder to a RepairOrderDTO.
     *
     * @return A DTO containing this repair order's data.
     */
    public RepairOrderDTO toDTO() {
        return new RepairOrderDTO(id, customerPhone,
                bikeSerialNo, customersProblemDescription,
                state, new ArrayList<>(diagnosticResults),
                new ArrayList<>(repairTasks));
    }
}