package com.gakshintala.parkmycar.persistence;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.domain.CarParkStatus;
import com.gakshintala.parkmycar.parkcar.ParkCarResult;
import com.gakshintala.parkmycar.ports.persistence.CreateParkingLot;
import com.gakshintala.parkmycar.ports.persistence.ParkCar;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

/**
 * gakshintala created on 11/2/19.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParkingLotState implements CreateParkingLot, ParkCar {
    Map<Integer, Car> slotToCar;
    TreeSet<Integer> availableSlots;

    static class SingletonHelper {
        private static final ParkingLotState INSTANCE = new ParkingLotState();

        static void init(int capacity) {
            INSTANCE.slotToCar = new HashMap<>();
            INSTANCE.availableSlots = IntStream.rangeClosed(1, capacity).boxed().collect(toCollection(TreeSet::new));
        }

        static boolean isInitialized() {
            return INSTANCE.slotToCar != null && INSTANCE.availableSlots != null;
        }
    }

    static final int INVALID_SLOT = 0;

    Supplier<Integer> getFirstFreeSlot = () -> availableSlots.first();
    Supplier<Boolean> isLotFull = () -> availableSlots.size() == 0;

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
        if (isLotFull.get()) {
            return new ParkCarResult(CarParkStatus.LOT_FULL, INVALID_SLOT);
        }
        final var firstFreeSlot = getFirstFreeSlot.get();
        slotToCar.computeIfAbsent(firstFreeSlot, slot -> {
            availableSlots.remove(slot);
            return car;
        });
        return new ParkCarResult(CarParkStatus.SUCCESS, firstFreeSlot);
    }
}
