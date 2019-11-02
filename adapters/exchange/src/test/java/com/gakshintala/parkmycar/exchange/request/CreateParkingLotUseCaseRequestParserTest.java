package com.gakshintala.parkmycar.exchange.request;

import com.gakshintala.parkmycar.createparkinglot.CreateParkingLotCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateParkingLotUseCaseRequestParserTest {
    public static final String TEST_CAPACITY = "6";

    @Test
    void withNullCapacity() {
        assertThrows(NullPointerException.class, () -> new CreateParkingLotRequestParser(null));
    }

    @Test
    void toCommandWithNonNumberCapacity() {
        assertThrows(NumberFormatException.class, () -> new CreateParkingLotRequestParser("").toCommand());
    }

    @Test
    void toCommandWithNumberCapacity() {
        assertEquals(new CreateParkingLotRequestParser(TEST_CAPACITY).toCommand(),
                new CreateParkingLotCommand(6));
    }
}