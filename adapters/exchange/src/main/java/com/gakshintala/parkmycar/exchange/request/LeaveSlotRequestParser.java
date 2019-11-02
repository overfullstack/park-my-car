package com.gakshintala.parkmycar.exchange.request;

import com.gakshintala.parkmycar.exchange.RequestParser;
import com.gakshintala.parkmycar.usecases.leaveslot.LeaveSlotCommand;
import lombok.NonNull;
import lombok.Value;

/*
 * gakshintala created on 11/2/19
 */
@Value
public class LeaveSlotRequestParser implements RequestParser<LeaveSlotCommand> {
    @NonNull
    String slotId;

    @Override
    public LeaveSlotCommand toCommand() {
        return new LeaveSlotCommand(Integer.parseInt(slotId));
    }
}
