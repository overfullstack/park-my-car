package com.gakshintala.parkmycar.createparkinglot;

import com.gakshintala.parkmycar.Request;
import com.gakshintala.parkmycar.commands.CreateParkingLotCommand;
import lombok.Value;

/*
 * gakshintala created on 11/2/19
 */
@Value
public class CreateParkingLotRequest implements Request<CreateParkingLotCommand> {
    String capacity;
    
    @Override
    public CreateParkingLotCommand toCommand() {
        return new CreateParkingLotCommand(Integer.parseInt(capacity));
    }
}
