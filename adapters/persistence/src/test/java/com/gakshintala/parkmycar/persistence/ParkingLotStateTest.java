package com.gakshintala.parkmycar.persistence;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.domain.CarParkStatus;
import com.gakshintala.parkmycar.parkcar.ParkCarResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static com.gakshintala.parkmycar.persistence.ParkingLotState.INVALID_SLOT;

class ParkingLotStateTest {

    public static final int TEST_CAPACITY = 6;
    public static final int CONCURRENCY_EMULATED_SAME_SLOT = 1;

    @Test
    @Order(1)
    void createParkingLot() {
        final var parkingLot = ParkingLotState.getInstance();
        Assertions.assertNull(parkingLot.slotToCar);
        Assertions.assertNull(parkingLot.availableSlots);
        
        final var createOnceResult = parkingLot.createParkingLot(TEST_CAPACITY);
        final var createAgainResult = parkingLot.createParkingLot(TEST_CAPACITY);

        Assertions.assertTrue(createOnceResult);
        Assertions.assertFalse(createAgainResult);

        Assertions.assertNotNull(parkingLot.slotToCar);
        Assertions.assertNotNull(parkingLot.availableSlots);
        Assertions.assertEquals(parkingLot.availableSlots.size(), TEST_CAPACITY);
    }
    
    @Test
    void isSingleTon() {
        final var parkingLot = ParkingLotState.getInstance();
        parkingLot.createParkingLot(TEST_CAPACITY);
        Assertions.assertSame(ParkingLotState.getInstance(), ParkingLotState.getInstance());
    }

    @Test
    void parkCarSuccessful() {
        ParkingLotState.SingletonHelper.init(TEST_CAPACITY);
        final var parkingLot = ParkingLotState.getInstance();
        Assertions.assertEquals(parkingLot.park(new Car("regNo", "color")),
                new ParkCarResult(CarParkStatus.SUCCESS, 1));
    }
    
    @Test
    void parkCarLotFull() {
        ParkingLotState.SingletonHelper.init(TEST_CAPACITY);
        final var parkingLot = ParkingLotState.getInstance();
        IntStream.rangeClosed(1, TEST_CAPACITY).forEach(ignore -> parkingLot.park(new Car("regNo", "color")));
        Assertions.assertEquals(ParkingLotState.getInstance().park(new Car("regNo", "color")), 
                new ParkCarResult(CarParkStatus.LOT_FULL, INVALID_SLOT));
    }
    
    @Test
    void concurrentlyGettingTheSameSlot() {
        ParkingLotState.SingletonHelper.init(TEST_CAPACITY);
        final var parkingLot = ParkingLotState.getInstance();
        parkingLot.getFirstFreeSlot = () -> CONCURRENCY_EMULATED_SAME_SLOT;
        
        Assertions.assertEquals(parkingLot.park(new Car("regNo", "color")),
                new ParkCarResult(CarParkStatus.SUCCESS, CONCURRENCY_EMULATED_SAME_SLOT));
        Assertions.assertEquals(ParkingLotState.getInstance().park(new Car("regNo", "color")),
                new ParkCarResult(CarParkStatus.SLOT_TAKEN, INVALID_SLOT));
    }
}