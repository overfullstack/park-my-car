package com.gakshintala.parkmycar.parkcar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkCarCommandTest {

    @Test
    void nullCar() {
        Assertions.assertThrows(NullPointerException.class, () -> new ParkCarCommand(null));
    }
}