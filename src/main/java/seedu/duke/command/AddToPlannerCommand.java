package seedu.duke.command;

public class AddToPlannerCommand extends Command {
    private final String moduleCode;
    private final String semester;

    public AddToPlannerCommand(String moduleCode, String semester) {
        this.moduleCode = moduleCode;
        this.semester = semester;
    }
}
