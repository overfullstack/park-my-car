package com.gakshintala.parkmycar.usecases.lotqueries;

import com.gakshintala.parkmycar.ports.UseCase;
import com.gakshintala.parkmycar.ports.persistence.QueryLotStatus;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * gakshintala created on 11/3/19.
 */
@RequiredArgsConstructor
public class SlotNumsWithColorQuery implements UseCase<String, Collection<String>> {
    private final QueryLotStatus queryLotStatus;

    @Override
    public Collection<String> execute(String colorToQuery) {
        return queryLotStatus.status().entrySet().stream()
                .filter(entry -> entry.getValue().getColor().equalsIgnoreCase(colorToQuery))
                .map(Entry::getKey)
                .map(String::valueOf)
                .collect(Collectors.toSet());
    }
}
