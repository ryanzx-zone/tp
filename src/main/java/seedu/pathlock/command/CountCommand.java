package seedu.pathlock.command;

import seedu.pathlock.appstate.AppState;
import seedu.pathlock.module.ModuleList;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Displays the user's MC progress towards graduation.
 */
public class CountCommand extends Command {

    private static final Logger logger = Logger.getLogger(CountCommand.class.getName());

    @Override
    public String execute(AppState appState) {
        ModuleList modules = appState.getModule();
        assert modules != null : "ModuleList should not be null";
        logger.log(Level.INFO, "Executing count command");
        String result = modules.countMcs();
        assert result != null : "countMcs result should not be null";
        return result;
    }

}
