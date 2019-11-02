package com.gakshintala.parkmycar.parkcar;

import com.gakshintala.parkmycar.domain.CarParkStatus;
import lombok.NonNull;
import lombok.Value;

/*
 * gakshintala created on 11/2/19
 */
@Value
public class ParkCarResult {
    @NonNull
    CarParkStatus carParkStatus;
    int slotId;
}
