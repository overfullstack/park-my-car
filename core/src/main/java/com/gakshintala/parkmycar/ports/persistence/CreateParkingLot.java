package com.gakshintala.parkmycar.ports.persistence;/* gakshintala created on 11/2/19 */

/**
 * gakshintala created on 11/2/19.
 */
@FunctionalInterface
public interface CreateParkingLot {
    boolean createParkingLot(int capacity);
}
