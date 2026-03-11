package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

import seedu.duke.command.Command;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.parser.Parser;
import seedu.duke.ui.UI;

public class PathLock {
    /**
     * Main entry-point for the PathLock application.
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ModuleList modules = new ModuleList(new ArrayList<Module>());

        UI.opening();
        while (true) {
            UI.userPrompt();

            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                UI.closing();
                break;
            }

            else if (input.equalsIgnoreCase("help")) {
                UI.help();
            }

            else {
                Command command = Parser.parseCommand(input);

                if (command == null) {
                    UI.unknownCommand();
                    continue;
                }

                try {
                    String result = command.execute(modules);
                    System.out.println(result);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        scanner.close();
    }
}
