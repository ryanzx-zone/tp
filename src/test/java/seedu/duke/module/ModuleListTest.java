package seedu.duke.module;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.DuplicateException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ModuleListTest {

    @Test
    public void addModule_recognisedModule_moduleAdded() throws DuplicateException {
        ModuleList ml = new ModuleList();
        ml.addModule(new Module("CS2113", 4));
        assertEquals(1, ml.getCompletedModules().size());
    }

    @Test
    public void addModule_recognisedModule_moduleMarkedCompleted() throws DuplicateException {
        ModuleList ml = new ModuleList();
        ml.addModule(new Module("CS2113", 4));
        assertTrue(ml.getCompletedModules().get(0).isCompleted());
    }

    @Test
    public void addModule_duplicateModule_exceptionThrown() {
        ModuleList ml = new ModuleList();
        try {
            ml.addModule(new Module("CS2113", 4));
            ml.addModule(new Module("CS2113", 4));
            fail();
        } catch (DuplicateException e) {
            assertEquals("Module CS2113 has already been completed", e.getMessage());
        }
    }

    @Test
    public void addModule_unrecognisedModule_exceptionThrown() {
        ModuleList ml = new ModuleList();
        try {
            ml.addModule(new Module("FAKE1234", 4));
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("FAKE1234"));
        } catch (DuplicateException e) {
            fail();
        }
    }

    @Test
    public void addModule_orGroupModule_moduleAdded() throws DuplicateException {
        ModuleList ml = new ModuleList();
        ml.addModule(new Module("CP3880", 10));
        assertEquals(1, ml.getCompletedModules().size());
    }

    @Test
    public void removeModule_existingModule_returnsTrue() throws DuplicateException {
        ModuleList ml = new ModuleList();
        ml.addModule(new Module("CS2113", 4));
        assertTrue(ml.removeModule("CS2113"));
    }

    @Test
    public void removeModule_existingModule_moduleRemovedFromList() throws DuplicateException {
        ModuleList ml = new ModuleList();
        ml.addModule(new Module("CS2113", 4));
        ml.removeModule("CS2113");
        assertEquals(0, ml.getCompletedModules().size());
    }

    @Test
    public void removeModule_nonExistingModule_returnsFalse() {
        ModuleList ml = new ModuleList();
        assertFalse(ml.removeModule("CS2113"));
    }

    @Test
    public void listCompletedModules_emptyList_returnsNoModulesMessage() {
        ModuleList ml = new ModuleList();
        assertEquals("No modules completed yet.", ml.listCompletedModules());
    }

    @Test
    public void listCompletedModules_nonEmptyList_containsModuleCode() throws DuplicateException {
        ModuleList ml = new ModuleList();
        ml.addModule(new Module("CS2113", 4));
        String result = ml.listCompletedModules();
        assertTrue(result.contains("CS2113"));
    }

    @Test
    public void countMcs_emptyList_showsZeroCompleted() {
        ModuleList ml = new ModuleList();
        String result = ml.countMcs();
        assertTrue(result.contains("Completed: 0 / 160 MCs"));
    }

    @Test
    public void countMcs_oneModule_showsCorrectMcs() throws DuplicateException {
        ModuleList ml = new ModuleList();
        ml.addModule(new Module("CS2113", 4));
        String result = ml.countMcs();
        assertTrue(result.contains("Completed: 4 / 160 MCs"));
    }

    @Test
    public void getMcForModule_knownModule_returnsCorrectMc() {
        assertEquals(2, new ModuleList().getMcForModule("MA1511"));
    }

    @Test
    public void getMcForModule_unknownModule_throwsException() {
        ModuleList moduleList = new ModuleList();

        assertThrows(IllegalArgumentException.class, () -> {
            moduleList.getMcForModule("UNKNOWN1234");
        });
    }

    @Test
    public void getTotalGraduationMcs_always_returns160() {
        assertEquals(160, ModuleList.getTotalGraduationMcs());
    }

    @Test
    public void listIncompleteModules_noModulesCompleted_containsAllRequired() {
        ModuleList ml = new ModuleList();
        String result = ml.listIncompleteModules();
        assertTrue(result.contains("CS2113"));
        assertTrue(result.contains("MA1511"));
    }

    @Test
    public void listIncompleteModules_oneModuleCompleted_doesNotContainIt() throws DuplicateException {
        ModuleList ml = new ModuleList();
        ml.addModule(new Module("CS2113", 4));
        String result = ml.listIncompleteModules();
        assertFalse(result.contains("CS2113"));
    }


    @Test
    public void getMcForModule_twoMcModule_returnsTwo() {
        assertEquals(2, new ModuleList().getMcForModule("MA1512"));
        assertEquals(2, new ModuleList().getMcForModule("CG2027"));
        assertEquals(2, new ModuleList().getMcForModule("EG2401A"));
    }

    @Test
    public void getMcForModule_fourMcModule_returnsFour() {
        assertEquals(4, new ModuleList().getMcForModule("CS2113"));
        assertEquals(4, new ModuleList().getMcForModule("CG2271"));
        assertEquals(4, new ModuleList().getMcForModule("EE2026"));
    }

    @Test
    public void getMcForModule_eightMcModule_returnsEight() {
        assertEquals(8, new ModuleList().getMcForModule("CG4002"));
        assertEquals(8, new ModuleList().getMcForModule("CP4106"));
    }

    @Test
    public void getMcForModule_internshipModule_returnsCorrectMc() {
        assertEquals(12, new ModuleList().getMcForModule("CP3880"));
        assertEquals(10, new ModuleList().getMcForModule("EG3611A"));
    }

    @Test
    public void getMcForModule_lowercaseInput_returnsCorrectMc() {
        assertEquals(2, new ModuleList().getMcForModule("ma1511"));
        assertEquals(4, new ModuleList().getMcForModule("cs2113"));
    }

    @Test
    public void countMcs_emptyList_showsZeroPercentage() {
        ModuleList ml = new ModuleList();
        String result = ml.countMcs();
        assertTrue(result.contains("0.0%"));
        assertTrue(result.contains("Incomplete: 160 MCs"));
    }

    @Test
    public void countMcs_multipleModules_showsCorrectPercentage() throws DuplicateException {
        ModuleList ml = new ModuleList();
        ml.addModule(new Module("MA1511", 2));
        ml.addModule(new Module("MA1512", 2));
        ml.addModule(new Module("CS2113", 4));
        ml.addModule(new Module("CG4002", 8));
        String result = ml.countMcs();
        assertTrue(result.contains("Completed: 16 / 160 MCs (10.0%)"));
        assertTrue(result.contains("Incomplete: 144 MCs (90.0%)"));
    }

    @Test
    public void countMcs_manyModules_showsCorrectMcs() throws DuplicateException {
        ModuleList ml = new ModuleList();
        ml.addModule(new Module("CP3880", 10));
        ml.addModule(new Module("CG4002", 8));
        ml.addModule(new Module("CS2113", 4));
        ml.addModule(new Module("CS2040C", 4));
        ml.addModule(new Module("CS2107", 4));
        ml.addModule(new Module("CG2271", 4));
        ml.addModule(new Module("CG2023", 4));
        ml.addModule(new Module("CS1231", 4));
        String result = ml.countMcs();
        assertTrue(result.contains("Completed: 44 / 160 MCs"));
    }

    @Test
    public void countMcs_afterRemovingModule_updatesCorrectly() throws DuplicateException {
        ModuleList ml = new ModuleList();
        ml.addModule(new Module("CS2113", 4));
        ml.addModule(new Module("MA1511", 2));
        ml.removeModule("CS2113");
        String result = ml.countMcs();
        assertTrue(result.contains("Completed: 2 / 160 MCs"));
    }

    @Test
    public void listNeededModules_always_containsRequiredModules() {
        ModuleList ml = new ModuleList();
        String result = ml.listNeededModules();
        assertTrue(result.contains("CS2113"));
        assertTrue(result.contains("CG4002"));
    }
}
