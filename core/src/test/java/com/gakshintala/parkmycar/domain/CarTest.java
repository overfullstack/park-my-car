package com.gakshintala.parkmycar.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    void constructorTest() {
        Assertions.assertThrows(NullPointerException.class, () -> new Car(null, null));
        final var carPropsEmptyException = Assertions.assertThrows(RuntimeException.class, () -> new Car("", ""));
        Assertions.assertTrue(carPropsEmptyException.getMessage().contains("Car properties can't be empty"));
    }
}