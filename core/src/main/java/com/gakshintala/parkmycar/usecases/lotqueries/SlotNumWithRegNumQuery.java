package com.gakshintala.parkmycar.usecases.lotqueries;

import com.gakshintala.parkmycar.ports.UseCase;
import com.gakshintala.parkmycar.ports.persistence.QueryLotStatus;
import lombok.RequiredArgsConstructor;

import java.util.Map.Entry;
import java.util.Optional;

/**
 * gakshintala created on 11/3/19.
 */
@RequiredArgsConstructor
public class SlotNumWithRegNumQuery implements UseCase<String, Optional<Integer>> {
    private final QueryLotStatus queryLotStatus;

    @Override
    public Optional<Integer> execute(String regNumToQuery) {
        return queryLotStatus.status().entrySet().stream()
                .filter(entry -> entry.getValue().getRegistrationNumber().equalsIgnoreCase(regNumToQuery))
                .findAny()
                .map(Entry::getKey);
    }
}
