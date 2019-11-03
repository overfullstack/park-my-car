package com.gakshintala.parkmycar.persistence;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.domain.CarParkStatus;
import com.gakshintala.parkmycar.usecases.parkcar.ParkCarResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.IntStream;

import static com.gakshintala.parkmycar.persistence.ParkingLotState.INVALID_SLOT;

class ParkingLotStateTest {

    static final int TEST_CAPACITY = 6;
    static final int CONCURRENCY_EMULATED_SAME_SLOT = 1;
    static final int TEST_VALID_SLOT_ID = 1;
    private static ParkingLotState parkingLot;
    
    @BeforeEach
    void setup() {
        ParkingLotState.SingletonHelper.init(TEST_CAPACITY);
        parkingLot = ParkingLotState.getInstance();
    }
    
    @Test
    void parkCarSuccessful() {
        Assertions.assertEquals(new ParkCarResult(CarParkStatus.SUCCESS, 1),
                parkingLot.park(new Car("regNo", "color")));
    }

    @Test
    void parkCarLotFull() {
        IntStream.rangeClosed(1, TEST_CAPACITY).forEach(ignore -> parkingLot.park(new Car("regNo", "color")));
        Assertions.assertEquals(new ParkCarResult(CarParkStatus.LOT_FULL, INVALID_SLOT),
                ParkingLotState.getInstance().park(new Car("regNo", "color")));
    }

    @Test
    void concurrentlyGettingTheSameSlot() {
        parkingLot.getFirstFreeSlot = () -> CONCURRENCY_EMULATED_SAME_SLOT;

        Assertions.assertEquals(new ParkCarResult(CarParkStatus.SUCCESS, CONCURRENCY_EMULATED_SAME_SLOT),
                parkingLot.park(new Car("regNo", "color")));
        Assertions.assertEquals(new ParkCarResult(CarParkStatus.SLOT_TAKEN, INVALID_SLOT),
                ParkingLotState.getInstance().park(new Car("regNo", "color")));
        parkingLot.getFirstFreeSlot = () -> parkingLot.availableSlots.first();
    }

    @Test
    void leaveValidSlot() {
        Assertions.assertTrue(parkingLot.leave(TEST_VALID_SLOT_ID));
    }

    @Test
    void leaveValidSlotIdempotent() {
        Assertions.assertTrue(parkingLot.leave(TEST_VALID_SLOT_ID));
        Assertions.assertTrue(parkingLot.leave(TEST_VALID_SLOT_ID));
    }

    @Test
    void leaveInvalidSlot() {
        Assertions.assertFalse(parkingLot.leave(0));
        Assertions.assertFalse(parkingLot.leave(TEST_CAPACITY + 1));
    }

    @Test
    void status() {
        final var car1 = new Car("regNo", "color");
        final var car2 = new Car("regNo", "color");
        final var car1ParkResult = parkingLot.park(car1);
        final var car2ParkResult = parkingLot.park(car2);
        parkingLot.leave(car1ParkResult.getSlotId());
        
        final var expectedStatus = Map.of(car2ParkResult.getSlotId(), car2);
        Assertions.assertEquals(expectedStatus, parkingLot.status());
    }

    @Test
    void isSlotAllocationFindFirst() {
        final var car1 = new Car("regNo", "color");
        final var car2 = new Car("regNo", "color");
        final var car3 = new Car("regNo", "color");
        final var car4 = new Car("regNo", "color");
        
        parkingLot.park(car1);
        final var car2ParkResult = parkingLot.park(car2);
        parkingLot.park(car3);
        
        parkingLot.leave(car2ParkResult.getSlotId());
        final var car4ParkResult = parkingLot.park(car4);
        
        Assertions.assertEquals(2, car4ParkResult.getSlotId());
    }
}