package com.gakshintala.parkmycar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.gakshintala.parkmycar.CommandController.INVALID_COMMAND;

class CommandControllerTest {
    
    @Test
    void invalidCommand() {
        Assertions.assertEquals(INVALID_COMMAND, CommandController.executeCommand("invalidCommand"));
    }
    
    @Test
    void invalidArgumentCount() {
        Assertions.assertEquals("'create_parking_lot' command needs 1 argument", 
                CommandController.executeCommand("create_parking_lot"));
    }
}