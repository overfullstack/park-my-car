package com.gakshintala.parkmycar.ports.persistence;/* gakshintala created on 11/2/19 */

import com.gakshintala.parkmycar.domain.Car;
import com.gakshintala.parkmycar.usecases.parkcar.ParkCarResult;

/**
 * gakshintala created on 11/2/19.
 */
@FunctionalInterface
public interface ParkCar {
    ParkCarResult park(Car car);
}
