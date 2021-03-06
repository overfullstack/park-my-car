package com.gakshintala.parkmycar.persistence;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.domain.CarParkStatus;
import com.gakshintala.parkmycar.ports.persistence.CreateParkingLot;
import com.gakshintala.parkmycar.ports.persistence.LeaveSlot;
import com.gakshintala.parkmycar.ports.persistence.ParkCar;
import com.gakshintala.parkmycar.ports.persistence.QueryLotStatus;
import com.gakshintala.parkmycar.usecases.parkcar.ParkCarResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

/**
 * gakshintala created on 11/2/19.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParkingLotState implements CreateParkingLot, ParkCar, LeaveSlot, QueryLotStatus {
    int capacity;
    Map<Integer, Car> slotToCar;
    TreeSet<Integer> availableSlots;

    @UtilityClass
    static class SingletonHelper {
        private static final ParkingLotState INSTANCE = new ParkingLotState();

        static void init(int capacity) {
            INSTANCE.capacity = capacity;
            INSTANCE.slotToCar = new ConcurrentHashMap<>();
            INSTANCE.availableSlots = IntStream.rangeClosed(1, capacity).boxed().collect(toCollection(TreeSet::new));
        }

        static boolean isInitialized() {
            return INSTANCE.slotToCar != null && INSTANCE.availableSlots != null;
        }
    }

    static final int INVALID_SLOT = 0;

    IntSupplier getFirstFreeSlot = () -> availableSlots.first();
    final BooleanSupplier isLotFull = () -> availableSlots.isEmpty();

    public static ParkingLotState getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public boolean createParkingLot(int capacity) {
        if (SingletonHelper.isInitialized()) {
            return false;
        }
        SingletonHelper.init(capacity);
        return true;
    }

    @Override
    public ParkCarResult park(Car car) {
        if (isLotFull.getAsBoolean()) {
            return new ParkCarResult(CarParkStatus.LOT_FULL, INVALID_SLOT);
        }
        final var firstFreeSlot = getFirstFreeSlot.getAsInt();
        final var parkedCar = slotToCar.computeIfAbsent(firstFreeSlot, slot -> {
            availableSlots.remove(slot);
            return car;
        });
        return parkedCar == car
                ? new ParkCarResult(CarParkStatus.SUCCESS, firstFreeSlot)
                : new ParkCarResult(CarParkStatus.SLOT_TAKEN, INVALID_SLOT);
    }

    @Override
    public boolean leave(int slotId) {
        if (slotId < 1 || slotId > capacity) {
            return false;
        }
        slotToCar.remove(slotId);
        availableSlots.add(slotId);
        return true;
    }

    @Override
    public Map<Integer, Car> status() {
        return slotToCar;
    }
}
