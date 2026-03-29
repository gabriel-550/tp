package seedu.tutorswift;

import seedu.tutorswift.command.Command;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main entry-point for the TutorSwift application.
 * Manages the lifecycle of the application, including data persistence.
 */
public class TutorSwift {
    private static final Logger logger = Logger.getLogger(TutorSwift.class.getName());
    private Ui ui;
    private StudentList students;
    private Storage storage;

    /**
     * Initializes the UI, Storage, and loads student data from the disk.
     */
    public TutorSwift() {
        if (java.util.logging.Logger.getLogger("").getHandlers().length > 0) {
            java.util.logging.Logger.getLogger("").getHandlers()[0]
                    .setLevel(java.util.logging.Level.WARNING);
        }
        ui = new Ui();
        storage = new Storage();
        students = storage.load();
        logger.log(Level.INFO, "TutorSwift initialized and data loaded.");
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readUserInput();

                // Parse user input into command object to determine command name
                Command c = Parser.parseUserInput(userInput);
                c.execute(students, ui);
                // v2.0 New: Automatic persistence
                if (!c.isExit()) {
                    storage.save(students);
                }
                isExit = c.isExit();
            } catch (TutorSwiftException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
        logger.log(Level.INFO, "Application exited normally.");

    }

    public static void main(String[] args) {
        new TutorSwift().run();
    }
}
