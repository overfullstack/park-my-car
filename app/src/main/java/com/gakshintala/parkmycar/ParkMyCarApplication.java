package com.gakshintala.parkmycar;

import com.gakshintala.parkmycar.exchange.request.CreateParkingLotRequestParser;
import com.gakshintala.parkmycar.exchange.request.LeaveSlotRequestParser;
import com.gakshintala.parkmycar.exchange.request.ParkCarRequestParser;
import com.gakshintala.parkmycar.exchange.resultmapper.ResultMappers;

import java.util.Scanner;

import static com.gakshintala.parkmycar.Config.createParkingLotUseCase;
import static com.gakshintala.parkmycar.Config.leaveSlot;
import static com.gakshintala.parkmycar.Config.lotStatusQuery;
import static com.gakshintala.parkmycar.Config.parkCarUseCase;
import static com.gakshintala.parkmycar.Config.regNumsWithColorQuery;
import static com.gakshintala.parkmycar.Config.slotNumsWithColorQuery;
import static com.gakshintala.parkmycar.Config.slotNumsWithRegNumQuery;

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
                case PARK:
                    UseCaseExecutor.executeForConsole(
                            parkCarUseCase(),
                            new ParkCarRequestParser(commandArgs[1], commandArgs[2]).toCommand(),
                            ResultMappers.parkCarResultMapper);
                    break;
                case LEAVE:
                    UseCaseExecutor.executeForConsole(
                            leaveSlot(),
                            new LeaveSlotRequestParser(commandArgs[1]).toCommand(),
                            ResultMappers.leaveSlotResultMapper);
                    break;
                case STATUS:
                    UseCaseExecutor.executeForConsole(
                            lotStatusQuery(),
                            null,
                            ResultMappers.lotStatusMapper);
                    break;
                case REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
                    UseCaseExecutor.executeForConsole(
                            regNumsWithColorQuery(),
                            commandArgs[1],
                            ResultMappers.collectionToStringCommaSeparatedMapper);
                    break;
                case SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
                    UseCaseExecutor.executeForConsole(
                            slotNumsWithColorQuery(),
                            commandArgs[1],
                            ResultMappers.collectionToStringCommaSeparatedMapper);
                    break;
                case SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
                    UseCaseExecutor.executeForConsole(
                            slotNumsWithRegNumQuery(),
                            commandArgs[1],
                            ResultMappers.slotNumWithRegNumResultMapper);
                    break;
                case EXIT:
                    return;
            }
        }

    }
}
