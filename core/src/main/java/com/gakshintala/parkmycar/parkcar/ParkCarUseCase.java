package com.gakshintala.parkmycar.parkcar;

import com.gakshintala.parkmycar.ports.UseCase;
import com.gakshintala.parkmycar.ports.persistence.ParkCar;
import lombok.RequiredArgsConstructor;

/**
 * gakshintala created on 11/2/19.
 */
@RequiredArgsConstructor
public class ParkCarUseCase implements UseCase<ParkCarCommand, ParkCarResult> {
    private final ParkCar parkCar;

    @Override
    public ParkCarResult execute(ParkCarCommand command) {
        return parkCar.park(command.getCar());
    }
}
