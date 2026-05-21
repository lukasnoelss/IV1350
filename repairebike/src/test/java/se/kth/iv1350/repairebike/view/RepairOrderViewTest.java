package se.kth.iv1350.repairebike.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.repairebike.dto.RepairOrderDTO;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RepairOrderViewTest {
    private RepairOrderView repairOrderView;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        repairOrderView = new RepairOrderView();
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testRepairOrderUpdatedContainsId() {
        RepairOrderDTO repairOrder = createRepairOrderDTO();
        repairOrderView.repairOrderUpdated(repairOrder);
        String output = outContent.toString();
        assertTrue(output.contains("ID: 1"),
                "Output should contain the repair order ID");
    }

    @Test
    public void testRepairOrderUpdatedContainsState() {
        RepairOrderDTO repairOrder = createRepairOrderDTO();
        repairOrderView.repairOrderUpdated(repairOrder);
        String output = outContent.toString();
        assertTrue(output.contains("State: NewlyCreated"),
                "Output should contain the repair order state");
    }

    @Test
    public void testRepairOrderUpdatedContainsPhone() {
        RepairOrderDTO repairOrder = createRepairOrderDTO();
        repairOrderView.repairOrderUpdated(repairOrder);
        String output = outContent.toString();
        assertTrue(output.contains("Phone: 0701234567"),
                "Output should contain the customer phone number");
    }

    @Test
    public void testRepairOrderUpdatedContainsBike() {
        RepairOrderDTO repairOrder = createRepairOrderDTO();
        repairOrderView.repairOrderUpdated(repairOrder);
        String output = outContent.toString();
        assertTrue(output.contains("Bike: BIKE-001"),
                "Output should contain the bike serial number");
    }

    @Test
    public void testRepairOrderUpdatedContainsProblem() {
        RepairOrderDTO repairOrder = createRepairOrderDTO();
        repairOrderView.repairOrderUpdated(repairOrder);
        String output = outContent.toString();
        assertTrue(output.contains("Problem: Battery not charging"),
                "Output should contain the problem description");
    }

    @Test
    public void testHandleErrorsPrintsMessage() {
        repairOrderView.handleErrors(new Exception("test error"));
        String output = outContent.toString();
        assertTrue(output.contains("Could not display repair order update"),
                "Output should contain error message when update fails");
    }

    private RepairOrderDTO createRepairOrderDTO() {
        return new RepairOrderDTO(
                1,
                "0701234567",
                "BIKE-001",
                "Battery not charging",
                "NewlyCreated",
                new ArrayList<>(),
                new ArrayList<>());
    }
}