package com.gakshintala.parkmycar;

import com.gakshintala.parkmycar.createparkinglot.CreateParkingLotCommand;
import com.gakshintala.parkmycar.createparkinglot.CreateParkingLotResult;
import com.gakshintala.parkmycar.createparkinglot.CreateParkingLotUseCase;
import com.gakshintala.parkmycar.persistence.ParkingLotState;
import com.gakshintala.parkmycar.ports.UseCase;
import lombok.experimental.UtilityClass;

/**
 * gakshintala created on 11/2/19.
 */
@UtilityClass
public class Config {

    static UseCase<CreateParkingLotCommand, CreateParkingLotResult> createParkingLotUseCase() {
        return new CreateParkingLotUseCase(ParkingLotState.getInstance());
    }
}