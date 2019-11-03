package com.gakshintala.parkmycar.usecases.leaveslot;

import com.gakshintala.parkmycar.ports.UseCase;
import com.gakshintala.parkmycar.ports.persistence.LeaveSlot;
import lombok.RequiredArgsConstructor;

/**
 * gakshintala created on 11/2/19.
 */
@RequiredArgsConstructor
public class LeaveSlotUseCase implements UseCase<LeaveSlotCommand, LeaveSlotResult> {
    private final LeaveSlot leaveSlot;

    @Override
    public LeaveSlotResult execute(LeaveSlotCommand command) {
        return new LeaveSlotResult(leaveSlot.leave(command.getSlotId()), command.getSlotId());
    }
}
