package spring.grpc.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Ranjith @ 2 Jan' 2018
 */
@Controller
@ControllerAdvice
public class ExceptionHandler {

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Error implements Serializable {

        private static final long serialVersionUID = -2019765651892182299L;

        private Boolean status;

        private HttpStatus httpStatus;

        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private LocalDateTime timestamp;

        private String message;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = CustomException.class)
    public Error handleBaseException(CustomException ex) {
        return error(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public Error handleException(Exception ex) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private Error error(HttpStatus status, String message) {
        return Error.builder()
                .httpStatus(status)
                .message(message)
                .status(Boolean.FALSE)
                .build();
    }

}
