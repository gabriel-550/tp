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
 * JUnit test class for {@link UnarchiveCommand}.
 */
class UnarchiveCommandTest {
    private StudentList studentList;
    private Ui ui;

    @BeforeEach
    void setUp() {
        studentList = new StudentList();
        ui = new Ui();
        Student student = new Student("Archived Student", "Sec 4", "Math");
        student.setArchived(true);
        studentList.addStudent(student);
    }

    /**
     * Tests moving a student from archived list back to active list.
     */
    @Test
    void execute_validIndex_movesToActive() throws TutorSwiftException {
        assertEquals(1, studentList.getArchivedSize());
        assertEquals(0, studentList.getActiveSize());

        UnarchiveCommand command = new UnarchiveCommand(1);
        command.execute(studentList, ui);

        assertEquals(0, studentList.getArchivedSize(), "Archived list should be empty");
        assertEquals(1, studentList.getActiveSize(), "Active list should have 1 student");
        assertEquals("Archived Student", studentList.getActiveStudent(0).getName());
    }

    /**
     * Tests that unarchiving with an out-of-bounds index throws an exception.
     */
    @Test
    void execute_invalidIndex_throwsException() {
        UnarchiveCommand command = new UnarchiveCommand(99);
        assertThrows(TutorSwiftException.class, () -> command.execute(studentList, ui));
    }
}
