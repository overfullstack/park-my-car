package com.gakshintala.parkmycar.createparkinglot;

import com.gakshintala.parkmycar.ports.persistence.CreateParkingLot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreateParkingLotUseCaseTest {

    public static final int TEST_CAPACITY = 6;
    private static final CreateParkingLot createParkingLotSuccessful = capacity -> true;
    private static final CreateParkingLot createParkingLotFailed = capacity -> false;

    @Test
    void createParkingLotSuccessful() {
        final var createParkingLotSuccessfulUseCase = new CreateParkingLotUseCase(createParkingLotSuccessful);
        Assertions.assertEquals(createParkingLotSuccessfulUseCase.execute(new CreateParkingLotCommand(TEST_CAPACITY)),
                new CreateParkingLotResult(true, TEST_CAPACITY));
    }

    @Test
    void createParkingLotFailed() {
        final var createParkingLotFailedUseCase = new CreateParkingLotUseCase(createParkingLotFailed);
        Assertions.assertEquals(createParkingLotFailedUseCase.execute(new CreateParkingLotCommand(TEST_CAPACITY)),
                new CreateParkingLotResult(false, TEST_CAPACITY));
    }
}