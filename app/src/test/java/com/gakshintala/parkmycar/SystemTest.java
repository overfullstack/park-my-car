package com.gakshintala.parkmycar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class SystemTest {
    public static final String COMMAND_INPUT_FILE_PATH = "com/gakshintala/parkmycar/command-input.txt";
    public static final String EXPECTED_OUTPUT_FILE_PATH = "com/gakshintala/parkmycar/expected-output.txt";

    @ParameterizedTest
    @MethodSource("parameters")
    void createParkingLot(String inputCommand, String expectedOutput) {
        if (inputCommand.equalsIgnoreCase(Command.STATUS.toString())) {
            Assertions.assertEquals(expectedOutput,
                    String.join("\\n", CommandController.executeCommand(inputCommand).split("\r\n")));
        } else {
            Assertions.assertEquals(expectedOutput, CommandController.executeCommand(inputCommand));
        }

    }

    private static Stream<Arguments> parameters() throws URISyntaxException, IOException {
        final var inputCommands =
                Files.readString(Paths.get(ClassLoader.getSystemResource(COMMAND_INPUT_FILE_PATH).toURI()))
                        .lines().collect(Collectors.toList());
        final var expectedResults =
                Files.readString(Paths.get(ClassLoader.getSystemResource(EXPECTED_OUTPUT_FILE_PATH).toURI()))
                        .lines().collect(Collectors.toList());

        return IntStream.range(0, Math.min(inputCommands.size(), expectedResults.size()))
                .mapToObj(index -> Arguments.of(inputCommands.get(index), expectedResults.get(index)));
    }
}