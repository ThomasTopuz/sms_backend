package ch.thomastopuz.Exception.NotFound;

import ch.thomastopuz.Exception.ApiExceptionBasePayload;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiNotFoundExceptionPayload extends ApiExceptionBasePayload {
    private String entity;
    private long id;

    public ApiNotFoundExceptionPayload(String message, String entity, Long id, Throwable throwable, HttpStatus httpStatus, ZonedDateTime timestamp) {
        super(message, throwable, httpStatus, timestamp);
        this.entity = entity;
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public long getId() {
        return id;
    }
}
