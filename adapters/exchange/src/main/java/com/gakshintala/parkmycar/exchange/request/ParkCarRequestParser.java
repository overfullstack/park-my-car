package com.gakshintala.parkmycar.exchange.request;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.exchange.RequestParser;
import com.gakshintala.parkmycar.parkcar.ParkCarCommand;
import lombok.NonNull;
import lombok.Value;

/*
 * gakshintala created on 11/2/19
 */
@Value
public class ParkCarRequestParser implements RequestParser<ParkCarCommand> {
    @NonNull
    String registrationNumber;
    @NonNull
    String color;

    @Override
    public ParkCarCommand toCommand() {
        return new ParkCarCommand(new Car(registrationNumber, color));
    }
}
