package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DuplicateException;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CountCommandTest {

    @Test
    public void execute_emptyModuleList_showsZeroMcs() {
        ModuleList ml = new ModuleList(new ArrayList<>());
        CountCommand cmd = new CountCommand();
        String result = cmd.execute(ml);
        assertTrue(result.contains("Completed: 0 / 80 MCs"));
        assertTrue(result.contains("0.0%"));
    }

    @Test
    public void execute_oneModule_showsCorrectMcs() throws DuplicateException {
        ModuleList ml = new ModuleList(new ArrayList<>());
        ml.addModule(new Module("CS2113", 4));
        CountCommand cmd = new CountCommand();
        String result = cmd.execute(ml);
        assertTrue(result.contains("Completed: 4 / 80 MCs"));
    }

    @Test
    public void execute_multipleModules_showsCumulativeMcs() throws DuplicateException {
        ModuleList ml = new ModuleList(new ArrayList<>());
        ml.addModule(new Module("MA1511", 2));
        ml.addModule(new Module("MA1512", 2));
        ml.addModule(new Module("CS2113", 4));
        CountCommand cmd = new CountCommand();
        String result = cmd.execute(ml);
        assertTrue(result.contains("Completed: 8 / 80 MCs"));
        assertTrue(result.contains("10.0%"));
    }
}
