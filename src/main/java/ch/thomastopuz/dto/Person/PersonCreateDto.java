package ch.thomastopuz.dto.Person;

import java.time.LocalDate;

public class PersonCreateDto extends PersonUpdateDto {
    private LocalDate dob;

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public boolean isValidForPOST() {
        return super.name!=null && super.surname!=null && super.email!=null && this.dob!=null;
    }
}
