package seedu.tutorswift;

import java.util.ArrayList;

/**
 * Represents a student with a name, subject, and academic level.
 */
public class Student {

    private String name;
    private String subject;
    private String academicLevel;
    private final ArrayList<Lesson> lessons;
    private final ArrayList<Grade> grades;

    /**
     * Creates a student with the given name, subject, and academic level.
     */
    public Student(String name, String academicLevel, String subject) {
        this.name = name;
        this.academicLevel = academicLevel;
        this.subject = subject;
        this.lessons = new ArrayList<>();
        this.grades = new ArrayList<>();
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
            if (!subject.equals(this.subject)) {
                this.subject = subject;
                this.grades.clear();
            }
        }
    }

    public void addLesson(Lesson newLesson) throws TutorSwiftException {
        for (Lesson existingLesson : lessons) {
            if (existingLesson.isOverlapping(newLesson)) {
                throw new TutorSwiftException("Schedule conflict! Overlaps with existing lesson: " + existingLesson);
            }
        }
        lessons.add(newLesson);
    }

    public void addGrade(String assessment, int score) {
        grades.add(new Grade(assessment, score));
    }

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
        StringBuilder gradeStr = new StringBuilder();

        if (!grades.isEmpty()) {
            gradeStr.append(" | Grades: ");
            for (int i = 0; i < grades.size(); i++) {
                gradeStr.append("[").append(grades.get(i).toString()).append("]");
                if (i < grades.size() - 1) {
                    gradeStr.append(" "); // space between grades only
                }
            }
        }

        return name + " | " + academicLevel + " | " + subject + gradeStr.toString();
    }

}
