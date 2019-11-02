package com.gakshintala.parkmycar.exchange.resultmapper;

import com.gakshintala.parkmycar.createparkinglot.CreateParkingLotResult;
import com.gakshintala.parkmycar.domain.CarParkStatus;
import com.gakshintala.parkmycar.parkcar.ParkCarResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.gakshintala.parkmycar.exchange.resultmapper.ResultMappers.createParkingLotResultMapper;
import static com.gakshintala.parkmycar.exchange.resultmapper.ResultMappers.parkCarResultMapper;

class ResultMappersTest {

    public static final int TEST_CREATED_CAPACITY = 6;

    @Test
    void createParkingLotResponseMapperSuccessful() {
        Assertions.assertEquals(
                createParkingLotResultMapper.fromResult(new CreateParkingLotResult(true, TEST_CREATED_CAPACITY)),
                String.format("Created a parking lot with %d slots", TEST_CREATED_CAPACITY));
    }

    @Test
    void createParkingLotResponseMapperFailed() {
        Assertions.assertEquals(
                createParkingLotResultMapper.fromResult(new CreateParkingLotResult(false, TEST_CREATED_CAPACITY)),
                String.format("Parking lot is already created with capacity %d", TEST_CREATED_CAPACITY));
    }
    
    @Test
    void parkCarResponseMapperSuccessful() {
        Assertions.assertEquals(parkCarResultMapper.fromResult(new ParkCarResult(CarParkStatus.SUCCESS, 1)),
                String.format("Allocated slot number: %d", 1));
    }

    @Test
    void parkCarResponseMapperLotFull() {
        Assertions.assertEquals(parkCarResultMapper.fromResult(new ParkCarResult(CarParkStatus.LOT_FULL, 1)),
                "Sorry, parking lot is full");
    }
}