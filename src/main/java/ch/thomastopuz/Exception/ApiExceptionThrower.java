package ch.thomastopuz.Exception;

import ch.thomastopuz.Exception.BadRequest.ApiBadRequestException;
import ch.thomastopuz.Exception.NotFound.ApiNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ApiExceptionThrower {

    public void throwBadRequestException(String msg) {
        throw new ApiBadRequestException(msg);
    }

    public void throwBadRequestException() {
        throw new ApiBadRequestException("Bad request!");
    }

    public void throwNotFoundException(String context, Long id) {
        throw new ApiNotFoundException(context + "not found", context, id);
    }
}
