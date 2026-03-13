package seedu.duke.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuplicateExceptionTest {

    @Test
    public void constructor_moduleCode_messageFormattedCorrectly() {
        DuplicateException e = new DuplicateException("CS2113");
        assertEquals("Module CS2113 has already been completed", e.getMessage());
    }
}
