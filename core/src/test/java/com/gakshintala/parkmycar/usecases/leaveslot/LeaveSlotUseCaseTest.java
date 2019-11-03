package com.gakshintala.parkmycar.usecases.leaveslot;

import com.gakshintala.parkmycar.ports.persistence.LeaveSlot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LeaveSlotUseCaseTest {
    public static final int TEST_SLOT = 6;
    private static final LeaveSlot leaveSlotSuccessful = capacity -> true;
    private static final LeaveSlot leaveSlotFailed = capacity -> false;

    @Test
    void leaveSlotSuccessful() {
        final var leaveSlotSuccessfulUseCase = new LeaveSlotUseCase(leaveSlotSuccessful);
        Assertions.assertEquals(leaveSlotSuccessfulUseCase.execute(new LeaveSlotCommand(TEST_SLOT)),
                new LeaveSlotResult(true, TEST_SLOT));
    }

    @Test
    void leaveSlotFailed() {
        final var leaveSlotFailedUseCase = new LeaveSlotUseCase(leaveSlotFailed);
        Assertions.assertEquals(leaveSlotFailedUseCase.execute(new LeaveSlotCommand(TEST_SLOT)),
                new LeaveSlotResult(false, TEST_SLOT));
    }
}