package com.gakshintala.parkmycar.createparkinglot;

import com.gakshintala.parkmycar.ports.UseCase;
import com.gakshintala.parkmycar.ports.persistence.CreateParkingLot;
import lombok.RequiredArgsConstructor;

/**
 * gakshintala created on 11/2/19.
 */
@RequiredArgsConstructor
public class CreateParkingLotUseCase implements UseCase<CreateParkingLotCommand, CreateParkingLotResult> {
    private final CreateParkingLot createParkingLot;

    @Override
    public CreateParkingLotResult execute(CreateParkingLotCommand command) {
        return new CreateParkingLotResult(
                createParkingLot.createParkingLot(command.getCapacity()),
                command.getCapacity());
    }
}
