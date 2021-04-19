package ch.thomastopuz.exception.NotFound;

/**
 * the actual Api not found exception throwable
 */
public class ApiNotFoundException extends RuntimeException {
    /**
     * the entity of the not found exception
     */
    private String entity;
    /**
     * the provided id
     */
    private Long id;

    /**
     * api not found exception constructor
     *
     * @param message the error message
     * @param entity  the entity of the not found exception
     * @param id      the provided id
     */
    public ApiNotFoundException(String message, String entity, Long id) {
        super(message);
        this.entity = entity;
        this.id = id;
    }

    /**
     * api not found exception constuctor
     *
     * @param message the error message
     * @param cause   the cause of the exception
     * @param entity  the entity of the not found exception
     * @param id      the provided id
     */
    public ApiNotFoundException(String message, Throwable cause, String entity, Long id) {
        super(message, cause);
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
    public Long getId() {
        return id;
    }
}
