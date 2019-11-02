package com.gakshintala.parkmycar.exchange.resultmapper;

import com.gakshintala.parkmycar.usecases.createparkinglot.CreateParkingLotResult;
import com.gakshintala.parkmycar.exchange.ResultMapper;
import com.gakshintala.parkmycar.usecases.leaveslot.LeaveSlotResult;
import com.gakshintala.parkmycar.usecases.parkcar.ParkCarResult;
import lombok.experimental.UtilityClass;

/**
 * gakshintala created on 11/2/19.
 */
@UtilityClass
public class ResultMappers {
    public ResultMapper<CreateParkingLotResult> createParkingLotResultMapper = result ->
            result.isSuccess()
                    ? String.format("Created a parking lot with %d slots", result.getCreatedCapacity())
                    : String.format("Parking lot is already created with capacity %d", result.getCreatedCapacity());

    public ResultMapper<ParkCarResult> parkCarResultMapper = result -> {
        switch (result.getCarParkStatus()) {
            case SUCCESS:
                return String.format("Allocated slot number: %d", result.getSlotId());
            case LOT_FULL:
                return "Sorry, parking lot is full";
            default:
                return "Status unknown";
        }
    };
    
    public ResultMapper<LeaveSlotResult> leaveSlotResultMapper = result -> 
            result.isSuccess()
            ? String.format("Slot number %d is free", result.getSlotId())
            : String.format("Slot %d not found", result.getSlotId());
                    

}
