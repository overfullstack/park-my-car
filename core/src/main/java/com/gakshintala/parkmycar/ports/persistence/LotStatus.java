package com.gakshintala.parkmycar.ports.persistence;

import com.gakshintala.parkmycar.domain.Car;

import java.util.Map;

/**
 * gakshintala created on 11/3/19.
 */
@FunctionalInterface
public interface LotStatus {
    Map<Integer, Car> status();
}
