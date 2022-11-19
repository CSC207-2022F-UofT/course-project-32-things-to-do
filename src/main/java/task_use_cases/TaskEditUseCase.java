package task_use_cases;

import entities.Timeblockable;
import entities.Gradable;
import entities.Preparatory;

import entities.Task;
import entities.Event;
import entities.Assignment;
// import Entities.CollaborativeTask;

import java.time.LocalDateTime;

public class TaskEditUseCase {
    public void setTitle(Task task, String title) {
        task.setTitle(title);
    }
    public void setPriority(Task task, int priority) {
        task.setPriority(priority);
    }
    public void setEventRecurring(Event event, boolean recurring, String frequency) {
        event.setRecurring(recurring, frequency);
    }
    public void setAssignmentDueDate(Assignment assignment, LocalDateTime dueDate) {
        assignment.setDueDate(dueDate);
    }
    public void setTaskTimeBlock(Timeblockable task, LocalDateTime startTime, LocalDateTime endTime) {
        task.setTimeBlock(startTime, endTime);
    }
    public void setTaskWeightage(Gradable task, double weightage) {
        task.setWeightage(weightage);
    }
    public void setTaskGradeReceived(Gradable task, double gradeReceived) {
        task.setGradeReceived(gradeReceived);
    }
    public void setTimeSpent(Preparatory task, double timeSpent) {

    }
    public void setTaskTimeNeeded(Preparatory task, double timeNeeded) {
        task.setTimeNeeded(timeNeeded);
    }
    public void setTaskComplete(Task task) {
        task.setComplete();
    }
}
