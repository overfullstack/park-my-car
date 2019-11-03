package com.gakshintala.parkmycar.usecases.lotstatus;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.ports.UseCase;
import com.gakshintala.parkmycar.ports.persistence.LotStatus;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * gakshintala created on 11/3/19.
 */
@RequiredArgsConstructor
public class LotStatusUseCase implements UseCase<Void, Map<Integer, Car>> {
    private final LotStatus lotStatus;

    @Override
    public Map<Integer, Car> execute(Void command) {
        return lotStatus.status();
    }
}
