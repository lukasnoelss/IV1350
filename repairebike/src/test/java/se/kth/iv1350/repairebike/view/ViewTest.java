package se.kth.iv1350.repairebike.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.repairebike.controller.Controller;
import se.kth.iv1350.repairebike.integration.CustomerRegistry;
import se.kth.iv1350.repairebike.integration.Printer;
import se.kth.iv1350.repairebike.integration.RepairOrderRegistry;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ViewTest {
    private View view;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() throws Exception {
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry repairOrderRegistry = RepairOrderRegistry.getInstance();
        Printer printer = new Printer();
        Controller controller = new Controller(
                customerRegistry, repairOrderRegistry, printer);
        view = new View(controller);
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testRunFakeExecutionPrintsFoundCustomer() {
        view.runFakeExecution();
        String output = outContent.toString();
        assertTrue(output.contains("Found customer:"),
                "Output should contain found customer message");
    }

    @Test
    public void testRunFakeExecutionPrintsCustomerNotFound() {
        view.runFakeExecution();
        String output = outContent.toString();
        assertTrue(output.contains("Customer not found:"),
                "Output should contain customer not found message");
    }

    @Test
    public void testRunFakeExecutionPrintsRepairOrderCreated() {
        view.runFakeExecution();
        String output = outContent.toString();
        assertTrue(output.contains("Repair order created."),
                "Output should contain repair order created message");
    }

    @Test
    public void testRunFakeExecutionPrintsOrderList() {
        view.runFakeExecution();
        String output = outContent.toString();
        assertTrue(output.contains("Order ID:"),
                "Output should contain order list");
    }

    @Test
    public void testRunFakeExecutionPrintsDiagnosticResultAdded() {
        view.runFakeExecution();
        String output = outContent.toString();
        assertTrue(output.contains("Diagnostic result added."),
                "Output should confirm diagnostic result was added");
    }

    @Test
    public void testRunFakeExecutionPrintsRepairTaskAdded() {
        view.runFakeExecution();
        String output = outContent.toString();
        assertTrue(output.contains("Repair task added."),
                "Output should confirm repair task was added");
    }

    @Test
    public void testRunFakeExecutionPrintsFoundRepairOrder() {
        view.runFakeExecution();
        String output = outContent.toString();
        assertTrue(output.contains("Found order ID:"),
                "Output should contain found repair order message");
    }

    @Test
    public void testRunFakeExecutionPrintsDatabaseError() {
        view.runFakeExecution();
        String output = outContent.toString();
        assertTrue(output.contains("ERROR: Could not reach database."),
                "Output should contain database error message");
    }
}