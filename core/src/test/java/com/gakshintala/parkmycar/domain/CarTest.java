package com.gakshintala.parkmycar.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    void propertiesAreNull() {
        Assertions.assertThrows(NullPointerException.class, () -> new Car(null, null));
    }
    
    @Test
    void propertiesAreEmpty() {
        final var carPropsEmptyException = Assertions.assertThrows(RuntimeException.class, () -> new Car("", ""));
        Assertions.assertTrue(carPropsEmptyException.getMessage().contains("Car properties can't be empty"));
    }
}