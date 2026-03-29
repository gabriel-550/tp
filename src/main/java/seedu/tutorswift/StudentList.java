package seedu.tutorswift;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides methods for operations (e.g. add, delete, and get student) handling
 * an <code>ArrayList</code> of <code>Student</code> objects.
 * Manages two separate lists: active students and archived students.
 */
public class StudentList {
    // @@author Alex-Chen-666
    private static final Logger logger = Logger.getLogger(StudentList.class.getName());

    private final ArrayList<Student> activeStudents;
    private final ArrayList<Student> archivedStudents;

    public StudentList() {
        this.activeStudents = new ArrayList<>();
        this.archivedStudents = new ArrayList<>();
    }

    public void addStudent(Student student) {
        assert student != null : "Cannot add null student";
        if (student.isArchived()) {
            archivedStudents.add(student);
        } else {
            activeStudents.add(student);
        }
    }

    /**
     * Moves a student from active list to archived list.
     *
     * @param index Zero-based index in active list.
     * @return The archived student.
     */
    public Student archiveStudent(int index) {
        assert index >= 0 && index < activeStudents.size() : "Invalid active index";
        Student student = activeStudents.remove(index);
        student.setArchived(true);
        archivedStudents.add(student);
        logger.log(Level.INFO, "Archived student: " + student.getName());
        return student;
    }

    /**
     * Moves a student from archived list to active list.
     *
     * @param index Zero-based index in archived list.
     * @return The unarchived student.
     */
    public Student unarchiveStudent(int index) {
        assert index >= 0 && index < archivedStudents.size() : "Invalid archived index";
        Student student = archivedStudents.remove(index);
        student.setArchived(false);
        activeStudents.add(student);
        logger.log(Level.INFO, "Unarchived student: " + student.getName());
        return student;
    }

    /**
     * Permanently deletes a student from the active list.
     *
     * @param index Zero-based index in the active list.
     */
    public void deleteActiveStudent(int index) {
        assert index >= 0 && index < activeStudents.size() : "Invalid active index";
        Student removed = activeStudents.remove(index);
        logger.log(Level.INFO, "Deleted active student: " + removed.getName());
    }
    /**
     * Permanently deletes a student from the archived list.
     *
     * @param index Zero-based index in the archived list.
     */
    public void deleteArchivedStudent(int index) {
        assert index >= 0 && index < archivedStudents.size() : "Invalid archived index";
        Student removed = archivedStudents.remove(index);
        logger.log(Level.INFO, "Permanently deleted archived student: " + removed.getName());
    }

    public Student getActiveStudent(int index) {
        assert index >= 0 && index < activeStudents.size();
        return activeStudents.get(index);
    }

    public Student getArchivedStudent(int index) {
        assert index >= 0 && index < archivedStudents.size();
        return archivedStudents.get(index);
    }

    public int getActiveSize() {
        return activeStudents.size();
    }

    public int getArchivedSize() {
        return archivedStudents.size();
    }

    public ArrayList<Student> getAllActive() {
        return activeStudents;
    }

    public ArrayList<Student> getAllArchived() {
        return archivedStudents;
    }
    // @@author
}
