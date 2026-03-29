package seedu.duke.command;

import seedu.duke.appstate.AppState;
import seedu.duke.module.ModuleList;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Displays the modules unlocked by completing a specified module.
 */
public class PostreqCommand extends Command {

    private static final Logger logger = Logger.getLogger(PostreqCommand.class.getName());
    private final String moduleCode;

    public PostreqCommand(String moduleCode) {
        assert moduleCode != null : "Module code should not be null";
        assert !moduleCode.trim().isEmpty() : "Module code should not be empty";
        this.moduleCode = moduleCode.toUpperCase();
    }

    @Override
    public String execute(AppState appState) {
        assert appState != null : "AppState should not be null";
        ModuleList modules = appState.getModule();
        assert modules != null : "ModuleList should not be null";

        logger.log(Level.FINE, "Executing postreq command for module: {0}", moduleCode);

        String result = modules.getModulesUnlockedBy(moduleCode);

        assert result != null : "getModulesUnlockedBy result should not be null";
        logger.log(Level.FINE, "Postreq result for {0}: {1}", new Object[]{moduleCode, result});

        return result;
    }
}
