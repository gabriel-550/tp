package seedu.tutorswift;

/**
 * Represents a student with a name, subject, and academic level.
 * Includes status tracking for active or archived states.
 */
public class Student {

    private String name;
    private String subject;
    private String academicLevel;
    private boolean isArchived;

    /**
     * Creates a student with the given name, subject, and academic level.
     * Default status is active (not archived).
     */
    public Student(String name, String academicLevel, String subject) {
        this.name = name;
        this.academicLevel = academicLevel;
        this.subject = subject;
        this.isArchived = false;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }

    public void editStudent(String name, String academicLevel, String subject) {
        if (name != null) {
            this.name = name;
        }

        if (academicLevel != null) {
            this.academicLevel = academicLevel;
        }

        if (subject != null) {
            this.subject = subject;
        }
    }
// @@author Alex-Chen-666
    /**
     * Checks if the student is currently archived.
     *
     * @return true if archived, false otherwise.
     */
    public boolean isArchived() {
        return isArchived;
    }
    /**
     * Sets the archived status of the student.
     *
     * @param archived The new status to set.
     */
    public void setArchived(boolean archived) {
        isArchived = archived;
    }
// @@author
    /**
     * Returns a string representation of the student in the format:
     * "name | academic level | subject".
     *
     * This is used for displaying the student in the UI.
     *
     * @return a formatted string representing the student
     */
    @Override
    public String toString() {
        String status = isArchived ? " [ARCHIVED]" : "";
        return name + " | " + academicLevel + " | " + subject + status;
    }

    // @@author Alex-Chen-666
    /**
     * Converts the student data into a save-friendly string format.
     *
     * @return A formatted string for file storage.
     */
    public String toSaveFormat() {
        return name + " | " + academicLevel + " | " + subject + " | " + isArchived;
    }
    // @@author

}
