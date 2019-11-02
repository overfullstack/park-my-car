package com.gakshintala.parkmycar;

import com.gakshintala.parkmycar.exchange.ResultMapper;
import com.gakshintala.parkmycar.ports.UseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

class UseCaseExecutorTest {

    public static final String EXPECTED_STRING = "expected String";
    private static String consoleResult;
    private static final Consumer<String> consolePrinterStub = strToPrint -> consoleResult = strToPrint;
    private static final UseCase<String, String> dummyUseCase = ignore -> "ignore";
    
    @Test
    void createParkingLotUseCaseExecution() {
        UseCaseExecutor.consolePrinter = consolePrinterStub;
        ResultMapper<String> resultMapper = result -> EXPECTED_STRING;
        UseCaseExecutor.executeForConsole(dummyUseCase, "ignore", resultMapper);
        Assertions.assertEquals(consoleResult, EXPECTED_STRING);
    }
}