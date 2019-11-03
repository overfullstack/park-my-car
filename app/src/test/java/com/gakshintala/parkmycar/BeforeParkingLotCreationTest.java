package com.gakshintala.parkmycar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.gakshintala.parkmycar.CommandController.PARKING_LOT_IS_NOT_YET_CREATED;

class BeforeParkingLotCreationTest {
    
    @Test
    void beforeCreationOfParkingLot() {
        Assertions.assertEquals(PARKING_LOT_IS_NOT_YET_CREATED, 
                CommandController.executeCommand("park KA-01-HH-1234 White"));
    }
    
}