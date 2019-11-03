package com.gakshintala.parkmycar.usecases.lotstatus;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.ports.persistence.LotStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class LotStatusUseCaseTest {
    private static final Map<Integer, Car> TEST_LOT_STATUS = new ConcurrentHashMap<>();
    private static final LotStatus lotStatus = () -> TEST_LOT_STATUS;
    
    @Test
    void getLotStatus() {
        final var lotStatusUseCase = new LotStatusUseCase(lotStatus);
        Assertions.assertEquals(TEST_LOT_STATUS, lotStatusUseCase.execute(null));
    }
}