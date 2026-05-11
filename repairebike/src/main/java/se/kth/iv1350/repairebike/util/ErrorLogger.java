package se.kth.iv1350.repairebike.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Logs error messages to a file.
 */
public class ErrorLogger {
    private PrintWriter logFile;

    /**
     * Creates a new ErrorLogger that writes to the specified file.
     *
     * @param filename The name of the log file.
     */
    public ErrorLogger(String filename) {
        try {
            logFile = new PrintWriter(new FileWriter(filename, true));
        } catch (IOException e) {
            System.out.println("Could not open log file: " + e.getMessage());
        }
    }

    /**
     * Logs an error message with a timestamp.
     *
     * @param message The error message to log.
     * @param cause   The exception that caused the error.
     */
    public void log(String message, Exception cause) {
        logFile.println(LocalDateTime.now() + " ERROR: " + message);
        cause.printStackTrace(logFile);
        logFile.flush();
    }
}