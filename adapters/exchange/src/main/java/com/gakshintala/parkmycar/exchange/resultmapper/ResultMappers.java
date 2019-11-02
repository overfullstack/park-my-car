package com.gakshintala.parkmycar.exchange.resultmapper;

import com.gakshintala.parkmycar.createparkinglot.CreateParkingLotResult;
import com.gakshintala.parkmycar.exchange.ResultMapper;
import lombok.experimental.UtilityClass;

/*
 * gakshintala created on 11/2/19
 */
@UtilityClass
public class ResultMappers {
    public ResultMapper<CreateParkingLotResult> createParkingLotResultMapper = result ->
            result.isSuccess()
                    ? String.format("Created a parking lot with %d slots", result.getCreatedCapacity())
                    : "Something went wrong, parking lot could not be created";
}
