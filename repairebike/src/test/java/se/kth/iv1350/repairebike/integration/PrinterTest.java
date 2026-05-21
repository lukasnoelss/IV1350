package se.kth.iv1350.repairebike.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.repairebike.dto.RepairOrderDTO;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class PrinterTest {
    private Printer printer;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        printer = new Printer();
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrintRepairOrderContainsId() {
        RepairOrderDTO repairOrder = createRepairOrderDTO();
        printer.printRepairOrder(repairOrder);
        String output = outContent.toString();
        assertTrue(output.contains("ID: 1"),
                "Output should contain the repair order ID");
    }

    @Test
    public void testPrintRepairOrderContainsPhone() {
        RepairOrderDTO repairOrder = createRepairOrderDTO();
        printer.printRepairOrder(repairOrder);
        String output = outContent.toString();
        assertTrue(output.contains("Phone: 0701234567"),
                "Output should contain the customer phone number");
    }

    @Test
    public void testPrintRepairOrderContainsBike() {
        RepairOrderDTO repairOrder = createRepairOrderDTO();
        printer.printRepairOrder(repairOrder);
        String output = outContent.toString();
        assertTrue(output.contains("Bike: BIKE-001"),
                "Output should contain the bike serial number");
    }

    @Test
    public void testPrintRepairOrderContainsProblem() {
        RepairOrderDTO repairOrder = createRepairOrderDTO();
        printer.printRepairOrder(repairOrder);
        String output = outContent.toString();
        assertTrue(output.contains("Problem: Battery not charging"),
                "Output should contain the problem description");
    }

    @Test
    public void testPrintRepairOrderContainsState() {
        RepairOrderDTO repairOrder = createRepairOrderDTO();
        printer.printRepairOrder(repairOrder);
        String output = outContent.toString();
        assertTrue(output.contains("State:"),
                "Output should contain the repair order state");
    }

    private RepairOrderDTO createRepairOrderDTO() {
        return new RepairOrderDTO(
                1,
                "0701234567",
                "BIKE-001",
                "Battery not charging",
                "NewlyCreated",
                new java.util.ArrayList<>(),
                new java.util.ArrayList<>());
    }
}