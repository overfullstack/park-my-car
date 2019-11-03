package com.gakshintala.parkmycar.exchange.resultmapper;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.domain.CarParkStatus;
import com.gakshintala.parkmycar.usecases.createparkinglot.CreateParkingLotResult;
import com.gakshintala.parkmycar.usecases.leaveslot.LeaveSlotResult;
import com.gakshintala.parkmycar.usecases.parkcar.ParkCarResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.gakshintala.parkmycar.exchange.resultmapper.ResultMappers.STATUS_TABLE_ENTRY_FORMAT;
import static com.gakshintala.parkmycar.exchange.resultmapper.ResultMappers.STATUS_TABLE_HEADER_FORMAT;
import static com.gakshintala.parkmycar.exchange.resultmapper.ResultMappers.collectionToStringCommaSeparatedMapper;
import static com.gakshintala.parkmycar.exchange.resultmapper.ResultMappers.createParkingLotResultMapper;
import static com.gakshintala.parkmycar.exchange.resultmapper.ResultMappers.leaveSlotResultMapper;
import static com.gakshintala.parkmycar.exchange.resultmapper.ResultMappers.lotStatusMapper;
import static com.gakshintala.parkmycar.exchange.resultmapper.ResultMappers.parkCarResultMapper;
import static com.gakshintala.parkmycar.exchange.resultmapper.ResultMappers.slotNumWithRegNumResultMapper;

class ResultMappersTest {

    public static final int TEST_CREATED_CAPACITY = 6;
    public static final int INVALID_SLOT_ID = 0;
    public static final int VALID_SLOT_ID = 1;

    @Test
    void createParkingLotResponseMapperSuccessful() {
        Assertions.assertEquals(
                String.format("Created a parking lot with %d slots", TEST_CREATED_CAPACITY),
                createParkingLotResultMapper.fromResult(new CreateParkingLotResult(true, TEST_CREATED_CAPACITY)));
    }

    @Test
    void createParkingLotResponseMapperFailed() {
        Assertions.assertEquals(
                String.format("Parking lot is already created with capacity %d", TEST_CREATED_CAPACITY),
                createParkingLotResultMapper.fromResult(new CreateParkingLotResult(false, TEST_CREATED_CAPACITY)));
    }

    @Test
    void parkCarResponseMapperSuccessful() {
        Assertions.assertEquals(String.format("Allocated slot number: %d", 1),
                parkCarResultMapper.fromResult(new ParkCarResult(CarParkStatus.SUCCESS, 1)));
    }

    @Test
    void parkCarResponseMapperLotFull() {
        Assertions.assertEquals("Sorry, parking lot is full",
                parkCarResultMapper.fromResult(new ParkCarResult(CarParkStatus.LOT_FULL, 1)));
    }

    @Test
    void leaveSlotResponseMapperSlotNotFound() {
        Assertions.assertEquals(String.format("Slot %d not found", INVALID_SLOT_ID),
                leaveSlotResultMapper.fromResult(new LeaveSlotResult(false, INVALID_SLOT_ID)));
    }

    @Test
    void leaveSlotResponseMapperSuccessful() {
        Assertions.assertEquals(String.format("Slot number %d is free", VALID_SLOT_ID),
                leaveSlotResultMapper.fromResult(new LeaveSlotResult(true, VALID_SLOT_ID)));
    }

    @Test
    void lotStatusMapperWithEmptyResult() {
        Assertions.assertEquals("Parking Lot is Empty", lotStatusMapper.fromResult(Map.of()));
    }

    @Test
    void lotStatusMapperWithNullResult() {
        Assertions.assertEquals("Parking Lot is not yet Created", lotStatusMapper.fromResult(null));
    }

    @Test
    void lotStatusMapperWithOneEntry() {
        Assertions.assertEquals(
                String.format(STATUS_TABLE_HEADER_FORMAT, "Slot No.", "Registration No", "Colour")
                        + "\r\n"
                        + String.format(STATUS_TABLE_ENTRY_FORMAT, 1, "KA-01-HH-1234", "White"),
                lotStatusMapper.fromResult(Map.of(1, new Car("KA-01-HH-1234", "White"))));
    }

    @Test
    void collectionToStringCommaSeparatedWithEmptyCollection() {
        Assertions.assertEquals("", collectionToStringCommaSeparatedMapper.fromResult(Collections.emptySet()));
    }

    @Test
    void collectionToStringCommaSeparatedWithSizeOneCollection() {
        Assertions.assertEquals("a", collectionToStringCommaSeparatedMapper.fromResult(Set.of("a")));
    }

    @Test
    void slotNumWithRegNumResultMapperNotFound() {
        Assertions.assertEquals(slotNumWithRegNumResultMapper.fromResult(Optional.empty()), "Not found");
    }

    @Test
    void slotNumWithRegNumResultMapper() {
        Assertions.assertEquals(slotNumWithRegNumResultMapper.fromResult(Optional.of(VALID_SLOT_ID)),
                String.valueOf(VALID_SLOT_ID));
    }
}