package com.gakshintala.parkmycar;

/*
 * gakshintala created on 11/2/19
 */
public interface Request<CommandT> {
    CommandT toCommand();
}
