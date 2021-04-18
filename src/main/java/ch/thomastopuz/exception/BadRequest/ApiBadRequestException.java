package ch.thomastopuz.exception.BadRequest;

public class ApiBadRequestException extends RuntimeException {

    public ApiBadRequestException(String message) {
        super(message);
    }

    public ApiBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
