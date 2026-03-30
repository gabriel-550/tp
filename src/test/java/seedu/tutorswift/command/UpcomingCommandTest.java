package seedu.tutorswift.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tutorswift.Lesson;
import seedu.tutorswift.RelativeLesson;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpcomingCommandTest {

    private StudentList students;
    private UiTest uiTest;

    static class UiTest extends Ui {
        boolean isEmptyCalled = false;
        List<RelativeLesson> capturedLessons = null;

        @Override
        public void showEmptyUpcomingLessons() {
            isEmptyCalled = true;
        }

        @Override
        public void showUpcomingLessons(List<RelativeLesson> lessons) {
            capturedLessons = lessons;
        }
    }

    @BeforeEach
    public void setUp() {
        students = new StudentList();
        uiTest = new UiTest();
    }

    @Test
    public void execute_noLessons_callsShowEmptyUpcomingLessons() throws TutorSwiftException {
        UpcomingCommand command = new UpcomingCommand();

        command.execute(students, uiTest);

        assertTrue(uiTest.isEmptyCalled);
    }

    @Test
    public void execute_withLessons_sortsAndCallsShowUpcomingLessons() throws TutorSwiftException {
        Student testStudent1 = new Student("Alice", "Primary 1", "Math");
        Student testStudent2 = new Student("Bob", "Secondary 3", "Physics");
        students.addStudent(testStudent1);
        students.addStudent(testStudent2);

        DayOfWeek today = LocalDate.now().getDayOfWeek();
        DayOfWeek tomorrow = today.plus(1);

        Lesson earlierLesson = new Lesson(today, LocalTime.of(10, 0), LocalTime.of(12, 0));
        Lesson laterLesson = new Lesson(tomorrow, LocalTime.of(14, 0), LocalTime.of(16, 0));

        // Add them out of order
        testStudent1.addLesson(laterLesson);
        testStudent2.addLesson(earlierLesson);

        UpcomingCommand command = new UpcomingCommand();

        command.execute(students, uiTest);

        assertEquals(2, uiTest.capturedLessons.size());
        // Verify the command correctly sorted them so the earlier lesson is first
        assertEquals(earlierLesson, uiTest.capturedLessons.get(0).getLesson());
        assertEquals(laterLesson, uiTest.capturedLessons.get(1).getLesson());
    }
}