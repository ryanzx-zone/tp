package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.module.ModuleList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveCommandTest {
    @Test
    public void execute_existingModule_removedSuccessfully() {
        ModuleList modules = new ModuleList();

        DoneCommand doneCommand = new DoneCommand("CS1231",4);
        doneCommand.execute(modules);

        RemoveCommand removeCommand = new RemoveCommand("CS1231");
        String result = removeCommand.execute(modules);

        assertEquals("CS1231 has been removed", result);
        assertEquals(0, modules.getCompletedModules().size());
    }

    @Test
    public void execute_moduleNotInList_returnsErrorMessage() {
        ModuleList modules = new ModuleList();

        RemoveCommand command = new RemoveCommand("CS1231");
        String result = command.execute(modules);

        assertEquals("CS1231 is not in your module list", result);
    }

    @Test
    public void execute_lowercaseInput_convertedToUppercase() {
        ModuleList modules = new ModuleList();
        DoneCommand doneCommand = new DoneCommand("CS1231", 4);
        doneCommand.execute(modules);

        RemoveCommand removeCommand = new RemoveCommand("cs1231");
        String result = removeCommand.execute(modules);

        assertEquals("CS1231 has been removed", result);
    }

}
