package com.gakshintala.parkmycar.persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingLotStateSingleTonTest {

    public static final int TEST_CAPACITY = 6;

    @Test
    void createParkingLot() {
        final var parkingLot = ParkingLotState.getInstance();
        Assertions.assertNull(parkingLot.slotToCar);
        Assertions.assertNull(parkingLot.availableSlots);
        
        final var createOnceResult = parkingLot.createParkingLot(TEST_CAPACITY);
        Assertions.assertTrue(createOnceResult);
        
        final var createAgainResult = parkingLot.createParkingLot(TEST_CAPACITY);
        Assertions.assertFalse(createAgainResult);
        Assertions.assertEquals(parkingLot.capacity, TEST_CAPACITY);
        Assertions.assertNotNull(parkingLot.slotToCar);
        Assertions.assertNotNull(parkingLot.availableSlots);
        Assertions.assertEquals(parkingLot.availableSlots.size(), TEST_CAPACITY);

        Assertions.assertSame(ParkingLotState.getInstance(), ParkingLotState.getInstance());
    }
    
}