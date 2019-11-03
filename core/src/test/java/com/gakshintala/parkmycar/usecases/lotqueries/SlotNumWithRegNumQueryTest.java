package com.gakshintala.parkmycar.usecases.lotqueries;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.ports.persistence.QueryLotStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

class SlotNumWithRegNumQueryTest {
    private static final QueryLotStatus emptyLot = Map::of;
    private static final QueryLotStatus testLotMixed = () -> Map.of(
            1, new Car("regNo1", "white"),
            2, new Car("regNo2", "white"),
            3, new Car("regNo3", "black"),
            4, new Car("regNo4", "red")
    );

    @Test
    void queryEmptyLot() {
        final var slotNumWithRegNumQuery = new SlotNumWithRegNumQuery(emptyLot);
        Assertions.assertTrue(slotNumWithRegNumQuery.execute("white").isEmpty());
    }

    @Test
    void queryRegNumNotFound() {
        final var slotNumWithRegNumQuery = new SlotNumWithRegNumQuery(testLotMixed);
        Assertions.assertTrue(slotNumWithRegNumQuery.execute("invalidRegistrationNumber").isEmpty());
    }

    @Test
    void querySlotNumsWithRegNum() {
        final var slotNumWithRegNumQuery = new SlotNumWithRegNumQuery(testLotMixed);
        Assertions.assertEquals(slotNumWithRegNumQuery.execute("regNo3"), Optional.of(3));
    }
}