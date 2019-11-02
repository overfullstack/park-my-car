package com.gakshintala.parkmycar.usecases.parkcar;

import com.gakshintala.parkmycar.domain.Car;
import lombok.NonNull;
import lombok.Value;

/*
 * gakshintala created on 11/2/19
 */
@Value
public class ParkCarCommand {
    @NonNull
    Car car;
}
