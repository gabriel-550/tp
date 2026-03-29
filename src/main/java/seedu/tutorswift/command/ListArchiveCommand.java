package seedu.tutorswift.command;

import seedu.tutorswift.StudentList;
import seedu.tutorswift.Ui;

/**
 * Command to display all students in the archive.
 */
public class ListArchiveCommand extends Command {
    @Override
    public void execute(StudentList students, Ui ui) {
        ui.showArchivedList(students);
    }
}
