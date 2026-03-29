package seedu.tutorswift.command;

import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;


/**
 * Command to move a student from active list to the archive.
 */
public class ArchiveCommand extends Command {
    private final int index;

    public ArchiveCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        int zeroBasedIndex = index - 1;
        if (zeroBasedIndex < 0 || zeroBasedIndex >= students.getActiveSize()) {
            throw new TutorSwiftException("Invalid index! Use 'list' to view active students.");
        }

        Student archivedStudent = students.archiveStudent(zeroBasedIndex);
        ui.showArchiveSuccess(archivedStudent);
    }
}
