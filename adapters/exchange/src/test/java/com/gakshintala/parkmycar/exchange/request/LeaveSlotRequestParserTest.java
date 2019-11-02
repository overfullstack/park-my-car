package com.gakshintala.parkmycar.exchange.request;

import com.gakshintala.parkmycar.usecases.leaveslot.LeaveSlotCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LeaveSlotRequestParserTest {
    public static final String TEST_CAPACITY = "6";

    @Test
    void withNullCapacity() {
        assertThrows(NullPointerException.class, () -> new LeaveSlotRequestParser(null));
    }

    @Test
    void toCommandWithNonNumberCapacity() {
        assertThrows(NumberFormatException.class, () -> new LeaveSlotRequestParser("").toCommand());
    }

    @Test
    void toCommandWithNumberCapacity() {
        assertEquals(new LeaveSlotRequestParser(TEST_CAPACITY).toCommand(),
                new LeaveSlotCommand(6));
    }
}