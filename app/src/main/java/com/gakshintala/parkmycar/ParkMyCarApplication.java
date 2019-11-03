package com.gakshintala.parkmycar;

import com.gakshintala.parkmycar.exchange.request.CreateParkingLotRequestParser;
import com.gakshintala.parkmycar.exchange.request.LeaveSlotRequestParser;
import com.gakshintala.parkmycar.exchange.request.ParkCarRequestParser;
import com.gakshintala.parkmycar.exchange.resultmapper.ResultMappers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static com.gakshintala.parkmycar.Command.CREATE_PARKING_LOT;
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
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            final var commandsFromFile = Files.readString(Paths.get(args[0])).split("\n");
            for (var lineOfCommand : commandsFromFile) {
                executeCommand(lineOfCommand.trim());
            }
        } else {
            startInteractive();
        }
    }

    private static void startInteractive() {
        var scanner = new Scanner(System.in);
        while (true) {
            final var lineOfCommand = scanner.nextLine().trim();
            if (lineOfCommand.equalsIgnoreCase("exit")) {
                break;
            }
            executeCommand(lineOfCommand);
        }
    }

    private static void executeCommand(String lineOfCommand) {
        final var commandWithArgs = lineOfCommand.split(" ");
        final var command = Command.valueOf(commandWithArgs[0].toUpperCase());
        
        if (command != CREATE_PARKING_LOT && isParkingLotNotCreated()) {
            System.out.println("Parking lot is not yet Created");
            return;
        }
        if (validateArguments(commandWithArgs.length - 1, command.getNoOfArgsRequired())) {
            System.out.println(String.format("'%s' command needs %d arguments",
                    command.toString().toLowerCase(), command.getNoOfArgsRequired()));
            return;
        }
        
        switch (command) {
            case CREATE_PARKING_LOT:
                UseCaseExecutor.executeForConsole(
                        createParkingLotUseCase(),
                        new CreateParkingLotRequestParser(commandWithArgs[1]).toCommand(),
                        ResultMappers.createParkingLotResultMapper);
                break;
            case PARK:
                UseCaseExecutor.executeForConsole(
                        parkCarUseCase(),
                        new ParkCarRequestParser(commandWithArgs[1], commandWithArgs[2]).toCommand(),
                        ResultMappers.parkCarResultMapper);
                break;
            case LEAVE:
                UseCaseExecutor.executeForConsole(
                        leaveSlot(),
                        new LeaveSlotRequestParser(commandWithArgs[1]).toCommand(),
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
                        commandWithArgs[1],
                        ResultMappers.collectionToStringCommaSeparatedMapper);
                break;
            case SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
                UseCaseExecutor.executeForConsole(
                        slotNumsWithColorQuery(),
                        commandWithArgs[1],
                        ResultMappers.collectionToStringCommaSeparatedMapper);
                break;
            case SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
                UseCaseExecutor.executeForConsole(
                        slotNumsWithRegNumQuery(),
                        commandWithArgs[1],
                        ResultMappers.slotNumWithRegNumResultMapper);
                break;
            default:
                System.out.println("Command not supported!");
        }
    }

    private static boolean isParkingLotNotCreated() {
        return lotStatusQuery().execute(null) == null;
    }

    private static boolean validateArguments(int argLengthForCommand, int noOfArgsRequiredForCommand) {
        return argLengthForCommand != noOfArgsRequiredForCommand;
    }
}
