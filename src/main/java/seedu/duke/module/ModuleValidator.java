package seedu.duke.module;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ModuleValidator {

    private static final Logger logger = Logger.getLogger(ModuleValidator.class.getName());

    /**
     * NUS module code format: 2-3 uppercase letters, 4 digits, optional trailing letter.
     * Example: CS2040C, CS1010E
     */
    private static final String MODULE_CODE_FORMAT = "^[A-Z]{2,3}\\d{4}[A-Z]?$";
    private ModuleValidator() {}

    /**
     * Returns true if the module code matches the NUS format
     *
     * @param code Module code to check (already uppercase)
     * @return true if valid format, false otherwise
     */
    public static boolean isValidModuleCode(String code) {
        if (code == null || code.isBlank()) {
            return false;
        }
        return code.matches(MODULE_CODE_FORMAT);
    }

    /**
     * Validates that the module code is non-null, non-empty, and matches the NUS format
     *
     * @param code Module code to validate.
     */
    public static void validateModuleCode(String code) {
        if (code == null || code.isBlank()) {
            logger.log(Level.WARNING, "Module code was null or blank");
            throw new IllegalArgumentException("Module code cannot be empty.");
        }
        if (!isValidModuleCode(code)) {
            logger.log(Level.WARNING, "Invalid module code format: {0}", code);
            throw new IllegalArgumentException( "Invalid module code format: \"" + code + "\".");
        }
    }

    /**
     * Validates that an MC (Modular Credit) value is positive integer
     *
     * @param mc MC value to validate (must not be null).
     */
    public static void validateMc(Integer mc) {
        if (mc == null) {
            logger.log(Level.WARNING, "MC value was null");
            throw new IllegalArgumentException("MC value is required. Use /mc <number> (e.g. /mc 4).");
        }
        if (mc <= 0) {
            logger.log(Level.WARNING, "MC value was non-positive: {0}", mc);
            throw new IllegalArgumentException(
                    "MC must be a positive integer, but got: " + mc + "."
            );
        }
    }

    /**
     * Validates that a user-supplied MC(Modular credit) matches the expected MC for Core Modules
     * If the user did not supply an MC (mc is null), this check is skipped.
     *
     * @param suppliedMc Mc provided by the user, or null if not supplied
     * @param expectedMc The correct MC for this module from the module database (JSON file)
     * @param moduleCode Module code
     */
    public static void validateInternalMc(Integer suppliedMc, int expectedMc, String moduleCode) {
        if (suppliedMc == null) {
            return;
        }
        if (suppliedMc != expectedMc) {
            logger.log(Level.WARNING,
                    "MC mismatch for {0}: expected {1}, got {2}",
                    new Object[]{moduleCode, expectedMc, suppliedMc});
            throw new IllegalArgumentException(
                    "Incorrect MC for " + moduleCode + ". "
                            + "Expected: " + expectedMc + ", but got: " + suppliedMc + "."
            );
        }
    }
}
