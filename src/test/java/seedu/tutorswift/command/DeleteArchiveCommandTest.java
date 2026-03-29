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
 * JUnit test class for DeleteArchiveCommand.
 */
class DeleteArchiveCommandTest {
    private StudentList studentList;
    private Ui ui;

    @BeforeEach
    void setUp() {
        studentList = new StudentList();
        ui = new Ui();
        Student student = new Student("Archived Student", "Graduated", "None");
        student.setArchived(true);
        studentList.addStudent(student);
    }

    @Test
    void execute_validIndex_deletesFromArchive() throws TutorSwiftException {
        assertEquals(1, studentList.getArchivedSize());
        DeleteArchiveCommand command = new DeleteArchiveCommand(1);
        command.execute(studentList, ui);
        assertEquals(0, studentList.getArchivedSize(), "Archive should be empty after deletion");
    }

    @Test
    void execute_invalidIndex_throwsException() {
        DeleteArchiveCommand command = new DeleteArchiveCommand(2);
        assertThrows(TutorSwiftException.class, () -> command.execute(studentList, ui));
    }
}
