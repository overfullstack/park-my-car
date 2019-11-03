package com.gakshintala.parkmycar.usecases.lotqueries;

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.ports.UseCase;
import com.gakshintala.parkmycar.ports.persistence.QueryLotStatus;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * gakshintala created on 11/3/19.
 */
@RequiredArgsConstructor
public class RegNumsWithColorQuery implements UseCase<String, Collection<String>> {
    private final QueryLotStatus queryLotStatus;

    @Override
    public Collection<String> execute(String colorToQuery) {
        return queryLotStatus.status().values().stream()
                .filter(car -> car.getColor().equalsIgnoreCase(colorToQuery))
                .map(Car::getRegistrationNumber)
                .collect(Collectors.toSet());
    }
}
