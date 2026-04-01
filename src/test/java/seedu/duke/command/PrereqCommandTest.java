package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.appstate.AppState;
import seedu.duke.module.ModuleList;
import seedu.duke.planner.PlannerList;
import seedu.duke.profile.UserProfile;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrereqCommandTest {

    private AppState createTestState() {
        return new AppState(new ModuleList(),
                new PlannerList(),
                new UserProfile("Test User", 3.50),
                "Test User"
        );
    }

    @Test
    public void execute_moduleWithPrereqs_showsPrerequisites() {
        AppState state = createTestState();
        PrereqCommand cmd = new PrereqCommand("EE2211");
        String result = cmd.execute(state);
        assertTrue(result.contains("Prerequisites for EE2211"));
        assertTrue(result.contains("CS1010"));
        assertTrue(result.contains("MA1511"));
        assertTrue(result.contains("MA1508E"));
    }

    @Test
    public void execute_moduleWithNoPrereqs_showsNoPrerequisites() {
        AppState state = createTestState();
        PrereqCommand cmd = new PrereqCommand("CS1010");
        String result = cmd.execute(state);
        assertTrue(result.contains("CS1010 has no prerequisites"));
    }

    @Test
    public void execute_unrecognisedModule_showsNotRecognised() {
        AppState state = createTestState();
        PrereqCommand cmd = new PrereqCommand("FAKE1234");
        String result = cmd.execute(state);
        assertTrue(result.contains("FAKE1234 is not a recognised module"));
    }

    @Test
    public void execute_lowercaseInput_normalisesToUppercase() {
        AppState state = createTestState();
        PrereqCommand cmd = new PrereqCommand("ee2211");
        String result = cmd.execute(state);
        assertTrue(result.contains("Prerequisites for EE2211"));
    }

    @Test
    public void execute_singlePrereq_showsSingleModule() {
        AppState state = createTestState();
        PrereqCommand cmd = new PrereqCommand("CS2113");
        String result = cmd.execute(state);
        assertTrue(result.contains("Prerequisites for CS2113"));
        assertTrue(result.contains("CS2040C"));
    }
}
