package se.kth.iv1350.repairebike.dto;

import java.util.List;

/**
 * Carries repair order data between layers.
 */
public class RepairOrderDTO {
    private int id;
    private String customerPhone;
    private String bikeSerialNo;
    private String customersProblemDescription;
    private String state;
    private List<String> diagnosticResults;
    private List<String> repairTasks;

    /**
     * Creates a new RepairOrderDTO.
     *
     * @param id                          The repair order id.
     * @param customerPhone               The customer's phone number.
     * @param bikeSerialNo                The bike's serial number.
     * @param customersProblemDescription The problem description.
     * @param state                       The current state.
     * @param diagnosticResults           The list of diagnostic results.
     * @param repairTasks                 The list of repair tasks.
     */
    public RepairOrderDTO(int id, String customerPhone,
            String bikeSerialNo,
            String customersProblemDescription,
            String state, List<String> diagnosticResults,
            List<String> repairTasks) {
        this.id = id;
        this.customerPhone = customerPhone;
        this.bikeSerialNo = bikeSerialNo;
        this.customersProblemDescription = customersProblemDescription;
        this.state = state;
        this.diagnosticResults = diagnosticResults;
        this.repairTasks = repairTasks;
    }

    /** @return The repair order id. */
    public int getId() {
        return id;
    }

    /** @return The customer's phone number. */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /** @return The bike's serial number. */
    public String getBikeSerialNo() {
        return bikeSerialNo;
    }

    /** @return The problem description. */
    public String getCustomersProblemDescription() {
        return customersProblemDescription;
    }

    /** @return The current state. */
    public String getState() {
        return state;
    }

    /** @return The list of diagnostic results. */
    public List<String> getDiagnosticResults() {
        return diagnosticResults;
    }

    /** @return The list of repair tasks. */
    public List<String> getRepairTasks() {
        return repairTasks;
    }
}
