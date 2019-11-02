package com.gakshintala.parkmycar.response;

import com.gakshintala.parkmycar.commands.createparkinglot.CreateParkingLotResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.gakshintala.parkmycar.response.ConsoleResponseRenderers.createParkingLotResponseRenderer;

class ConsoleResponseRenderersTest {

    public static final int TEST_CREATED_CAPACITY = 6;

    @Test
    void createParkingLotResponseRenderer() {
        Assertions.assertEquals(
                createParkingLotResponseRenderer.fromResult(new CreateParkingLotResult(true, TEST_CREATED_CAPACITY)),
                String.format("Created a parking lot with %d slots", TEST_CREATED_CAPACITY));
    }
}