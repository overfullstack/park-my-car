package com.gakshintala.parkmycar;/* gakshintala created on 11/2/19 */

public interface ResponseRenderer<ResultT> {
    String fromResult(ResultT result);
}
