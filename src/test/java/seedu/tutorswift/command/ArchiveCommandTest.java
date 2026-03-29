package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// @@author Alex-Chen-666
/**
 * JUnit test class for ArchiveCommand.
 */
class ArchiveCommandTest {
    private StudentList studentList;
    private Ui ui;

    @BeforeEach
    void setUp() {
        studentList = new StudentList();
        ui = new Ui();
        studentList.addStudent(new Student("John Doe", "Sec 4", "Math"));
    }

    @Test
    void execute_validIndex_movesToArchive() throws TutorSwiftException {
        ArchiveCommand command = new ArchiveCommand(1);
        command.execute(studentList, ui);

        assertEquals(0, studentList.getActiveSize(), "Active list should be empty");
        assertEquals(1, studentList.getArchivedSize(), "Archived list should have 1 student");
        assertEquals("John Doe", studentList.getArchivedStudent(0).getName());
    }

    @Test
    void execute_invalidIndex_throwsException() {
        ArchiveCommand command = new ArchiveCommand(99);
        assertThrows(TutorSwiftException.class, () -> command.execute(studentList, ui));
    }
}
// @@author
