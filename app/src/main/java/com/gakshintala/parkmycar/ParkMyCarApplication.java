package com.gakshintala.parkmycar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * gakshintala created on 11/2/19.
 */
public class ParkMyCarApplication {

    static final Consumer<String> consolePrinter = System.out::println;

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            executeFromFile(args[0]);
        } else {
            startInteractive();
        }
    }

    private static void executeFromFile(String arg) throws IOException {
        consolePrinter.accept(
                Files.readString(Paths.get(arg))
                        .lines()
                        .map(String::trim)
                        .map(CommandController::executeCommand)
                        .collect(Collectors.joining("\n")));
    }

    private static void startInteractive() {
        final var scanner = new Scanner(System.in);
        while (true) {
            final var lineOfCommand = scanner.nextLine().trim();
            if (lineOfCommand.equalsIgnoreCase("exit")) {
                break;
            }
            consolePrinter.accept(CommandController.executeCommand(lineOfCommand));
        }
    }

}
