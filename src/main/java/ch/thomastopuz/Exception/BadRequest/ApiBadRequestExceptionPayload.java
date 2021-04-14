package ch.thomastopuz.Exception.BadRequest;

import ch.thomastopuz.Exception.ApiExceptionBasePayload;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiBadRequestExceptionPayload  extends ApiExceptionBasePayload {
    public ApiBadRequestExceptionPayload(String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime timestamp) {
        super(message, throwable, httpStatus, timestamp);
    }
}
