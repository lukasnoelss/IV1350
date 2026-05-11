package se.kth.iv1350.repairebike.view;

import se.kth.iv1350.repairebike.dto.RepairOrderDTO;
import se.kth.iv1350.repairebike.model.RepairOrderObserver;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Logs repair order updates to a file.
 * Notifies technicians and receptionists about
 * repair order changes by writing to a log file.
 */
public class RepairOrderLogger implements RepairOrderObserver {
    private PrintWriter logFile;

    /**
     * Creates a new RepairOrderLogger that writes to the specified file.
     *
     * @param filename The name of the log file.
     */
    public RepairOrderLogger(String filename) {
        try {
            logFile = new PrintWriter(new FileWriter(filename, true));
        } catch (IOException e) {
            System.out.println("Could not open repair order log file: "
                    + e.getMessage());
        }
    }

    /**
     * Called when a repair order has been updated.
     * Writes the updated repair order to the log file.
     *
     * @param repairOrder The updated repair order.
     */
    @Override
    public void repairOrderUpdated(RepairOrderDTO repairOrder) {
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
}