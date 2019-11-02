package com.gakshintala.parkmycar.commands.createparkinglot;

import lombok.Value;

/*
 * gakshintala created on 11/2/19
 */
@Value
public class CreateParkingLotResult {
    boolean isSuccess;
    int createdCapacity;
}
