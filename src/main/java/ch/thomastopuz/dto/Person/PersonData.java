package ch.thomastopuz.dto.Person;

/**
 * Person data abstract class, extended by the person DTo's
 */
public abstract class PersonData {
    /**
     * the person name
     */
    protected String name;
    /**
     * the person surname
     */
    protected String surname;
    /**
     * the person email
     */
    protected String email;

    /**
     * the person's name getter
     *
     * @return the person name
     */
    public String getName() {
        return name;
    }

    /**
     * the person's surname getter
     *
     * @return the person surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * the person's email getter
     *
     * @return the person email
     */
    public String getEmail() {
        return email;
    }
}
