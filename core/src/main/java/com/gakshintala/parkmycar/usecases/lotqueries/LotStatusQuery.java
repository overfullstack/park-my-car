package com.gakshintala.parkmycar.usecases.lotqueries;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.ports.UseCase;
import com.gakshintala.parkmycar.ports.persistence.QueryLotStatus;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * gakshintala created on 11/3/19.
 */
@RequiredArgsConstructor
public class LotStatusQuery implements UseCase<Void, Map<Integer, Car>> {
    private final QueryLotStatus queryLotStatus;

    @Override
    public Map<Integer, Car> execute(Void command) {
        return queryLotStatus.status();
    }
}
