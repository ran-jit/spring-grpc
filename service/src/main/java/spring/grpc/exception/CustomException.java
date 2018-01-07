package spring.grpc.exception;

/**
 * Created by Ranjith @ 2 Jan' 2018
 */
public class CustomException extends Exception {

    private int code;
    private StatusCode statusCode;

    public CustomException(String message, int code) {
        super(message);
        this.code = code;
    }

    public CustomException(String message, int code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public CustomException(StatusCode err) {
        this(err.getInternalKey(), err.getCode());
        this.statusCode = err;
    }

    public CustomException(StatusCode err, Throwable cause) {
        this(err.getInternalKey(), err.getCode(), cause);
        this.statusCode = err;
    }

}
