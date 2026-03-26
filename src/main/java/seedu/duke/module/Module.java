package seedu.duke.module;

import seedu.duke.Planner.ModuleStatus;

public class Module {
    private final String moduleCode;
    private final int modularCredits;
    private ModuleStatus Status;
    private String semester;

    public Module(String moduleCode, int modularCredits) {
        this.moduleCode = moduleCode;
        this.modularCredits = modularCredits;
        this.Status = ModuleStatus.UNPLANNED;
        this.semester = "";
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public int getModularCredits() {
        return modularCredits;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public void markIncompleted() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return moduleCode;
    }

}
