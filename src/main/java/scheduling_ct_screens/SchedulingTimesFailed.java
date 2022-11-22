package scheduling_ct_screens;

public class SchedulingTimesFailed extends RuntimeException{
    public SchedulingTimesFailed(String error){
        super(error);
    }
}
