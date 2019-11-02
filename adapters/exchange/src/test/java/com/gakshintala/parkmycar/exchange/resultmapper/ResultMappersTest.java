package com.gakshintala.parkmycar.exchange.resultmapper;

import com.gakshintala.parkmycar.createparkinglot.CreateParkingLotResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.gakshintala.parkmycar.exchange.resultmapper.ResultMappers.createParkingLotResultMapper;

class ResultMappersTest {

    public static final int TEST_CREATED_CAPACITY = 6;

    @Test
    void createParkingLotResponseRendererSuccessful() {
        Assertions.assertEquals(
                createParkingLotResultMapper.fromResult(new CreateParkingLotResult(true, TEST_CREATED_CAPACITY)),
                String.format("Created a parking lot with %d slots", TEST_CREATED_CAPACITY));
    }

    @Test
    void createParkingLotResponseRendererFailed() {
        Assertions.assertEquals(
                createParkingLotResultMapper.fromResult(new CreateParkingLotResult(false, TEST_CREATED_CAPACITY)),
                String.format("Parking lot is already created with capacity %d", TEST_CREATED_CAPACITY));
    }
}