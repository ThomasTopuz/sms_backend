package ch.thomastopuz.exception.BadRequest;

import ch.thomastopuz.exception.ApiExceptionBasePayload;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiBadRequestExceptionPayload  extends ApiExceptionBasePayload {
    public ApiBadRequestExceptionPayload(String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime timestamp) {
        super(message, throwable, httpStatus, timestamp);
    }
}
