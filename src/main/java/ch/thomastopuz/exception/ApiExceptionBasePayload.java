package ch.thomastopuz.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * base api exception payload
 */
public class ApiExceptionBasePayload {
    /**
     * the error message
     */
    private final String message;
    /**
     * the httpStatus (BAD_REQUEST, NOT_FOUND, FORBIDDEN...)
     */
    private final HttpStatus httpStatus;
    /**
     * timestamp of the exception
     */
    private final ZonedDateTime timestamp;

    /**
     * api exception base payload constructor
     *
     * @param message    the error message
     * @param httpStatus the httpStatus (BAD_REQUEST, NOT_FOUND, FORBIDDEN...)
     * @param timestamp  timestamp of the exception
     */
    public ApiExceptionBasePayload(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    /**
     * error message getter
     *
     * @return the error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * http status getter
     *
     * @return the httpStatus (BAD_REQUEST, NOT_FOUND, FORBIDDEN...)
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * timestamp getter
     *
     * @return timestamp of the exception
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
