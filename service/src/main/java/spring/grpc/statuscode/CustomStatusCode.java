package spring.grpc.statuscode;

import spring.grpc.exception.StatusCode;

/**
 * Created by Ranjith @ 2 Jan' 2018
 */
public interface CustomStatusCode {
    StatusCode ERR_REQUEST = new StatusCode(1000, "ERR_REQUEST");
    StatusCode NULL_ENTRY = new StatusCode(1001, "NULL_ENTRY");
}
