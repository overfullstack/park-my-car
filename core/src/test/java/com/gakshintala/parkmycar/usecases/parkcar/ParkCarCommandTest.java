package com.gakshintala.parkmycar.usecases.parkcar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkCarCommandTest {

    @Test
    void nullCar() {
        Assertions.assertThrows(NullPointerException.class, () -> new ParkCarCommand(null));
    }
}