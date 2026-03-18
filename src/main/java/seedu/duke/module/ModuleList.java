package seedu.duke.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.duke.exception.DuplicateException;

public class ModuleList {

    private static final int TOTAL_GRADUATION_MCS = 80;

    private static final List<String> REQUIRED_MODULES = Arrays.asList(
            // engineering core
            "MA1511", "MA1512", "MA1508E", "EG2401A",

            // CEG discipline core
            "CG1111A", "CG2111A", "CS1231", "CG2023", "CG2027", "CG2028", "CG2271",
            "CG3201", "CG3207", "CS2040C", "CS2107", "CS2113", "EE2026", "EE4204"
    );

    // OR groups: completing any one module in a group fulfils the requirement
    private static final List<List<String>> OR_GROUPS = Arrays.asList(
            // internship: CP3880 OR EG3611A
            Arrays.asList("CP3880", "EG3611A"),
            // capstone: CG4002 OR CG4001 OR CP4106 OR EE4002D OR EE4002R OR CDE4301
            Arrays.asList("CG4002", "CG4001", "CP4106", "EE4002D", "EE4002R", "CDE4301")
    );

    private static final Map<String, Integer> MODULE_MC_MAP = new HashMap<>();

    public final List<Module> completedModules;

    static {
        // engineering core
        MODULE_MC_MAP.put("MA1511", 2);
        MODULE_MC_MAP.put("MA1512", 2);
        MODULE_MC_MAP.put("MA1508E", 4);
        MODULE_MC_MAP.put("EG2401A", 2);

        // internship OR options (10 MCs each)
        MODULE_MC_MAP.put("CP3880", 10);
        MODULE_MC_MAP.put("EG3611A", 10);

        // CEG discipline core
        MODULE_MC_MAP.put("CG1111A", 4);
        MODULE_MC_MAP.put("CG2111A", 4);
        MODULE_MC_MAP.put("CS1231", 4);
        MODULE_MC_MAP.put("CG2023", 4);
        MODULE_MC_MAP.put("CG2027", 2);
        MODULE_MC_MAP.put("CG2028", 2);
        MODULE_MC_MAP.put("CG2271", 4);
        MODULE_MC_MAP.put("CG3201", 4);
        MODULE_MC_MAP.put("CG3207", 4);
        MODULE_MC_MAP.put("CS2040C", 4);
        MODULE_MC_MAP.put("CS2107", 4);
        MODULE_MC_MAP.put("CS2113", 4);
        MODULE_MC_MAP.put("EE2026", 4);
        MODULE_MC_MAP.put("EE4204", 4);

        // capstone OR options (8 MCs each)
        MODULE_MC_MAP.put("CG4002", 8);
        MODULE_MC_MAP.put("CG4001", 8);
        MODULE_MC_MAP.put("CP4106", 8);
        MODULE_MC_MAP.put("EE4002D", 8);
        MODULE_MC_MAP.put("EE4002R", 8);
        MODULE_MC_MAP.put("CDE4301", 8);
    }

    public ModuleList(List<Module> completedModules) {
        this.completedModules = completedModules;
    }

    /**
     * Returns the MC value for a given module code.
     *
     * @param moduleCode Module code to look up.
     * @return MC value, or 4 as default if not found.
     */
    public static int getMcForModule(String moduleCode) {
        return MODULE_MC_MAP.getOrDefault(moduleCode.toUpperCase(), 4);
    }

    public static int getTotalGraduationMcs() {
        return TOTAL_GRADUATION_MCS;
    }

    /**
     * Checks if the given module code is a recognised required module.
     */
    private boolean isRecognisedModule(String moduleCode) {
        for (String required : REQUIRED_MODULES) {
            if (required.equalsIgnoreCase(moduleCode)) {
                return true;
            }
        }
        for (List<String> orGroup : OR_GROUPS) {
            for (String option : orGroup) {
                if (option.equalsIgnoreCase(moduleCode)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addModule(Module newModule) throws DuplicateException {
        if (!isRecognisedModule(newModule.getModuleCode())) {
            throw new IllegalArgumentException(newModule.getModuleCode()
                    + " is not a recognised module in the required list.");
        }
        for (Module module : completedModules) {
            if (module.getModuleCode().equalsIgnoreCase(newModule.getModuleCode())) {
                throw new DuplicateException(newModule.getModuleCode());
            }
        }
        newModule.markCompleted();
        completedModules.add(newModule);
    }

    public boolean removeModule(String moduleCode) {
        for (int i = 0; i < completedModules.size(); i++) {
            Module module = completedModules.get(i);
            if (module.getModuleCode().equalsIgnoreCase(moduleCode)) {
                module.markIncompleted();
                completedModules.remove(i);
                return true;
            }
        }
        return false;
    }

    private boolean isInCompletedList(String moduleCode) {
        for (Module module : completedModules) {
            if (module.getModuleCode().equalsIgnoreCase(moduleCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if any module in the given OR group has been completed.
     */
    private boolean isOrGroupFulfilled(List<String> orGroup) {
        for (String moduleCode : orGroup) {
            if (isInCompletedList(moduleCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a list of completed modules.
     *
     * @return A numbered list of completed modules or a message
     *         indicating that no modules have been completed.
     */
    public String listCompletedModules() {
        if (completedModules.isEmpty()) {
            return "No modules completed yet.";
        }

        StringBuilder completedModulesList = new StringBuilder("Completed modules:\n");
        for (int i = 0; i < completedModules.size(); i++) {
            completedModulesList.append(i + 1)
                    .append(". ")
                    .append(completedModules.get(i).getModuleCode())
                    .append("\n");
        }
        return completedModulesList.toString().trim();
    }

    /**
     * Returns a list of all modules required for graduation.
     *
     * @return A numbered list of required modules and OR module groups
     *         that are needed for graduation.
     */
    public String listNeededModules() {
        StringBuilder neededModulesList = new StringBuilder("Modules required for graduation:\n");
        int index = 1;
        for (String module : REQUIRED_MODULES) {
            neededModulesList.append(index).append(". ").append(module).append("\n");
            index++;
        }
        for (List<String> orGroup : OR_GROUPS) {
            neededModulesList.append(index).append(". ")
                    .append(String.join(" OR ", orGroup))
                    .append("\n");
            index++;
        }
        return neededModulesList.toString().trim();
    }

    /**
     * Returns a summary of completed and remaining MCs with percentage.
     */
    public String countMcs() {
        int completedMcs = 0;
        for (Module module : completedModules) {
            completedMcs += module.getModularCredits();
        }
        int remainingMcs = TOTAL_GRADUATION_MCS - completedMcs;
        if (remainingMcs < 0) {
            remainingMcs = 0;
        }
        double percentage = (double) completedMcs / TOTAL_GRADUATION_MCS * 100;
        double remainingPercentage = 100.0 - percentage;

        return String.format("Completed: %d / %d MCs (%.1f%%)\n"
                + "Incomplete: %d MCs (%.1f%%)",
                completedMcs, TOTAL_GRADUATION_MCS, percentage,
                remainingMcs, remainingPercentage);
    }

    /**
     * Returns a list of required modules that have not yet been completed.
     *
     * Modules already marked as completed will not appear in the list.
     * OR module groups are considered completed if any module in the group
     * has been completed.
     *
     * @return A numbered list of incomplete modules or a message indicating
     *         that all required modules have been completed.
     */
    public String listIncompleteModules() {
        List<String> incompleteModules = new ArrayList<>();

        for (String requiredModule : REQUIRED_MODULES) {
            if (!isInCompletedList(requiredModule)) {
                incompleteModules.add(requiredModule);
            }
        }

        for (List<String> orGroup : OR_GROUPS) {
            if (!isOrGroupFulfilled(orGroup)) {
                incompleteModules.add(String.join(" OR ", orGroup));
            }
        }

        if (incompleteModules.isEmpty()) {
            return "All required modules have been completed.";
        }

        StringBuilder incompleteModulesList = new StringBuilder("Incomplete modules:\n");
        for (int i = 0; i < incompleteModules.size(); i++) {
            incompleteModulesList.append(i + 1)
                    .append(". ")
                    .append(incompleteModules.get(i))
                    .append("\n");
        }
        return incompleteModulesList.toString().trim();
    }
}
