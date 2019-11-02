package com.gakshintala.parkmycar;

import com.gakshintala.parkmycar.exchange.request.CreateParkingLotRequestParser;
import com.gakshintala.parkmycar.exchange.resultmapper.ResultMappers;

import java.util.Scanner;

import static com.gakshintala.parkmycar.Config.createParkingLotUseCase;

/**
 * gakshintala created on 11/2/19.
 */
public class ParkMyCarApplication {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        while (true) {
            final var commandArgs = scanner.nextLine().split(" ");
            final var command = Command.valueOf(commandArgs[0].toUpperCase());
            switch (command) {
                case CREATE_PARKING_LOT:
                    UseCaseExecutor.executeForConsole(
                            createParkingLotUseCase(),
                            new CreateParkingLotRequestParser(commandArgs[1]).toCommand(),
                            ResultMappers.createParkingLotResultMapper);
                    break;
                case EXIT:
                    return;
            }
        }

    }
}
