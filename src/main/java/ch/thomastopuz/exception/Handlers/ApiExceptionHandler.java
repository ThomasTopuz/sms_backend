package ch.thomastopuz.exception.Handlers;

import ch.thomastopuz.exception.BadRequest.ApiBadRequestException;
import ch.thomastopuz.exception.BadRequest.ApiBadRequestExceptionPayload;
import ch.thomastopuz.exception.NotFound.ApiNotFoundException;
import ch.thomastopuz.exception.NotFound.ApiNotFoundExceptionPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    // bad request
    @ExceptionHandler(value = {ApiBadRequestException.class})
    public ResponseEntity<Object> handleApiBadRequestException(ApiBadRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST; // status code: 400
        ApiBadRequestExceptionPayload apiException = new ApiBadRequestExceptionPayload(
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    // not found
    @ExceptionHandler(value = {ApiNotFoundException.class})
    public ResponseEntity<Object> handleApiNotFoundException(ApiNotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND; // status code: 404
        ApiNotFoundExceptionPayload apiException = new ApiNotFoundExceptionPayload(
                e.getMessage(),
                e.getEntity(),
                e.getId(),
                e,
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, notFound);
    }

}
