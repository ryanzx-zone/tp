package seedu.duke.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import seedu.duke.exception.DuplicateException;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;

public class ListCompletedCommandTest {
    @Test
    public void execute_noCompletedModules_returnsEmptyMessage() {
        ModuleList modules = new ModuleList(new ArrayList<>());
        ListCompletedCommand command = new ListCompletedCommand();
        String result = command.execute(modules);
        assertEquals("No modules completed yet.", result);
    }

    @Test
    public void execute_withCompletedModules_returnsCompletedList() throws DuplicateException {
        ModuleList modules = new ModuleList(new ArrayList<>());
        modules.addModule(new Module("CS2113",4));
        modules.addModule(new Module("CS1231",4));
        ListCompletedCommand command = new ListCompletedCommand();
        String result = command.execute(modules);
        assertEquals("Completed modules:\n1. CS2113\n2. CS1231", result);
    }
}
