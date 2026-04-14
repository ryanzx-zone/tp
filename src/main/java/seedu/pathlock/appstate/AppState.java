package seedu.pathlock.appstate;

import seedu.pathlock.module.ModuleList;
import seedu.pathlock.planner.PlannerList;
import seedu.pathlock.profile.UserProfile;
import seedu.pathlock.storage.PlannerStorage;

public class AppState {
    private ModuleList module;
    private PlannerList planner;
    private UserProfile profile;
    private PlannerStorage plannerStorage;
    private String activePlannerName;

    //@@author Kailer811
    public AppState(ModuleList module, PlannerList planner, UserProfile profile,
                    PlannerStorage plannerStorage) {
        this.module = module;
        this.planner = planner;
        this.profile = profile;
        this.plannerStorage = plannerStorage;
        this.activePlannerName = plannerStorage.getPlannerName();
    }

    public void setPlanner(PlannerList planner) {
        this.planner = planner;
    }

    public ModuleList getModule() {
        return module;
    }

    public PlannerList getPlanner() {
        return planner;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public PlannerStorage getPlannerStorage() {
        return plannerStorage;
    }

    public String getActivePlannerName() {
        return activePlannerName;
    }

    public void switchPlanner(String username, String plannerName) {
        this.activePlannerName = plannerName;
        this.plannerStorage = new PlannerStorage(username, plannerName);
    }

    public void update(ModuleList module, PlannerList planner, UserProfile profile, PlannerStorage plannerStorage) {
        this.module = module;
        this.planner = planner;
        this.profile = profile;
        this.plannerStorage = plannerStorage;
        this.activePlannerName = plannerStorage.getPlannerName();
    }
    //@@author
}
