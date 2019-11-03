package com.gakshintala.parkmycar.usecases.parkcar;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.domain.CarParkStatus;
import com.gakshintala.parkmycar.ports.persistence.ParkCar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkCarUseCaseTest {
    public static final int TEST_SLOT = 6;
    private static final int INVALID_SLOT = 0;
    
    private static final ParkCar parkCarLotFull = car -> new ParkCarResult(CarParkStatus.LOT_FULL, INVALID_SLOT);
    private static final ParkCar parkCarSlotTaken = car -> new ParkCarResult(CarParkStatus.SLOT_TAKEN, INVALID_SLOT);
    private static final ParkCar parkCarSuccessful = car -> new ParkCarResult(CarParkStatus.SUCCESS, TEST_SLOT);
    
    @Test
    void parkCarLotFull() {
        final var parkCarUseCase = new ParkCarUseCase(parkCarLotFull);
        Assertions.assertEquals(parkCarUseCase.execute(new ParkCarCommand(new Car("regNo", "color"))),
                new ParkCarResult(CarParkStatus.LOT_FULL, INVALID_SLOT));
    }

    @Test
    void parkCarSlotTaken() {
        final var parkCarUseCase = new ParkCarUseCase(parkCarSlotTaken);
        Assertions.assertEquals(parkCarUseCase.execute(new ParkCarCommand(new Car("regNo", "color"))),
                new ParkCarResult(CarParkStatus.SLOT_TAKEN, INVALID_SLOT));
    }

    @Test
    void parkCarSuccessful() {
        final var parkCarUseCase = new ParkCarUseCase(parkCarSuccessful);
        Assertions.assertEquals(parkCarUseCase.execute(new ParkCarCommand(new Car("regNo", "color"))),
                new ParkCarResult(CarParkStatus.SUCCESS, TEST_SLOT));
    }
}