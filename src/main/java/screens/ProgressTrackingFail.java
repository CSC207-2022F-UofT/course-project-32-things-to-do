package screens;

/**
 * New Exception type for the Progress Tracker Use Case
 */

public class ProgressTrackingFail extends RuntimeException{
    public ProgressTrackingFail(String error) {
        super(error);
    }
}
