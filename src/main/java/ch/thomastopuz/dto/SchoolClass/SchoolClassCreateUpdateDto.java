package ch.thomastopuz.dto.SchoolClass;

public class SchoolClassCreateUpdateDto {
    private String name;
    private Long teacherId; // will get the teacher and set as the teacher for this schoolclass

    public String getName() {
        return name;
    }

    public Long getTeacherId() {
        return teacherId;
    }
}