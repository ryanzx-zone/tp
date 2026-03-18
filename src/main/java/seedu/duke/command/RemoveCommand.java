package seedu.duke.command;

import seedu.duke.module.ModuleList;
import seedu.duke.storage.Storage;
import java.io.IOException;

public class RemoveCommand extends Command {
    private final String moduleCode;
    public RemoveCommand(String moduleCode) {
        this.moduleCode = moduleCode.toUpperCase();
    }

    @Override
    public String execute(ModuleList modules) {
        boolean removed = modules.removeModule(moduleCode);
        try {
            Storage.save(modules.completedModules);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (removed) {
            return moduleCode + " has been removed";
        } else {
            return moduleCode + " is not in your module list";
        }
    }
}
