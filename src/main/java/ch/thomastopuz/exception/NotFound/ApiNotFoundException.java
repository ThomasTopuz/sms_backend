package ch.thomastopuz.exception.NotFound;

public class ApiNotFoundException extends RuntimeException {
    private String entity;
    private Long id;

    public ApiNotFoundException(String message, String entity, Long id) {
        super(message);
        this.entity = entity;
        this.id = id;
    }

    public ApiNotFoundException(String message, Throwable cause, String entity, Long id) {
        super(message, cause);
        this.entity = entity;
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public Long getId() {
        return id;
    }
}
