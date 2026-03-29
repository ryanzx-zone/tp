package seedu.duke.command;

import seedu.duke.appstate.AppState;
import seedu.duke.module.ModuleList;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Displays the prerequisites needed before taking a specified module.
 */
public class PrereqCommand extends Command {

    private static final Logger logger = Logger.getLogger(PrereqCommand.class.getName());
    private final String moduleCode;

    public PrereqCommand(String moduleCode) {
        assert moduleCode != null : "Module code should not be null";
        assert !moduleCode.trim().isEmpty() : "Module code should not be empty";
        this.moduleCode = moduleCode.toUpperCase();
    }

    @Override
    public String execute(AppState appState) {
        assert appState != null : "AppState should not be null";
        ModuleList modules = appState.getModule();
        assert modules != null : "ModuleList should not be null";

        logger.log(Level.FINE, "Executing prereq command for module: {0}", moduleCode);

        String result = modules.getPrerequisites(moduleCode);

        assert result != null : "getPrerequisites result should not be null";
        logger.log(Level.FINE, "Prereq result for {0}: {1}", new Object[]{moduleCode, result});

        return result;
    }
}
