package se.kth.iv1350.repairebike.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a repair order for an electric bike.
 */
public class RepairOrder {
    private int id;
    private LocalDate date;
    private LocalDate estimatedCompletionDate;
    private String customerPhone;
    private String bikeSerialNo;
    private String customersProblemDescription;
    private String state;
    private List<String> diagnosticResults;
    private List<String> repairTasks;

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
    }

    /**
     * Adds a diagnostic result to this repair order.
     *
     * @param diagTaskResult The diagnostic result to add.
     */
    public void addDiagnosticResult(String diagTaskResult) {
        diagnosticResults.add(diagTaskResult);
    }

    /**
     * Adds a repair task to this repair order.
     *
     * @param repairTask The repair task to add.
     */
    public void addRepairTask(String repairTask) {
        repairTasks.add(repairTask);
    }

    /**
     * Changes the state of this repair order to Accepted.
     */
    public void accept() {
        this.state = "Accepted";
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
        return diagnosticResults;
    }

    /**
     * Returns the list of repair tasks.
     *
     * @return The list of repair tasks.
     */
    public List<String> getRepairTasks() {
        return repairTasks;
    }
}
