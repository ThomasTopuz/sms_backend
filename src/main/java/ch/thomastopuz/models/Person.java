package ch.thomastopuz.models;

import java.time.LocalDate;

public interface Person {

    long getId();

    void setId(long id);

    String getName();

    void setName(String name);

    String getSurname();

    void setSurname(String surname);

    String getEmail();

    void setEmail(String email);

    LocalDate getDob();

    Integer getAge();
}
