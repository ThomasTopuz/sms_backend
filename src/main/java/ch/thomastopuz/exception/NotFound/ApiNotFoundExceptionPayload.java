package ch.thomastopuz.exception.NotFound;

import ch.thomastopuz.exception.ApiExceptionBasePayload;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * class that represents the payload for the not found api exception
 */
public class ApiNotFoundExceptionPayload extends ApiExceptionBasePayload {
    /**
     * the entity of the not found exception
     */
    private String entity;
    /**
     * the provided id
     */
    private long id;

    /**
     * ApiNotFoundExceptionPayload constructor
     *
     * @param message    the error message
     * @param entity     the entity of the not found exception
     * @param id         the provided id
     * @param httpStatus the httpStatus (BAD_REQUEST, NOT_FOUND, FORBIDDEN...)
     * @param timestamp  timestamp of the exception
     */
    public ApiNotFoundExceptionPayload(String message, String entity, Long id, HttpStatus httpStatus, ZonedDateTime timestamp) {
        super(message, httpStatus, timestamp);
        this.entity = entity;
        this.id = id;
    }

    /**
     * the entity getter
     *
     * @return the entity of the not found exception
     */
    public String getEntity() {
        return entity;
    }

    /**
     * the id getter
     *
     * @return the provided id
     */
    public long getId() {
        return id;
    }
}
