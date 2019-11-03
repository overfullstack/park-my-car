package com.gakshintala.parkmycar;

import com.gakshintala.parkmycar.exchange.ResultMapper;
import com.gakshintala.parkmycar.ports.UseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UseCaseExecutorTest {
    
    @Test
    void createParkingLotUseCaseExecution() {
        final var useCaseDummyInput = "useCaseDummyInput";
        final ResultMapper<String> resultMapper = String::toUpperCase;
        final UseCase<String, String> useCaseStub = command -> command;
        
        Assertions.assertEquals(UseCaseExecutor.executeForConsole(useCaseStub, useCaseDummyInput, resultMapper),
                useCaseDummyInput.toUpperCase());
    }
}