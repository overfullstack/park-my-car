package com.gakshintala.parkmycar.exchange.request;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.usecases.parkcar.ParkCarCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkCarRequestParserTest {

    public static final String TEST_REG_NO = "regNo";
    public static final String TEST_COLOR = "color";

    @Test
    void withNullProperties() {
        assertThrows(NullPointerException.class, () -> new ParkCarRequestParser(null, null));
    }
    
    @Test
    void toCommandWithEmptyProperties() {
        final var carPropsEmptyException = assertThrows(RuntimeException.class, 
                () -> new ParkCarRequestParser("", "").toCommand());
        Assertions.assertTrue(carPropsEmptyException.getMessage().contains("Car properties can't be empty"));
    }
    
    @Test
    void toCommandWithValidProperties() {
        assertEquals(new ParkCarRequestParser(TEST_REG_NO, TEST_COLOR).toCommand(),
                new ParkCarCommand(new Car(TEST_REG_NO, TEST_COLOR)));
    }
}