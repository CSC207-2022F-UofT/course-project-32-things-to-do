package use_cases.course_tracker.progress_tracker_use_case;

import java.util.ArrayList;

/**
 * Response Model for the Progress Tracker Use Case
 * Acts as the output data object in the use case layer
 */

public class ProgressTrackerResponseModel {

    private final double courseProgress;

    private final double mockGrade;

    private final double requiredAverage;

    private final ArrayList<String> ungradedTasks;

    public ProgressTrackerResponseModel(double courseProgress, double mockGrade, double requiredAverage,
                                        ArrayList<String> ungradedTasks) {
        this.courseProgress = courseProgress;
        this.mockGrade = mockGrade;
        this.requiredAverage = requiredAverage;
        this.ungradedTasks = ungradedTasks;
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

    public ArrayList<String> getUngradedTasks() {
        return ungradedTasks;
    }

}
