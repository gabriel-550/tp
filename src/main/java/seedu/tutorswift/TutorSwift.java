package seedu.duke;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();

        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();

            if (input.trim().equalsIgnoreCase("bye")) {
                ui.showExit();
                isExit = true;
            }
        }

        ui.close();
    }
}
