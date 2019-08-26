package com.gojek.parkinglot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class OperationsTest {
    Operations allowedCommands = new Operations();
    @Test
    public void checkCommandInList() throws Exception {
        assertFalse(allowedCommands.operationsStore.isEmpty());
        assertTrue(allowedCommands.operationsStore.containsKey("create_parking_lot"));
        assertFalse(allowedCommands.operationsStore.containsKey("mytestcommand"));
    }
}