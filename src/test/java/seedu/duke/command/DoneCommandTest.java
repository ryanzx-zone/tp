package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.module.ModuleList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoneCommandTest {
    @Test
    public void execute_validModule_success() {
        ModuleList modules = new ModuleList();
        DoneCommand command = new DoneCommand("CS1231", 4);

        String result = command.execute(modules);

        assertEquals("CS1231 has been added", result);
        assertEquals(1, modules.getCompletedModules().size());
    }

    @Test
    public void execute_lowercaseInput_convertedToUppercase() {
        ModuleList modules = new ModuleList();

        DoneCommand command = new DoneCommand("cs1231", 4);
        String result = command.execute(modules);

        assertEquals("CS1231 has been added", result);
    }
}
