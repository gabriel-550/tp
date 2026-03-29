package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test class for {@link ListArchiveCommand}.
 */
class ListArchiveCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private StudentList studentList;
    private Ui ui;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        studentList = new StudentList();
        ui = new Ui();
    }

    /**
     * Tests output when the archive is empty.
     */
    @Test
    void execute_emptyArchive_showsEmptyMessage() {
        ListArchiveCommand command = new ListArchiveCommand();
        command.execute(studentList, ui);

        assertTrue(outContent.toString().contains("Your archive is currently empty."));
    }

    /**
     * Tests output when the archive contains students.
     */
    @Test
    void execute_withArchivedStudents_showsStudents() {
        Student s1 = new Student("Old Student", "Graduated", "History");
        s1.setArchived(true);
        studentList.addStudent(s1);

        ListArchiveCommand command = new ListArchiveCommand();
        command.execute(studentList, ui);

        String output = outContent.toString();
        assertTrue(output.contains("Here are the ARCHIVED students:"));
        assertTrue(output.contains("Old Student | Graduated | History"));
    }
}
