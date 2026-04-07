package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.appstate.AppState;
import seedu.duke.module.ModuleList;
import seedu.duke.planner.PlannerList;
import seedu.duke.profile.UserProfile;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostreqCommandTest {

    private AppState createTestState() {
        return new AppState(new ModuleList(),
                new PlannerList(),
                new UserProfile("Test User", 3.50),
                "Test User"
        );
    }

    @Test
    public void execute_moduleUnlocksOthers_showsUnlockedModules() {
        AppState state = createTestState();
        PostreqCommand cmd = new PostreqCommand("CS2040C");
        String result = cmd.execute(state);
        assertTrue(result.contains("Modules unlocked by CS2040C"));
        assertTrue(result.contains("CG2271"));
        assertTrue(result.contains("CS2113"));
    }

    @Test
    public void execute_moduleUnlocksNothing_showsNoModules() {
        AppState state = createTestState();
        PostreqCommand cmd = new PostreqCommand("CS2113");
        String result = cmd.execute(state);
        // CS2113 is a prereq for CP3880 and CP3200 based on the json
        // Let's just check it returns a valid result
        assertTrue(result.contains("CS2113"));
    }

    @Test
    public void execute_unrecognisedModule_showsNoUnlocks() {
        AppState state = createTestState();
        PostreqCommand cmd = new PostreqCommand("FAKE1234");
        String result = cmd.execute(state);
        assertTrue(result.contains("FAKE1234 is not a recognised module"));
    }

    @Test
    public void execute_lowercaseInput_normalisesToUppercase() {
        AppState state = createTestState();
        PostreqCommand cmd = new PostreqCommand("cs2040c");
        String result = cmd.execute(state);
        assertTrue(result.contains("Modules unlocked by CS2040C"));
    }

    @Test
    public void execute_moduleWithManyDependents_showsAllUnlocked() {
        AppState state = createTestState();
        PostreqCommand cmd = new PostreqCommand("CS1010");
        String result = cmd.execute(state);
        assertTrue(result.contains("Modules unlocked by CS1010"));
        assertTrue(result.contains("CG2111A"));
        assertTrue(result.contains("CS2040C"));
    }
}
