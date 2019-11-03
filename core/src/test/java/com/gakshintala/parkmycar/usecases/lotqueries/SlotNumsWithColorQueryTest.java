package com.gakshintala.parkmycar.usecases.lotqueries;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.ports.persistence.QueryLotStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

class SlotNumsWithColorQueryTest {
    private static final QueryLotStatus emptyLot = Map::of;
    private static final QueryLotStatus testLotMixed = () -> Map.of(
            1, new Car("regNo1", "white"),
            2, new Car("regNo2", "white"),
            3, new Car("regNo3", "black"),
            4, new Car("regNo4", "red")
    );

    @Test
    void queryEmptyLot() {
        final var slotNumsWithColorQuery = new SlotNumsWithColorQuery(emptyLot);
        Assertions.assertTrue(slotNumsWithColorQuery.execute("white").isEmpty());
    }

    @Test
    void queryColorNotFound() {
        final var slotNumsWithColorQuery = new SlotNumsWithColorQuery(testLotMixed);
        Assertions.assertTrue(slotNumsWithColorQuery.execute("invalidColor").isEmpty());
    }

    @Test
    void querySlotNumsWithColor() {
        final var slotNumsWithColorQuery = new SlotNumsWithColorQuery(testLotMixed);
        Assertions.assertEquals(slotNumsWithColorQuery.execute("white"), Set.of("1", "2"));
    }
}