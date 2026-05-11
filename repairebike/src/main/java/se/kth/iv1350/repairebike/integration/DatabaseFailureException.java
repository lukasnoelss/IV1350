package se.kth.iv1350.repairebike.integration;

/**
 * Thrown when the database cannot be called,
 * for example when the database server is not running.
 */
public class DatabaseFailureException extends RuntimeException {

    /**
     * Creates a new instance with the item identifier
     * that triggered the failure.
     *
     * @param itemId The item identifier that caused the failure.
     */
    public DatabaseFailureException(int itemId) {
        super("Database failure when searching for item with id: " + itemId);
    }
}