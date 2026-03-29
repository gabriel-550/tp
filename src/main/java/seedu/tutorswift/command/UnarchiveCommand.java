package seedu.tutorswift.command;

import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

// @@author Alex-Chen-666
/**
 * Command to move a student from the archive back to the active list.
 */
public class UnarchiveCommand extends Command {
    private final int index;

    public UnarchiveCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        int zeroBasedIndex = index - 1;
        if (zeroBasedIndex < 0 || zeroBasedIndex >= students.getArchivedSize()) {
            throw new TutorSwiftException("Invalid index! Use 'list-archive' to check.");
        }

        Student unarchivedStudent = students.unarchiveStudent(zeroBasedIndex);
        ui.showUnarchiveSuccess(unarchivedStudent);
    }
}
// @@author
