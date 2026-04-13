package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Lesson;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScheduleCommandTest {
    private StudentList students;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        students = new StudentList();
        ui = new Ui();
    }

    @Test
    public void execute_validStudent_schedulesLessonSuccessfully() throws TutorSwiftException {
        Student testStudent = new Student("John Doe", "Primary 1", "Math");
        students.addStudent(testStudent);


        DayOfWeek testDay = DayOfWeek.MONDAY;
        LocalTime startTime = LocalTime.of(14, 0);
        LocalTime endTime = LocalTime.of(16, 0);
        ScheduleCommand command = new ScheduleCommand(1, testDay, startTime, endTime);

        command.execute(students, ui);
        Lesson scheduledLesson = testStudent.getLessons().get(0);

        assertEquals(1, testStudent.getLessons().size());
        assertEquals(testDay, scheduledLesson.getDay());
        assertEquals(startTime, scheduledLesson.getStartTime());
        assertEquals(endTime, scheduledLesson.getEndTime());
    }

    @Test
    public void execute_multipleStudents_targetsCorrectStudent() throws TutorSwiftException {
        Student alice = new Student("Alice", "Primary 3", "Math");
        Student bob = new Student("Bob", "Secondary 3", "Physics");
        students.addStudent(alice); // Index 1
        students.addStudent(bob);   // Index 2

        DayOfWeek testDay = DayOfWeek.TUESDAY;
        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(12, 0);

        ScheduleCommand command = new ScheduleCommand(2, testDay, startTime, endTime);
        command.execute(students, ui);

        assertEquals(1, bob.getLessons().size());
        assertEquals(0, alice.getLessons().size());
    }

    @Test
    public void constructor_invalidIndexZero_throwsTutorSwiftException() {
        DayOfWeek testDay = DayOfWeek.WEDNESDAY;
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(11, 0);

        // Test that an index of 0 fails immediately in the constructor
        TutorSwiftException exception = assertThrows(TutorSwiftException.class, () -> {
            new ScheduleCommand(0, testDay, startTime, endTime);
        });

        assertEquals("Student index must be a positive non-zero number.", exception.getMessage());
    }

    @Test
    public void execute_indexOutOfBounds_throwsTutorSwiftException() throws TutorSwiftException {
        Student testStudent = new Student("Charlie", "JC 1", "Chemistry");
        students.addStudent(testStudent); // List size is exactly 1

        DayOfWeek testDay = DayOfWeek.WEDNESDAY;
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(11, 0);

        // Target index 2, which does not exist
        ScheduleCommand command = new ScheduleCommand(2, testDay, startTime, endTime);

        TutorSwiftException exception = assertThrows(TutorSwiftException.class, () -> {
            command.execute(students, ui);
        });

        assertEquals("Invalid student index. Use list to view valid student indices.", exception.getMessage());
    }

    @Test
    public void execute_overlappingLesson_throwsTutorSwiftException() throws TutorSwiftException {
        Student testStudent = new Student("Eve", "Primary 5", "English");
        students.addStudent(testStudent);

        Lesson existingLesson = new Lesson(DayOfWeek.THURSDAY, LocalTime.of(15, 0), LocalTime.of(17, 0));
        testStudent.addLesson(existingLesson);

        DayOfWeek testDay = DayOfWeek.THURSDAY;
        LocalTime startTime = LocalTime.of(16, 0);
        LocalTime endTime = LocalTime.of(18, 0);
        ScheduleCommand command = new ScheduleCommand(1, testDay, startTime, endTime);

        assertThrows(TutorSwiftException.class, () -> {
            command.execute(students, ui);
        });
    }
}
