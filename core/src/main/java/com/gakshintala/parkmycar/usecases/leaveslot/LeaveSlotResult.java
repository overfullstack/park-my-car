package com.gakshintala.parkmycar.usecases.leaveslot;

import lombok.Value;

/*
 * gakshintala created on 11/2/19
 */
@Value
public class LeaveSlotResult {
    boolean isSuccess;
    int slotId;
}
