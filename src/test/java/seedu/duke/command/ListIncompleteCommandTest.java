package seedu.duke.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import seedu.duke.exception.DuplicateException;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;

public class ListIncompleteCommandTest {
    @Test
    public void execute_noCompletedModules_returnsAllIncompleteModules() {
        ModuleList modules = new ModuleList(new ArrayList<>());
        ListIncompleteCommand command = new ListIncompleteCommand();
        String result = command.execute(modules);
        String expected = "Incomplete modules:\n"
                + "1. MA1511\n"
                + "2. MA1512\n"
                + "3. MA1508E\n"
                + "4. EG2401A\n"
                + "5. CG1111A\n"
                + "6. CG2111A\n"
                + "7. CS1231\n"
                + "8. CG2023\n"
                + "9. CG2027\n"
                + "10. CG2028\n"
                + "11. CG2271\n"
                + "12. CG3201\n"
                + "13. CG3207\n"
                + "14. CS2040C\n"
                + "15. CS2107\n"
                + "16. CS2113\n"
                + "17. EE2026\n"
                + "18. EE4204\n"
                + "19. CP3880 OR EG3611A\n"
                + "20. CG4002 OR CG4001 OR CP4106 OR EE4002D OR EE4002R OR CDE4301";
        assertEquals(expected, result);
    }

    @Test
    public void execute_someCompletedModules_returnsRemainingIncompleteModules() throws DuplicateException {
        ModuleList modules = new ModuleList(new ArrayList<>());
        modules.addModule(new Module("CS2113",4));
        modules.addModule(new Module("CS1231",4));
        modules.addModule(new Module("CP3880",10));
        ListIncompleteCommand command = new ListIncompleteCommand();
        String result = command.execute(modules);
        String expected = "Incomplete modules:\n"
                + "1. MA1511\n"
                + "2. MA1512\n"
                + "3. MA1508E\n"
                + "4. EG2401A\n"
                + "5. CG1111A\n"
                + "6. CG2111A\n"
                + "7. CG2023\n"
                + "8. CG2027\n"
                + "9. CG2028\n"
                + "10. CG2271\n"
                + "11. CG3201\n"
                + "12. CG3207\n"
                + "13. CS2040C\n"
                + "14. CS2107\n"
                + "15. EE2026\n"
                + "16. EE4204\n"
                + "17. CG4002 OR CG4001 OR CP4106 OR EE4002D OR EE4002R OR CDE4301";
        assertEquals(expected, result);
    }
}
