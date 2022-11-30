package screens.collaborative_task_scheduling;

public class SchedulingTimesFailed extends RuntimeException{
    public SchedulingTimesFailed(String error){
        super(error);
    }
}
