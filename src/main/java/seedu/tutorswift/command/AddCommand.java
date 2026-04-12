package seedu.tutorswift.command;

import seedu.tutorswift.Student;
import seedu.tutorswift.StudentList;
import seedu.tutorswift.TutorSwiftException;
import seedu.tutorswift.Ui;

/**
 * Represents a command to add a new student to the student list.
 */
public class AddCommand extends Command {
    private final Student studentToAdd;


    /**
     * Constructs an {@code AddCommand} with the specified student.
     *
     * @param student The student to be added to the list.
     */
    public AddCommand(Student student) {
        assert student != null : "AddCommand initialized with null student";
        this.studentToAdd = student;
    }

    /**
     * Executes the add command.
     * Checks for duplicate student names across active and archived lists before
     * adding the new student to the student list and displaying a success message.
     *
     * @param students The list of students where the new student will be added.
     * @param ui The user interface used to show the confirmation message.
     * @throws TutorSwiftException If a student with the same name already exists.
     */

    @Override
    public void execute(StudentList students, Ui ui) throws TutorSwiftException {
        assert studentToAdd != null : "Student to add should not be null";
        assert students != null : "StudentList should not be null";
        assert ui != null : "Ui should not be null";

        if (students.hasStudentWithName(studentToAdd.getName())) {
            throw new TutorSwiftException("A student with the name '" +
                    studentToAdd.getName() + "' already exists in your records.");
        }

        int initialSize = students.getActiveSize();
        students.addStudent(studentToAdd);

        assert students.getActiveSize() == initialSize + 1 : "Active list size should increase by 1";
        ui.showAddSuccess(studentToAdd, students.getActiveSize());
    }

}
