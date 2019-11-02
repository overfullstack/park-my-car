package com.gakshintala.parkmycar.usecases.parkcar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkCarResultTest {
    @Test
    void nullCarParkStatus() {
        Assertions.assertThrows(NullPointerException.class, () -> new ParkCarResult(null, 0));
    }

}