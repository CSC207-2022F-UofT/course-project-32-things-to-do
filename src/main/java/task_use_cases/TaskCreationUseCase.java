package task_use_cases;

import entities.Event;
import entities.Test;
import entities.Assignment;
// import Entities.CollaborativeTask;

import java.time.LocalDateTime;

public class TaskCreationUseCase {
    public void createEvent(String title, String id, int priority, LocalDateTime startTime, LocalDateTime endTime,
                            boolean recurring, String frequency) {
        Event newEvent = new Event(title, id, priority, startTime, endTime, recurring, frequency);
    }
    public void createStudentTest(String title, String id, int priority, LocalDateTime startTime, LocalDateTime endTime,
                                  double weightage) {
        Test newTest = new Test(title, id, priority, startTime, endTime, weightage);
    }
    public void createInstructorTest(String title, String id, LocalDateTime startTime, LocalDateTime endTime,
                                     double weightage) {
        Test newTest = new Test(title, id, startTime, endTime, weightage);
    }
    public void createStudentAssignment(String title, String id, int priority, LocalDateTime dueDate) {
        Assignment newAssignment = new Assignment(title, id, priority, dueDate);
    }
    public void createInstructorAssignment(String title, String id, LocalDateTime dueDate) {
        Assignment newAssignment = new Assignment(title, id, dueDate);
    }
}
