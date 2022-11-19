package screens;

public class SchedulingTimesFailed extends RuntimeException{
    public SchedulingTimesFailed(String error){
        super(error);
    }
}
