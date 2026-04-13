package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.Ui;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Ui ui;
    private StudentList studentList;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        ui = new Ui();
        studentList = new StudentList();
    }

    //Positive test cases

    @Test
    void execute_listOneStudent_displaysCorrectly() throws Exception {
        // EP: Testing the smallest valid list size (Boundary: 1 student)
        Student s1 = new Student("John Tan", "Math", "Secondary 3");
        studentList.addStudent(s1);

        ListCommand listCommand = new ListCommand();
        listCommand.execute(studentList, ui);

        String output = outContent.toString();
        assertTrue(output.contains("1. John Tan | Math | Secondary 3"));
    }

    @Test
    void execute_listMultipleStudents_displaysAllStudents() throws Exception {
        // EP: Testing a typical populated list
        Student s1 = new Student("John Tan", "Math", "Secondary 3");
        Student s2 = new Student("Sarah Lim", "English", "Primary 6");
        studentList.addStudent(s1);
        studentList.addStudent(s2);

        ListCommand listCommand = new ListCommand();
        listCommand.execute(studentList, ui);

        String output = outContent.toString();
        assertTrue(output.contains("1. John Tan | Math | Secondary 3"));
        assertTrue(output.contains("2. Sarah Lim | English | Primary 6"));
    }

    //Negative/boundary cases

    @Test
    void execute_listEmpty_displaysNoStudentsMessage() throws Exception {
        // EP: Testing the empty boundary condition individually
        ListCommand listCommand = new ListCommand();
        listCommand.execute(studentList, ui);

        String output = outContent.toString();
        assertTrue(output.contains("Your active student list is currently empty."));
    }
}
