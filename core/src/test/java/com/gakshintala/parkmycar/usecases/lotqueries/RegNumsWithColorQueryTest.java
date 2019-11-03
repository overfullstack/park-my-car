package com.gakshintala.parkmycar.usecases.lotqueries;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.ports.persistence.QueryLotStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

class RegNumsWithColorQueryTest {
    private static final QueryLotStatus emptyLot = Map::of;
    private static final QueryLotStatus testLotMixed = () -> Map.of(
            1, new Car("regNo1", "white"),
            2, new Car("regNo2", "white"),
            3, new Car("regNo3", "black"),
            4, new Car("regNo4", "red")
    );

    @Test
    void queryEmptyLot() {
        final var regNumsWithColorQuery = new RegNumsWithColorQuery(emptyLot);
        Assertions.assertTrue(regNumsWithColorQuery.execute("white").isEmpty());
    }
    
    @Test
    void queryColorNotFound() {
        final var regNumsWithColorQuery = new RegNumsWithColorQuery(testLotMixed);
        Assertions.assertTrue(regNumsWithColorQuery.execute("invalidColor").isEmpty());
    }
    
    @Test
    void queryRegNumsWithColor() {
        final var regNumsWithColorQuery = new RegNumsWithColorQuery(testLotMixed);
        Assertions.assertEquals(regNumsWithColorQuery.execute("white"), Set.of("regNo1", "regNo2"));
    }
}