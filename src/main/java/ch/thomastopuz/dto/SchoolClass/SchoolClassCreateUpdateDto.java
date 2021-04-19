package ch.thomastopuz.dto.SchoolClass;

/**
 * DTO used to create/update a schoolclass
 */
public class SchoolClassCreateUpdateDto {
    /**
     * the schoolclass name
     */
    private String name;
    /**
     * the schoolclass teacher id
     */
    private Long teacherId; // will get the teacher and set as the teacher for this schoolclass

    /**
     * name getter
     *
     * @return the schoolclass name
     */
    public String getName() {
        return name;
    }

    /**
     * teacher id getter
     *
     * @return the schoolclass teacher id
     */
    public Long getTeacherId() {
        return teacherId;
    }
}