package com.gakshintala.parkmycar.exchange.request;

import com.gakshintala.parkmycar.usecases.createparkinglot.CreateParkingLotCommand;
import com.gakshintala.parkmycar.exchange.RequestParser;
import lombok.NonNull;
import lombok.Value;

/*
 * gakshintala created on 11/2/19
 */
@Value
public class CreateParkingLotRequestParser implements RequestParser<CreateParkingLotCommand> {
    @NonNull
    String capacity;

    @Override
    public CreateParkingLotCommand toCommand() {
        return new CreateParkingLotCommand(Integer.parseInt(capacity));
    }
}
