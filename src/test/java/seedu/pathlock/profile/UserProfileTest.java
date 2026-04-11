package seedu.pathlock.profile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserProfileTest {

    // Constructor Tests
    
    @Test
    public void constructor_validInputs_fieldsSetCorrectly() {
        UserProfile profile = new UserProfile("Alice", 4.0);
        assertEquals("Alice", profile.getName());
        assertEquals(4.0, profile.getGpa());
    }

    @Test
    public void constructor_nameWithLeadingTrailingSpaces_nameIsTrimmed() {
        UserProfile profile = new UserProfile("  Bob  ", 3.5);
        assertEquals("Bob", profile.getName());
    }

    @Test
    public void constructor_validGpaLowerBoundary_doesNotThrow() {
        assertDoesNotThrow(() -> new UserProfile("Alice", 2.0));
    }

    @Test
    public void constructor_validGpaUpperBoundary_doesNotThrow() {
        assertDoesNotThrow(() -> new UserProfile("Alice", 5.0));
    }

    @Test
    public void constructor_emptyName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new UserProfile("", 3.5));
    }

    @Test
    public void constructor_gpaBelowMinimum_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new UserProfile("Alice", 1.9));
        assertTrue(ex.getMessage().contains("2.0"));
    }

    @Test
    public void constructor_gpaAboveMaximum_throwsIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new UserProfile("Alice", 5.1));
        assertTrue(ex.getMessage().contains("5.0"));
    }

    // getRecommendedMaxWorkload Tests
    
    @Test
    public void getRecommendedMaxWorkload_gpaOf5_returns32() {
        UserProfile profile = new UserProfile("Alice", 5.0);
        assertEquals(32, profile.getRecommendedMaxWorkload());
    }

    @Test
    public void getRecommendedMaxWorkload_gpaAbove45_returns32() {
        UserProfile profile = new UserProfile("Alice", 4.5);
        assertEquals(32, profile.getRecommendedMaxWorkload());
    }

    // boundary testing
    @Test
    public void getRecommendedMaxWorkload_gpaJustBelow45_returns28() {
        UserProfile profile = new UserProfile("Alice", 4.49);
        assertEquals(28, profile.getRecommendedMaxWorkload());
    }

    @Test
    public void getRecommendedMaxWorkload_gpaOf42_returns28() {
        UserProfile profile = new UserProfile("Alice", 4.2);
        assertEquals(28, profile.getRecommendedMaxWorkload());
    }

    @Test
    public void getRecommendedMaxWorkload_gpaBetween40And449_returns28() {
        UserProfile profile = new UserProfile("Alice", 4.0);
        assertEquals(28, profile.getRecommendedMaxWorkload());
    }

    // boundary testing
    @Test
    public void getRecommendedMaxWorkload_gpaJustBelow40_returns26() {
        UserProfile profile = new UserProfile("Alice", 3.99);
        assertEquals(26, profile.getRecommendedMaxWorkload());
    }

    @Test
    public void getRecommendedMaxWorkload_gpaOf35_returns26() {
        UserProfile profile = new UserProfile("Alice", 3.5);
        assertEquals(26, profile.getRecommendedMaxWorkload());
    }

    @Test
    public void getRecommendedMaxWorkload_gpaBetween30And399_returns26() {
        UserProfile profile = new UserProfile("Alice", 3.0);
        assertEquals(26, profile.getRecommendedMaxWorkload());
    }

    // boundary testing
    @Test
    public void getRecommendedMaxWorkload_gpaJustBelow30_returns24() {
        UserProfile profile = new UserProfile("Alice", 2.99);
        assertEquals(24, profile.getRecommendedMaxWorkload());
    }

    @Test
    public void getRecommendedMaxWorkload_gpaOf25_returns24() {
        UserProfile profile = new UserProfile("Alice", 2.5);
        assertEquals(24, profile.getRecommendedMaxWorkload());
    }

    @Test
    public void getRecommendedMaxWorkload_gpaZeroFreshman_returns20() {
        UserProfile profile = new UserProfile("Alice", 0.0);
        assertEquals(20, profile.getRecommendedMaxWorkload());
    }
}
