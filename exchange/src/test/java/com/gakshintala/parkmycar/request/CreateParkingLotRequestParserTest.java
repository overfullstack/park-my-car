package com.gakshintala.parkmycar.request;

import com.gakshintala.parkmycar.commands.createparkinglot.CreateParkingLotCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateParkingLotRequestParserTest {
    public static final String TEST_CAPACITY = "6";

    @Test
    void toCommandWithNullCapacity() {
        Assertions.assertThrows(NullPointerException.class, () -> new CreateParkingLotRequestParser(null));
    }
    
    @Test
    void toCommandWithNonNumberCapacity() {
        Assertions.assertThrows(NumberFormatException.class, () -> new CreateParkingLotRequestParser("").toCommand());
    }

    @Test
    void toCommandWithNumberCapacity() {
        Assertions.assertEquals(new CreateParkingLotRequestParser(TEST_CAPACITY).toCommand(), new CreateParkingLotCommand(6));
    }
}