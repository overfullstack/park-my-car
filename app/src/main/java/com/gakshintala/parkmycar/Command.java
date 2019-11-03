package com.gakshintala.parkmycar;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * gakshintala created on 11/2/19.
 */
@AllArgsConstructor
public enum Command {
    CREATE_PARKING_LOT(1),
    PARK(2),
    LEAVE(1),
    STATUS(0),
    REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR(1),
    SLOT_NUMBERS_FOR_CARS_WITH_COLOUR(1),
    SLOT_NUMBER_FOR_REGISTRATION_NUMBER(1);

    @Getter
    private int noOfArgsRequired;
}
