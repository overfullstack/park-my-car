package com.gakshintala.parkmycar;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.persistence.ParkingLotState;
import com.gakshintala.parkmycar.ports.UseCase;
import com.gakshintala.parkmycar.usecases.createparkinglot.CreateParkingLotCommand;
import com.gakshintala.parkmycar.usecases.createparkinglot.CreateParkingLotResult;
import com.gakshintala.parkmycar.usecases.createparkinglot.CreateParkingLotUseCase;
import com.gakshintala.parkmycar.usecases.leaveslot.LeaveSlotCommand;
import com.gakshintala.parkmycar.usecases.leaveslot.LeaveSlotResult;
import com.gakshintala.parkmycar.usecases.leaveslot.LeaveSlotUseCase;
import com.gakshintala.parkmycar.usecases.lotqueries.LotStatusQuery;
import com.gakshintala.parkmycar.usecases.lotqueries.RegNumsWithColorQuery;
import com.gakshintala.parkmycar.usecases.lotqueries.SlotNumWithRegNumQuery;
import com.gakshintala.parkmycar.usecases.lotqueries.SlotNumsWithColorQuery;
import com.gakshintala.parkmycar.usecases.parkcar.ParkCarCommand;
import com.gakshintala.parkmycar.usecases.parkcar.ParkCarResult;
import com.gakshintala.parkmycar.usecases.parkcar.ParkCarUseCase;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * gakshintala created on 11/2/19.
 */
@UtilityClass
public class Config {

    static UseCase<CreateParkingLotCommand, CreateParkingLotResult> createParkingLotUseCase() {
        return new CreateParkingLotUseCase(ParkingLotState.getInstance());
    }

    static UseCase<ParkCarCommand, ParkCarResult> parkCarUseCase() {
        return new ParkCarUseCase(ParkingLotState.getInstance());
    }

    static UseCase<LeaveSlotCommand, LeaveSlotResult> leaveSlot() {
        return new LeaveSlotUseCase(ParkingLotState.getInstance());
    }

    static UseCase<Void, Map<Integer, Car>> lotStatusQuery() {
        return new LotStatusQuery(ParkingLotState.getInstance());
    }

    static UseCase<String, Collection<String>> regNumsWithColorQuery() {
        return new RegNumsWithColorQuery(ParkingLotState.getInstance());
    }

    static UseCase<String, Collection<String>> slotNumsWithColorQuery() {
        return new SlotNumsWithColorQuery(ParkingLotState.getInstance());
    }

    static UseCase<String, Optional<Integer>> slotNumsWithRegNumQuery() {
        return new SlotNumWithRegNumQuery(ParkingLotState.getInstance());
    }
}
