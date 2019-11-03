package com.gakshintala.parkmycar.exchange.resultmapper;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.exchange.ResultMapper;
import com.gakshintala.parkmycar.usecases.createparkinglot.CreateParkingLotResult;
import com.gakshintala.parkmycar.usecases.leaveslot.LeaveSlotResult;
import com.gakshintala.parkmycar.usecases.parkcar.ParkCarResult;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    static final String STATUS_TABLE_HEADER_FORMAT = "%1$-12s%2$-19s%3$-20s";
    static final String STATUS_TABLE_ENTRY_FORMAT = "%1$-12d%2$-19s%3$-20s";
    public ResultMapper<Map<Integer, Car>> lotStatusMapper = result -> {
        if (result == null) {
            return "Parking Lot is not yet Created";
        }
        return result.isEmpty()
                ? "Parking Lot is Empty"
                : String.format(STATUS_TABLE_HEADER_FORMAT, "Slot No.", "Registration No", "Colour")
                + "\n"
                + result.entrySet().stream()
                .map(entry -> String.format(STATUS_TABLE_ENTRY_FORMAT,
                        entry.getKey(),
                        entry.getValue().getRegistrationNumber(),
                        entry.getValue().getColor()))
                .collect(Collectors.joining("\n"));
    };

    public ResultMapper<Collection<String>> collectionToStringCommaSeparatedMapper = result ->
            String.join(", ", result);

    public ResultMapper<Optional<Integer>> slotNumWithRegNumResultMapper = result ->
            result.map(String::valueOf)
                    .orElse("Not found");
}
