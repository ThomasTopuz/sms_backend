package ch.thomastopuz.dto.Person;

import java.time.LocalDate;

/**
 * DTO used to check the payload for creating a student or teacher, extends PersonData
 */
public final class PersonCreateDto extends PersonData {
    /**
     * the person's dob (date of birth)
     */
    private LocalDate dob;

    /**
     * the person's dob getter
     *
     * @return dob (date of birth)
     */
    public LocalDate getDob() {
        return dob;
    }

    /**
     * method to check if dto has not missing properties
     *
     * @return boolean if the dto is eligible for POST
     */
    public boolean isValidForPOST() {
        return super.name != null && super.surname != null && super.email != null && this.dob != null;
    }
}
