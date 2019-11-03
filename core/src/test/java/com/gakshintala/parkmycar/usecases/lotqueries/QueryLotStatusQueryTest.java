package com.gakshintala.parkmycar.usecases.lotqueries;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.ports.persistence.QueryLotStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class QueryLotStatusQueryTest {
    private static final Map<Integer, Car> TEST_LOT_STATUS = new ConcurrentHashMap<>();
    private static final QueryLotStatus queryLotStatus = () -> TEST_LOT_STATUS;
    
    @Test
    void getLotStatus() {
        final var lotStatusUseCase = new LotStatusQuery(queryLotStatus);
        Assertions.assertEquals(TEST_LOT_STATUS, lotStatusUseCase.execute(null));
    }
}