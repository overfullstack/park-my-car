package com.gakshintala.parkmycar.persistence;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.ports.persistence.CreateParkingLot;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

/**
 * gakshintala created on 11/2/19.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParkingLotStateManager implements CreateParkingLot {
    Map<Integer, Car> slotToCar;
    TreeSet<Integer> availableSlots;

    private static class SingletonHelper {
        private static final ParkingLotStateManager INSTANCE = new ParkingLotStateManager();

        private static void init(int capacity) {
            INSTANCE.slotToCar = new HashMap<>();
            INSTANCE.availableSlots = IntStream.rangeClosed(1, capacity).boxed().collect(toCollection(TreeSet::new));
        }
    }

    public static ParkingLotStateManager getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public boolean createParkingLot(int capacity) {
        SingletonHelper.init(capacity);
        return true;
    }
}
