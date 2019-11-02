package com.gakshintala.parkmycar.response;

import com.gakshintala.parkmycar.ResponseRenderer;
import com.gakshintala.parkmycar.commands.createparkinglot.CreateParkingLotResult;
import lombok.experimental.UtilityClass;

/*
 * gakshintala created on 11/2/19
 */
@UtilityClass
public class ConsoleResponseRenderers {
    ResponseRenderer<CreateParkingLotResult> createParkingLotResponseRenderer = result -> null; 
}
