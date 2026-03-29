package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * JUnit test class for EditCommand.
 * Ensures that student attributes are correctly updated and that invalid inputs are handled.
 */
public class EditCommandTest {
    private StudentList students;
    private Ui ui;

    /**
     * Sets up the test environment before each test case.
     * Initialises a {@code StudentList} with one sample student.
     */
    @BeforeEach
    public void setUp() {
        students = new StudentList();
        ui = new Ui();
        Student student = new Student("John Doe", "Primary 1", "Math");
        students.addStudent(student);
    }

    /**
     * Tests if the command successfully updates only the name while leaving other fields unchanged.
     */
    @Test
    public void execute_validIndexAndName_success() throws TutorSwiftException {
        EditCommand editCommand = new EditCommand(1, "Jane Doe", null, null);
        editCommand.execute(students, ui);

        Student editedStudent = students.getActiveStudent(0);
        assertEquals("Jane Doe", editedStudent.getName());
        assertEquals("Primary 1", editedStudent.getAcademicLevel());
        assertEquals("Math", editedStudent.getSubject());
    }

    /**
     * Tests if the command correctly throws a {@code TutorSwiftException} when an invalid index is provided.
     */
    @Test
    public void execute_invalidIndex_throwsException() {
        EditCommand editCommand = new EditCommand(0, "Jane Doe", "Primary 2", "Science");

        assertThrows(TutorSwiftException.class, () -> {
            editCommand.execute(students, ui);
        });
    }

    /**
     * Tests if the command successfully updates all student attributes.
     */
    @Test
    public void execute_allFieldsUpdated_success() throws TutorSwiftException {
        EditCommand editCommand = new EditCommand(1, "Bob", "Secondary 4", "Physics");
        editCommand.execute(students, ui);

        Student editedStudent = students.getActiveStudent(0);
        assertEquals("Bob", editedStudent.getName());
        assertEquals("Secondary 4", editedStudent.getAcademicLevel());
        assertEquals("Physics", editedStudent.getSubject());
    }
}
