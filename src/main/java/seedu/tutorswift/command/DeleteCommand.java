package seedu.tutorswift.command;

import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

/**
 * Represents a command to delete an existing student by index.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Creates a DeleteCommand with the given one-based student index.
     *
     * @param index The one-based index of the student to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the student at the specified index.
     *
     * @param students The current list of students.
     * @param ui       The UI handler for displaying messages.
     * @throws TutorSwiftException If the index is out of range.
     */
    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        assert students != null : "StudentList should not be null";
        int zeroBasedIndex = index - 1;
        if (zeroBasedIndex < 0 || zeroBasedIndex >= students.getActiveSize()) {
            throw new TutorSwiftException("Invalid index! Please use 'list' to check valid active student indices.");
        }
        Student deletedStudent = students.getActiveStudent(zeroBasedIndex);
        assert deletedStudent != null : "Retrieved student should not be null";
        students.deleteActiveStudent(zeroBasedIndex);
        ui.showDeleteSuccess(deletedStudent, students.getActiveSize());
    }
}
