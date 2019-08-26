package com.gojek.parkinglot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ParkingLotTest {
    ParkingLot lotHandler = new ParkingLot();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
    @Test
    public void createParkingLot() throws Exception {
        lotHandler.createParkingLot("6");
        assertEquals(6, lotHandler.MAX_SIZE);
        assertEquals(6, lotHandler.availableSlotList.size());
        assertTrue("createdparkinglotwith6slots".equalsIgnoreCase(outContent.toString().trim().replace(" ", "")));
    }

    @Test
    public void park() throws Exception {
        lotHandler.park("KA-01-HH-1234", "White");
        lotHandler.park("KA-01-HH-9999", "White");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
        lotHandler.createParkingLot("6");
        lotHandler.park("KA-01-HH-1234", "White");
        lotHandler.park("KA-01-HH-9999", "White");
        assertEquals(4, lotHandler.availableSlotList.size());
    }

    @Test
    public void leave() throws Exception {
        lotHandler.leave("2");
        assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
        lotHandler.createParkingLot("6");
        lotHandler.park("KA-01-HH-1234", "White");
        lotHandler.park("KA-01-HH-9999", "White");
        lotHandler.leave("4");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "Slotnumber4isalreadyempty", outContent.toString().trim().replace(" ", ""));
    }

    @Test
    public void status() throws Exception {
        lotHandler.status();
        assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
        lotHandler.createParkingLot("6");
        lotHandler.park("KA-01-HH-1234", "White");
        lotHandler.park("KA-01-HH-9999", "White");
        lotHandler.status();
       
		/*
		 * assertEquals("Sorry,parkinglotisnotcreated\n" + "\n" +
		 * "Createdparkinglotwith6slots\n" + "\n" + "Allocatedslotnumber:1\n" + "\n" +
		 * "Allocatedslotnumber:2\n" + "\n" + "SlotNo.\tRegistrationNo.\tColor\n" +
		 * "1\tKA-01-HH-1234\tWhite\n" + "2\tKA-01-HH-9999\tWhite",
		 * outContent.toString().trim().replace(" ", ""));
		 */
    }

    @Test
    public void getRegistrationNumbersFromColor() throws Exception {
        lotHandler.getRegistrationNumbersFromColor("White");
        assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
        lotHandler.createParkingLot("6");
        lotHandler.park("KA-01-HH-1234", "White");
        lotHandler.park("KA-01-HH-9999", "White");
        lotHandler.getRegistrationNumbersFromColor("White");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "\n" +
                "KA-01-HH-1234,KA-01-HH-9999", outContent.toString().trim().replace(" ", ""));
        lotHandler.getRegistrationNumbersFromColor("Red");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "\n" +
                "KA-01-HH-1234,KA-01-HH-9999Notfound", outContent.toString().trim().replace(" ", ""));
    }

    @Test
    public void getSlotNumbersFromColor() throws Exception {
        lotHandler.getSlotNumbersFromColor("White");
        assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
        lotHandler.createParkingLot("6");
        lotHandler.park("KA-01-HH-1234", "White");
        lotHandler.park("KA-01-HH-9999", "White");
        lotHandler.getSlotNumbersFromColor("White");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "\n" +
                "1,2", outContent.toString().trim().replace(" ", ""));
        lotHandler.getSlotNumbersFromColor("Red");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "\n" +
                "1,2\n" +
                "Notfound", outContent.toString().trim().replace(" ", ""));
    }

    @Test
    public void getSlotNumberFromRegNo() throws Exception {
        lotHandler.getSlotNumberFromRegNo("KA-01-HH-1234");
        assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
        lotHandler.createParkingLot("6");
        lotHandler.park("KA-01-HH-1234", "White");
        lotHandler.park("KA-01-HH-9999", "White");
        lotHandler.getSlotNumberFromRegNo("KA-01-HH-1234");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "1", outContent.toString().trim().replace(" ", ""));
        lotHandler.getSlotNumberFromRegNo("KA-01-HH-9999");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "1\n" +
                "2", outContent.toString().trim().replace(" ", ""));
        lotHandler.leave("1");
        lotHandler.getSlotNumberFromRegNo("KA-01-HH-1234");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "1\n" +
                "2\n" +
                "Slotnumber1isfree\n" +
                "\n" +
                "Notfound", outContent.toString().trim().replace(" ", ""));
    }

}