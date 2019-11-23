package com.gakshintala.parkmycar;

import com.gakshintala.parkmycar.exchange.ResultMapper;
import com.gakshintala.parkmycar.ports.UseCase;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * gakshintala created on 11/2/19.
 */
@UtilityClass
public class UseCaseExecutor {

    public static <InputT, ResultT> String execute(
            @NonNull UseCase<InputT, ResultT> useCase,
            InputT input,
            @NonNull ResultMapper<ResultT> outputMapper) {
        return outputMapper.fromResult(useCase.execute(input));
    }
}