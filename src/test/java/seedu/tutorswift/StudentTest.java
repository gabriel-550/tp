package seedu.tutorswift;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {

    @Test
    public void studentConstructor_validInput_correctAttributes() {
        Student student = new Student("Alex Tan", "Secondary 3", "Math");
        assertEquals("Alex Tan", student.getName());
        assertEquals("Secondary 3", student.getAcademicLevel());
        assertEquals("Math", student.getSubject());
    }

    @Test
    public void editStudent_partialUpdate_onlySpecifiedFieldsChanged() {
        Student student = new Student("Alex Tan", "Secondary 3", "Math");

        // edits with name and subject being null, results in only academicLevel field being updated
        student.editStudent(null, "Secondary 4", null);

        assertEquals("Alex Tan", student.getName());
        assertEquals("Secondary 4", student.getAcademicLevel());
        assertEquals("Math", student.getSubject());
    }

    @Test
    public void editStudent_fullUpdate_allFieldsChanged() {
        Student student = new Student("Alex Tan", "Secondary 3", "Math");

        // edits with all fields updated
        student.editStudent("Alex Lim", "Secondary 4", "Science");

        assertEquals("Alex Lim", student.getName());
        assertEquals("Secondary 4",  student.getAcademicLevel());
        assertEquals("Science", student.getSubject());
    }

    @Test
    public void editStudent_noUpdate_noFieldsChanged() {
        Student student = new Student("Alex Tan", "Secondary 3", "Math");

        //edits with all fields being null, results in unchanged student attributes
        student.editStudent(null, null, null);

        assertEquals("Alex Tan", student.getName());
        assertEquals("Secondary 3",  student.getAcademicLevel());
        assertEquals("Math", student.getSubject());
    }

}
