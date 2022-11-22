package scheduling_ct_use_case;

/**
 * Response Model for the Scheduling Collaborative Tasks Use Case
 * Acts as the output data object in the use case layer
 */

public class ScheduleCTResponseModel {

    private final boolean isConflict;
    String displayString;

    public ScheduleCTResponseModel(boolean isConflict) {

        this.isConflict = isConflict;
    }

    public boolean getIsConflict() {
        return isConflict;
    }

    public String getDisplayString() {
        return displayString;
    }
    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }
}
