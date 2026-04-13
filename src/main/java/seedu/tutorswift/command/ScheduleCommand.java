package seedu.tutorswift.command;

import java.time.DayOfWeek;
import java.time.LocalTime;
import seedu.tutorswift.Lesson;
import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

public class ScheduleCommand extends Command {
    private final int studentIndex;
    private final DayOfWeek day;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public ScheduleCommand(
        int studentIndex,
        DayOfWeek day,
        LocalTime startTime,
        LocalTime endTime
    ) throws TutorSwiftException {
        assert day != null : "Day parameter should not be null";
        assert startTime != null : "Start time should not be null";
        assert endTime != null : "End time should not be null";

        if (studentIndex <= 0) {
            throw new TutorSwiftException("Student index must be a positive non-zero number.");
        }

        if (!startTime.isBefore(endTime)) {
            throw new TutorSwiftException("Start time must be before end time.");
        }

        this.studentIndex = studentIndex;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        assert students != null : "StudentList should not be null";
        assert ui != null : "Ui should not be null";

        if (studentIndex > students.getActiveSize()) {
            throw new TutorSwiftException("Invalid student index. Use list to view valid student indices.");
        }

        Student targetStudent = students.getActiveStudent(studentIndex - 1);

        Lesson newLesson = new Lesson(day, startTime, endTime);
        targetStudent.addLesson(newLesson);

        ui.showLessonScheduled(targetStudent.getName(), newLesson);
    }
}
