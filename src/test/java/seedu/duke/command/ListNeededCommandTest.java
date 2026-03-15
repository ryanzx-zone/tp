package seedu.duke.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import seedu.duke.module.ModuleList;

public class ListNeededCommandTest {
    @Test
    public void execute_returnsAllRequiredModules() {
        ModuleList modules = new ModuleList(new ArrayList<>());
        ListNeededCommand command = new ListNeededCommand();
        String result = command.execute(modules);
        String expected = "Modules required for graduation:\n"
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
}
