package scheduling_ct_use_case;

public class ScheduleCTResponseModel {

    private final boolean isConflict;
    private String displayString;

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
