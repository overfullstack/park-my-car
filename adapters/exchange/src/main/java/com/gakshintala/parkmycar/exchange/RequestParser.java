package com.gakshintala.parkmycar.exchange;

/*
 * gakshintala created on 11/2/19
 */
public interface RequestParser<CommandT> {
    CommandT toCommand();
}
