package com.gojek.parkinglot;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Operations {
    public static Map<String, Method> operationsStore;

    public Operations() {
        operationsStore = new HashMap<String, Method>();
        try {
            populateOperationsStore();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    private void populateOperationsStore() throws NoSuchMethodException {
        operationsStore.put("create_parking_lot", ParkingLot.class.getMethod("createParkingLot", String.class));
        operationsStore.put("park", ParkingLot.class.getMethod("park", String.class, String.class));
        operationsStore.put("leave", ParkingLot.class.getMethod("leave", String.class));
        operationsStore.put("status", ParkingLot.class.getMethod("status"));
        operationsStore.put("registration_numbers_for_cars_with_colour", ParkingLot.class.getMethod("getRegistrationNumbersFromColor", String.class));
        operationsStore.put("slot_numbers_for_cars_with_colour", ParkingLot.class.getMethod("getSlotNumbersFromColor", String.class));
        operationsStore.put("slot_number_for_registration_number", ParkingLot.class.getMethod("getSlotNumberFromRegNo", String.class));
    }
}
