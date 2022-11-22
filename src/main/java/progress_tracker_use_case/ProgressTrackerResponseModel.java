package progress_tracker_use_case;

/**
 * Response Model for the Progress Tracker Use Case
 * Acts as the output data object in the use case layer
 */

public class ProgressTrackerResponseModel {

    private final double courseProgress;

    private final double mockGrade;

    private final double requiredAverage;

    String displayString;

    public ProgressTrackerResponseModel(double courseProgress, double mockGrade, double requiredAverage) {
        this.courseProgress = courseProgress;
        this.mockGrade = mockGrade;
        this.requiredAverage = requiredAverage;
        this.displayString = "";
    }

    public double getCourseProgress() {
        return courseProgress;
    }

    public double getMockGrade() {
        return mockGrade;
    }

    public double getRequiredAverage() {
        return requiredAverage;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }
}
