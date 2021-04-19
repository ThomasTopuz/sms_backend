package ch.thomastopuz.exception.BadRequest;

import ch.thomastopuz.exception.ApiExceptionBasePayload;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * the payload for the bad request exception, i need this separate class because of the separate exception handlers
 */
public class ApiBadRequestExceptionPayload  extends ApiExceptionBasePayload {
    /**
     * the bad request exception payload constructor
     * @param message the error message
     * @param httpStatus the httpStatus (BAD_REQUEST, NOT_FOUND, FORBIDDEN...)
     * @param timestamp timestamp of the exception
     */
    public ApiBadRequestExceptionPayload(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        super(message, httpStatus, timestamp);
    }
}
