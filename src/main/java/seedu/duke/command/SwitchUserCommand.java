package seedu.duke.command;

import seedu.duke.appstate.AppState;
import seedu.duke.module.ModuleList;
import seedu.duke.module.Module;
import seedu.duke.profile.UserProfile;
import seedu.duke.storage.ProfileStorage;
import seedu.duke.storage.Storage;
import seedu.duke.storage.PlannerStorage;
import seedu.duke.planner.PlannerList;
import java.util.logging.Logger;

import java.io.IOException;
import java.util.List;

public class SwitchUserCommand extends Command {

    private static final Logger logger = Logger.getLogger(SwitchUserCommand.class.getName());

    private final String username;

    public SwitchUserCommand(String username) {
        this.username = username;
    }

    @Override
    public String execute(AppState appState) {

        try {
            ProfileStorage profileStorage = new ProfileStorage();
            UserProfile profile = profileStorage.loadProfile(username);

            if (profile == null) {
                return "User \"" + username + "\" does not exist.";
            }

            Storage storage = new Storage(username);
            ModuleList moduleList = new ModuleList();

            List<Module> savedModules = storage.load();
            for (Module saved : savedModules) {
                try {
                    String code = saved.getModuleCode();
                    int mc = saved.getModularCredits();

                    if (moduleList.isRecognisedModule(code)) {
                        moduleList.addModule(code);
                    } else {
                        moduleList.addExternalModule(code, mc);
                    }
                } catch (Exception e) {
                    logger.warning("Failed to switch user: " + e.getMessage());
                }
            }

            PlannerStorage plannerStorage = new PlannerStorage(username);
            PlannerList planner = plannerStorage.load();

            appState.update(moduleList, planner, profile, plannerStorage);

            return "Switched to user: " + username;

        } catch (IOException e) {
            return "Error switching user: " + e.getMessage();
        }
    }
}
