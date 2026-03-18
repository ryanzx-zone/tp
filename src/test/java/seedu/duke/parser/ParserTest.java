package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.CountCommand;
import seedu.duke.command.DoneCommand;
import seedu.duke.command.ListCompletedCommand;
import seedu.duke.command.ListIncompleteCommand;
import seedu.duke.command.ListNeededCommand;
import seedu.duke.command.RemoveCommand;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void parseCommand_listCompleted_returnsListCompletedCommand() {
        Command result = Parser.parseCommand("list completed");
        assertTrue(result instanceof ListCompletedCommand);
    }

    @Test
    public void parseCommand_listIncomplete_returnsListIncompleteCommand() {
        Command result = Parser.parseCommand("list incomplete");
        assertTrue(result instanceof ListIncompleteCommand);
    }

    @Test
    public void parseCommand_listNeeded_returnsListNeededCommand() {
        Command result = Parser.parseCommand("list needed");
        assertTrue(result instanceof ListNeededCommand);
    }

    @Test
    public void parseCommand_count_returnsCountCommand() {
        Command result = Parser.parseCommand("count");
        assertTrue(result instanceof CountCommand);
    }

    @Test
    public void parseCommand_doneWithModuleCode_returnsDoneCommand() {
        Command result = Parser.parseCommand("done CS2113");
        assertTrue(result instanceof DoneCommand);
    }

    @Test
    public void parseCommand_removeWithModuleCode_returnsRemoveCommand() {
        Command result = Parser.parseCommand("remove CS2113");
        assertTrue(result instanceof RemoveCommand);
    }

    @Test
    public void parseCommand_invalidInput_returnsNull() {
        assertNull(Parser.parseCommand("blahblah"));
    }

    @Test
    public void parseCommand_emptyString_returnsNull() {
        assertNull(Parser.parseCommand(""));
    }
}
