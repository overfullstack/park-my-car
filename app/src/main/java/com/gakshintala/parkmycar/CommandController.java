package com.gakshintala.parkmycar;

import com.gakshintala.parkmycar.exchange.request.CreateParkingLotRequestParser;
import com.gakshintala.parkmycar.exchange.request.LeaveSlotRequestParser;
import com.gakshintala.parkmycar.exchange.request.ParkCarRequestParser;
import com.gakshintala.parkmycar.exchange.resultmapper.ResultMappers;
import lombok.experimental.UtilityClass;

import java.util.stream.Stream;

import static com.gakshintala.parkmycar.Command.CREATE_PARKING_LOT;
import static com.gakshintala.parkmycar.Config.createParkingLotUseCase;
import static com.gakshintala.parkmycar.Config.leaveSlot;
import static com.gakshintala.parkmycar.Config.lotStatusQuery;
import static com.gakshintala.parkmycar.Config.parkCarUseCase;
import static com.gakshintala.parkmycar.Config.regNumsWithColorQuery;
import static com.gakshintala.parkmycar.Config.slotNumsWithColorQuery;
import static com.gakshintala.parkmycar.Config.slotNumsWithRegNumQuery;

/**
 * gakshintala created on 11/3/19.
 */
@UtilityClass
public class CommandController {

    public static final String PARKING_LOT_IS_NOT_YET_CREATED = "Parking lot is not yet Created";
    public static final String INVALID_COMMAND = "Invalid Command";

    static String executeCommand(final String lineOfCommand) {
        final var commandWithArgs = lineOfCommand.split(" ");
        if (isValidCommand(commandWithArgs[0])) {
            final var command = Command.valueOf(commandWithArgs[0].trim().toUpperCase());
            if (command != CREATE_PARKING_LOT && isParkingLotNotCreated()) {
                return PARKING_LOT_IS_NOT_YET_CREATED;
            }
            if (validateArgumentCount(commandWithArgs.length - 1, command.getNoOfArgsRequired())) {
                return String.format("'%s' command needs %d ", commandWithArgs[0], command.getNoOfArgsRequired())
                        + (command.getNoOfArgsRequired() > 1 ? "arguments" : "argument");
            }
            return getResultToRender(commandWithArgs, command);
        } else {
            return INVALID_COMMAND;
        }
    }

    private static boolean isValidCommand(String inputCommand) {
        return Stream.of(Command.values()).map(Command::name)
                .anyMatch(command -> command.equalsIgnoreCase(inputCommand));
    }

    private static boolean isParkingLotNotCreated() {
        return lotStatusQuery().execute(null) == null;
    }

    private static boolean validateArgumentCount(int argLengthForCommand, int noOfArgsRequiredForCommand) {
        return argLengthForCommand != noOfArgsRequiredForCommand;
    }

    static String getResultToRender(String[] commandWithArgs, Command command) {
        switch (command) {
            case CREATE_PARKING_LOT:
                return UseCaseExecutor.execute(
                        createParkingLotUseCase(),
                        new CreateParkingLotRequestParser(commandWithArgs[1]).toCommand(),
                        ResultMappers.createParkingLotResultMapper);
            case PARK:
                return UseCaseExecutor.execute(
                        parkCarUseCase(),
                        new ParkCarRequestParser(commandWithArgs[1], commandWithArgs[2]).toCommand(),
                        ResultMappers.parkCarResultMapper);
            case LEAVE:
                return UseCaseExecutor.execute(
                        leaveSlot(),
                        new LeaveSlotRequestParser(commandWithArgs[1]).toCommand(),
                        ResultMappers.leaveSlotResultMapper);
            case STATUS:
                return UseCaseExecutor.execute(
                        lotStatusQuery(),
                        null,
                        ResultMappers.lotStatusMapper);
            case REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
                return UseCaseExecutor.execute(
                        regNumsWithColorQuery(),
                        commandWithArgs[1],
                        ResultMappers.collectionToStringCommaSeparatedMapper);
            case SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
                return UseCaseExecutor.execute(
                        slotNumsWithColorQuery(),
                        commandWithArgs[1],
                        ResultMappers.collectionToStringCommaSeparatedMapper);
            case SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
                return UseCaseExecutor.execute(
                        slotNumsWithRegNumQuery(),
                        commandWithArgs[1],
                        ResultMappers.slotNumWithRegNumResultMapper);
            default:
                return "Command not supported!";
        }
    }
}
