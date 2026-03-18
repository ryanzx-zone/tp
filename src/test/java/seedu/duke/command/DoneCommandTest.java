package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.module.ModuleList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class DoneCommandTest {
    @Test
    public void execute_validModule_success() {
        ModuleList modules = new ModuleList(new ArrayList<>());
        DoneCommand command = new DoneCommand("CS1231");

        String result = command.execute(modules);

        assertEquals("CS1231 has been added", result);
        assertEquals(1, modules.completedModules.size());
    }

    @Test
    public void execute_lowercaseInput_convertedToUppercase() {
        ModuleList modules = new ModuleList(new ArrayList<>());

        DoneCommand command = new DoneCommand("cs1231");
        String result = command.execute(modules);

        assertEquals("CS1231 has been added", result);
    }
}
