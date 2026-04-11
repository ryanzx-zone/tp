package seedu.pathlock.profile;

public class UserProfile {
    private final String name;
    private final double gpa;

    public UserProfile(String name, double gpa) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (gpa != 0.0 && (gpa < 2.0 || gpa > 5.0)) {
            throw new IllegalArgumentException("GPA must be between 2.0 and 5.0.");
        }

        this.name = name.trim();
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public int getRecommendedMaxWorkload() {
        if (gpa == 0.0) {  // y1s1 freshmen: 18–20 MCs workload
            return 20;
        } else if (gpa >= 4.5) {
            return 32;
        } else if (gpa >= 4.0) {
            return 28;
        } else if (gpa >= 3.0) {
            return 26;
        } else {
            return 24;
        }
    }
}
