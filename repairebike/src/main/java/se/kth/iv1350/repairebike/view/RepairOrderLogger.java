package se.kth.iv1350.repairebike.view;

import se.kth.iv1350.repairebike.dto.RepairOrderDTO;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Logs repair order updates to a file.
 * Notifies technicians and receptionists about
 * repair order changes by writing to a log file.
 */
public class RepairOrderLogger extends RepairOrderObserverHandler {
    private PrintWriter logFile;

    /**
     * Creates a new RepairOrderLogger that writes to the specified file.
     *
     * @param filename The name of the log file.
     * @throws IOException if the log file cannot be opened.
     */
    public RepairOrderLogger(String filename) throws IOException {
        logFile = new PrintWriter(new FileWriter(filename, true));
    }

    /**
     * Writes the updated repair order to the log file.
     *
     * @param repairOrder The updated repair order.
     * @throws Exception if writing to the log file fails.
     */
    @Override
    protected void doHandleRepairOrderUpdate(RepairOrderDTO repairOrder)
            throws Exception {
        logFile.println(LocalDateTime.now()
                + " REPAIR ORDER UPDATE");
        logFile.println("ID: " + repairOrder.getId());
        logFile.println("State: " + repairOrder.getState());
        logFile.println("Phone: " + repairOrder.getCustomerPhone());
        logFile.println("Bike: " + repairOrder.getBikeSerialNo());
        logFile.println("Problem: "
                + repairOrder.getCustomersProblemDescription());
        logFile.println("Diagnostic results: "
                + repairOrder.getDiagnosticResults());
        logFile.println("Repair tasks: "
                + repairOrder.getRepairTasks());
        logFile.println("---------------------------");
        logFile.flush();
    }

    /**
     * Handles errors that occur when writing to the log file.
     * Prints an error message to System.out.
     *
     * @param e The exception that was thrown.
     */
    @Override
    protected void handleErrors(Exception e) {
        System.out.println("Could not write repair order to log file: "
                + e.getMessage());
    }
}