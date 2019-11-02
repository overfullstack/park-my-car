package com.gakshintala.parkmycar.createparkinglot;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreateParkingLotRequestTest {

    @Test
    void toCommand() {
        Assertions.assertThrows(NumberFormatException.class, () -> new CreateParkingLotRequest("").toCommand());
    }
}