package ch.thomastopuz.exception;

import ch.thomastopuz.exception.BadRequest.ApiBadRequestException;
import ch.thomastopuz.exception.NotFound.ApiNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Injectable utility class for throwing API exceptions
 */
@Component
public class ApiExceptionThrower {

    /**
     * method that throws a bad request api exception
     *
     * @param msg bad request message
     */
    public void throwBadRequestException(String msg) {
        throw new ApiBadRequestException(msg);
    }

    /**
     * method that throws a not found api exception
     *
     * @param context the context (entity)
     * @param id      the provided id
     */
    public void throwNotFoundException(String context, Long id) {
        throw new ApiNotFoundException(context + " not found", context, id);
    }
}
