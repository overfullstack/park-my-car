package com.gakshintala.parkmycar.domain;

import lombok.NonNull;
import lombok.Value;

/*
 * gakshintala created on 11/1/19
 */
@Value
public class Car {
    @NonNull
    String registrationNumber;
    @NonNull
    String color;

    public Car(@NonNull String registrationNumber, @NonNull String color) {
        if (registrationNumber.isEmpty() || color.isEmpty()) {
            throw new RuntimeException("Car properties can't be empty");
        }
        this.registrationNumber = registrationNumber;
        this.color = color;
    }
}
