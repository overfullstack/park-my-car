package com.gakshintala.parkmycar.exchange;/* gakshintala created on 11/2/19 */

/**
 * gakshintala created on 11/2/19.
 */
@FunctionalInterface
public interface ResultMapper<ResultT> {
    String fromResult(ResultT result);
}
