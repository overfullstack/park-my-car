package com.gakshintala.parkmycar;/* gakshintala created on 11/2/19 */

/**
 * gakshintala created on 11/2/19.
 */
@FunctionalInterface
public interface ResponseRenderer<ResultT> {
    String fromResult(ResultT result);
}
