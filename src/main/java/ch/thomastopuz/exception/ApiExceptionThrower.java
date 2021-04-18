package ch.thomastopuz.exception;

import ch.thomastopuz.exception.BadRequest.ApiBadRequestException;
import ch.thomastopuz.exception.NotFound.ApiNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ApiExceptionThrower {

    public void throwBadRequestException(String msg) {
        throw new ApiBadRequestException(msg);
    }

    public void throwNotFoundException(String context, Long id) {
        throw new ApiNotFoundException(context + " not found", context, id);
    }
}
