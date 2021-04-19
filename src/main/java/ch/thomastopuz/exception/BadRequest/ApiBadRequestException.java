package ch.thomastopuz.exception.BadRequest;

/**
 * the actual api bad request exception throwable
 */
public class ApiBadRequestException extends RuntimeException {

    /**
     * api bad request exception constructor
     *
     * @param message the error message
     */
    public ApiBadRequestException(String message) {
        super(message);
    }

    /**
     * api bad request exception constructor
     *
     * @param message the error message
     * @param cause   the cause of the exception
     */
    public ApiBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
