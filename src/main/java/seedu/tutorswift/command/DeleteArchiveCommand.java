package seedu.tutorswift.command;

import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Represents a command to permanently delete a student from the archive list.
 */
public class DeleteArchiveCommand extends Command {
    private static final Logger logger = Logger.getLogger(DeleteArchiveCommand.class.getName());
    private final int index;

    /**
     * Constructs a DeleteArchiveCommand with the target index in the archive list.
     *
     * @param index One-based index of the archived student.
     */
    public DeleteArchiveCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        assert students != null : "StudentList should not be null";

        int zeroBasedIndex = index - 1;

        if (zeroBasedIndex < 0 || zeroBasedIndex >= students.getArchivedSize()) {
            logger.log(Level.WARNING, "Invalid archive delete attempt at index: " + index);
            throw new TutorSwiftException("Invalid index! Please use 'list-archive' to check archived students.");
        }

        Student deletedStudent = students.getArchivedStudent(zeroBasedIndex);
        assert deletedStudent != null : "Student to delete should exist";

        students.deleteArchivedStudent(zeroBasedIndex);

        ui.showDeleteArchiveSuccess(deletedStudent, students.getArchivedSize());
        logger.log(Level.INFO, "Permanently deleted archived student: " + deletedStudent.getName());
    }
}
