package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test class for AddCommand.
 * Verifies that the command correctly interacts with the StudentList and Ui.
 */
class AddCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private StudentList studentList;
    private Ui ui;

    /**
     * Sets up the test environment before each test method is executed.
     * Redirects System.out to a stream and initializes the student list and UI.
     */
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        studentList = new StudentList();
        ui = new Ui();
    }

    /**
     * Tests adding a single student and verifies all attributes are stored correctly.
     */
    @Test
    void execute_validStudent_success() throws TutorSwiftException {
        Student student = new Student("John Doe", "Secondary 4", "Mathematics");
        AddCommand command = new AddCommand(student);
        command.execute(studentList, ui);

        assertEquals(1, studentList.getActiveSize(), "Size should be 1");
        Student added = studentList.getActiveStudent(0);

        assertEquals("John Doe", added.getName());
        assertEquals("Secondary 4", added.getAcademicLevel());
        assertEquals("Mathematics", added.getSubject());

        assertTrue(outContent.toString().contains("Got it. I've added this student:"));
    }

    /**
     * Tests adding multiple students to ensure the list tracks them correctly.
     */
    @Test
    void execute_multipleStudents_listSizeIncrements() throws TutorSwiftException{
        AddCommand cmd1 = new AddCommand(new Student("Alice", "P6", "English"));
        AddCommand cmd2 = new AddCommand(new Student("Bob", "S1", "Science"));

        cmd1.execute(studentList, ui);
        cmd2.execute(studentList, ui);

        assertEquals(2, studentList.getActiveSize(), "Size should be 2 after two additions");
        assertEquals("Alice", studentList.getActiveStudent(0).getName());
        assertEquals("Bob", studentList.getActiveStudent(1).getName());
    }

    /**
     * Tests adding a student with long strings or extra spaces.
     * This verifies that the command handles the data object as provided.
     */
    @Test
    void execute_studentWithSpacesInName_storedCorrectly() throws TutorSwiftException{
        String nameWithSpaces = "Mary Ann Tan";
        Student student = new Student(nameWithSpaces, "JC 1", "H2 Physics");
        AddCommand command = new AddCommand(student);
        command.execute(studentList, ui);

        assertEquals(nameWithSpaces, studentList.getActiveStudent(0).getName());
    }

    /**
     * Tests that adding a student with a duplicate name throws a TutorSwiftException.
     * Uses assertEquals to verify the exception message.
     */
    @Test
    void execute_duplicateName_throwsException() throws TutorSwiftException {
        Student original = new Student("Alice", "P6", "English");
        Student duplicate = new Student("Alice", "S1", "Science");

        AddCommand cmd1 = new AddCommand(original);
        AddCommand cmd2 = new AddCommand(duplicate);

        cmd1.execute(studentList, ui);

        try {
            cmd2.execute(studentList, ui);
            assertTrue(false, "Exception should have been thrown for duplicate name");
        } catch (TutorSwiftException e) {
            assertEquals("A student with the name 'Alice' already exists in your records.",
                    e.getMessage(),
                    "Exception message should match the expected duplicate error");
        }

        assertEquals(1, studentList.getActiveSize(), "List size should remain 1 after failed duplicate addition");
    }
}
