package seedu.tutorswift.command;

import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;
import seedu.tutorswift.Lesson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.YearMonth;
import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FeeSummaryCommandTest {

    private StudentList students;
    private Ui ui;

    @BeforeEach
    public void setUp() throws TutorSwiftException {
        students = new StudentList();
        ui = new Ui();
        Student s = new Student("Alice", "S2", "Math");
        s.getFeeRecord().setFeePerLesson(50);
        s.addLesson(new Lesson(DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(16, 0)));
        students.addStudent(s);
    }

    //Positive test cases (Each valid input at least once)
    @Test
    public void execute_validIndexAndDate_success() {
        // EP: Valid index, valid month
        FeeSummaryCommand command = new FeeSummaryCommand(1, YearMonth.of(2026, 4));
        assertDoesNotThrow(() -> command.execute(students, ui));
    }

    //BVA
    @Test
    public void execute_boundaryMonths_success() {
        // BVA: Test January and December (earliest/latest months)
        FeeSummaryCommand jan = new FeeSummaryCommand(1, YearMonth.of(2026, 1));
        FeeSummaryCommand dec = new FeeSummaryCommand(1, YearMonth.of(2026, 12));
        assertDoesNotThrow(() -> jan.execute(students, ui));
        assertDoesNotThrow(() -> dec.execute(students, ui));
    }

    //NEGATIVE CASES (Equivalence Partitioning)
    @Test
    public void execute_indexOutOfBounds_throwsException() {
        // BVA: Test index just above valid range (size + 1)
        FeeSummaryCommand command = new FeeSummaryCommand(2, YearMonth.of(2026, 4));
        assertThrows(TutorSwiftException.class, () -> command.execute(students, ui));
    }

    //Heuristic: Test Invalid Inputs Individually
    @Test
    public void execute_zeroIndex_throwsAssertionError() {
        // Invalid Index tested individually
        assertThrows(AssertionError.class, () -> new FeeSummaryCommand(0, YearMonth.of(2026, 4)));
    }
}
