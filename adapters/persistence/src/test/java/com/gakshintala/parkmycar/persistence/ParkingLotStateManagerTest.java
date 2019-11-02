package com.gakshintala.parkmycar.persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingLotStateManagerTest {

    public static final int TEST_CAPACITY = 6;

    @Test
    void withOutCreateParkingLot() {
        final var parkingLot = ParkingLotStateManager.getInstance();
        Assertions.assertNull(parkingLot.slotToCar);
        Assertions.assertNull(parkingLot.availableSlots);
    }

    @Test
    void withCreateParkingLot() {
        final var parkingLot = ParkingLotStateManager.getInstance();
        final var result = parkingLot.createParkingLot(TEST_CAPACITY);
        Assertions.assertTrue(result);
        Assertions.assertNotNull(parkingLot.slotToCar);
        Assertions.assertNotNull(parkingLot.availableSlots);
        Assertions.assertEquals(parkingLot.availableSlots.size(), TEST_CAPACITY);
    }
}