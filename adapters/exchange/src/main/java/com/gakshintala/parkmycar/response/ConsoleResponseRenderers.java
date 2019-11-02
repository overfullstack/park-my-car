package com.gakshintala.parkmycar.response;

import com.gakshintala.parkmycar.ResponseRenderer;
import com.gakshintala.parkmycar.createparkinglot.CreateParkingLotResult;
import lombok.experimental.UtilityClass;

/*
 * gakshintala created on 11/2/19
 */
@UtilityClass
public class ConsoleResponseRenderers {
    ResponseRenderer<CreateParkingLotResult> createParkingLotResponseRenderer = result ->
            result.isSuccess()
                    ? String.format("Created a parking lot with %d slots", result.getCreatedCapacity())
                    : "Something went wrong, parking lot could not be created";
}
