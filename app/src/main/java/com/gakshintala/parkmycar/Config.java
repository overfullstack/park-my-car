package com.gakshintala.parkmycar;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.persistence.ParkingQueryLotState;
import com.gakshintala.parkmycar.ports.UseCase;
import com.gakshintala.parkmycar.usecases.createparkinglot.CreateParkingLotCommand;
import com.gakshintala.parkmycar.usecases.createparkinglot.CreateParkingLotResult;
import com.gakshintala.parkmycar.usecases.createparkinglot.CreateParkingLotUseCase;
import com.gakshintala.parkmycar.usecases.leaveslot.LeaveSlotCommand;
import com.gakshintala.parkmycar.usecases.leaveslot.LeaveSlotResult;
import com.gakshintala.parkmycar.usecases.leaveslot.LeaveSlotUseCase;
import com.gakshintala.parkmycar.usecases.lotqueries.LotStatusQuery;
import com.gakshintala.parkmycar.usecases.lotqueries.RegNumsWithColorQuery;
import com.gakshintala.parkmycar.usecases.lotqueries.SlotNumsWithColorQuery;
import com.gakshintala.parkmycar.usecases.parkcar.ParkCarCommand;
import com.gakshintala.parkmycar.usecases.parkcar.ParkCarResult;
import com.gakshintala.parkmycar.usecases.parkcar.ParkCarUseCase;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Map;

/**
 * gakshintala created on 11/2/19.
 */
@UtilityClass
public class Config {

    static UseCase<CreateParkingLotCommand, CreateParkingLotResult> createParkingLotUseCase() {
        return new CreateParkingLotUseCase(ParkingQueryLotState.getInstance());
    }

    static UseCase<ParkCarCommand, ParkCarResult> parkCarUseCase() {
        return new ParkCarUseCase(ParkingQueryLotState.getInstance());
    }

    static UseCase<LeaveSlotCommand, LeaveSlotResult> leaveSlot() {
        return new LeaveSlotUseCase(ParkingQueryLotState.getInstance());
    }

    static UseCase<Void, Map<Integer, Car>> lotStatusQuery() {
        return new LotStatusQuery(ParkingQueryLotState.getInstance());
    }

    static UseCase<String, Collection<String>> regNumsWithColorQuery() {
        return new RegNumsWithColorQuery(ParkingQueryLotState.getInstance());
    }

    static UseCase<String, Collection<String>> slotNumsWithColorQuery() {
        return new SlotNumsWithColorQuery(ParkingQueryLotState.getInstance());
    }
}
