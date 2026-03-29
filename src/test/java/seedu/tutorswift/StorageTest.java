package seedu.tutorswift;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertEquals;

// @@author Alex-Chen-666
/**
 * JUnit test class for Storage.
 * Uses a temporary directory to avoid creating actual files during testing.
 */
class StorageTest {

    @TempDir
    Path tempDir;

    @Test
    void saveAndLoad_validStudentList_correctlyRestored() throws Exception {
        // Use a test-specific file in the temp directory
        String testFilePath = tempDir.resolve("tutorswift_test.txt").toString();

        // Setup students
        Student s1 = new Student("Active Student", "Sec 1", "Math");
        Student s2 = new Student("Archived Student", "Sec 4", "English");
        s2.setArchived(true);

        StudentList saveList = new StudentList();
        saveList.addStudent(s1);
        saveList.addStudent(s2);

        // Save

        Storage storage = new Storage(testFilePath);
        storage.save(saveList);

        // Load
        StudentList loadList = storage.load();

        assertEquals(1, loadList.getActiveSize());
        assertEquals(1, loadList.getArchivedSize());
        assertEquals("Active Student", loadList.getActiveStudent(0).getName());
        assertEquals("Archived Student", loadList.getArchivedStudent(0).getName());
    }
}
// @@author
