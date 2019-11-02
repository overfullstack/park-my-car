package com.gakshintala.parkmycar;

import com.gakshintala.parkmycar.exchange.ResultMapper;
import com.gakshintala.parkmycar.ports.UseCase;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.function.Consumer;

/**
 * gakshintala created on 11/2/19.
 */
@UtilityClass
public class UseCaseExecutor {
    static Consumer<String> consolePrinter = System.out::println;
    
    public static <InputT, ResultT> void executeForConsole(
            @NonNull UseCase<InputT, ResultT> useCase,
            @NonNull InputT input,
            @NonNull ResultMapper<ResultT> outputMapper) {
        consolePrinter.accept(
                outputMapper.fromResult(useCase.execute(input))
        );
    }
}