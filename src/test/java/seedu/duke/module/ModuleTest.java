package seedu.duke.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModuleTest {

    @Test
    public void constructor_validInputs_fieldsSetCorrectly() {
        Module m = new Module("CS2113", 4);
        assertEquals("CS2113", m.getModuleCode());
        assertEquals(4, m.getModularCredits());
    }

    @Test
    public void isCompleted_newModule_returnsFalse() {
        Module m = new Module("CS2113", 4);
        assertFalse(m.isCompleted());
    }

    @Test
    public void markCompleted_newModule_isCompletedReturnsTrue() {
        Module m = new Module("CS2113", 4);
        m.markCompleted();
        assertTrue(m.isCompleted());
    }

    @Test
    public void markIncompleted_completedModule_isCompletedReturnsFalse() {
        Module m = new Module("CS2113", 4);
        m.markCompleted();
        m.markIncompleted();
        assertFalse(m.isCompleted());
    }

    @Test
    public void toString_validModule_returnsModuleCode() {
        Module m = new Module("CS2113", 4);
        assertEquals("CS2113", m.toString());
    }
}
