package spring.grpc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by Ranjith @ 2 Jan' 2018
 */
@Getter
@ToString
@AllArgsConstructor
public class StatusCode {
    private int code;
    private String internalKey;
}
