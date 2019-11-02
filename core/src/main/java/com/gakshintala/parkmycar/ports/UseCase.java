package com.gakshintala.parkmycar.ports;

/**
 * gakshintala created on 11/2/19.
 */
@FunctionalInterface
public interface UseCase<CommandT, ResultT> {
    ResultT execute(CommandT command);
}
