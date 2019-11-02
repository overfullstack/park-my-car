package com.gakshintala.parkmycar.parkcar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkCarResultTest {
    @Test
    void nullCarParkStatus() {
        Assertions.assertThrows(NullPointerException.class, () -> new ParkCarResult(null, 0));
    }

}