package use_cases.task_management.task_creation_use_case;

import entities.*;
import use_cases.calendar_scheduler.schedule_conflict_use_case.*;
import use_cases.calendar_scheduler.scheduler_use_case.*;
import use_cases.task_management.read_write.*;

public class TaskCreationInteractor implements TaskCreationInputBoundary {
    private final TaskMapGateway taskMapRepository;
    private final TaskCreationOutputBoundary outputBoundary;
    private final User user = CurrentUser.getCurrentUser();
    private final String courseName;

    // for connecting to Scheduler use case
    private SchedulerInteractor scheduler;

    /**
     * Interactor for tasks that are involved with scheduling
     * @param taskMapRepository - for saving the task to a file
     * @param outputBoundary - the output boundary for displaying results
     * @param courseName - the name of the course the Task is for, or "none"
     * @param scheduleConflictOutputBoundary - output boundary for the scheduling conflict use case
     */
    public TaskCreationInteractor(TaskMapGateway taskMapRepository, TaskCreationOutputBoundary outputBoundary, String courseName,
                                  ScheduleConflictOutputBoundary scheduleConflictOutputBoundary) {
        this.taskMapRepository = taskMapRepository;
        this.outputBoundary = outputBoundary;
        this.courseName = courseName;
        this.scheduler = new SchedulerInteractor(scheduleConflictOutputBoundary);
    }

    /**
     * Interactor without scheduling (for course task creation)
     */
    public TaskCreationInteractor(TaskMapGateway taskMapRepository, TaskCreationOutputBoundary outputBoundary, String courseName) {
        this.taskMapRepository = taskMapRepository;
        this.outputBoundary = outputBoundary;
        this.courseName = courseName;
    }
    /**
     * Create a Task
     * @param requestModel - request model for Task
     * @param type - type of Task (one of "Event", "Test", "Assignment")
     * @return - response model after Task is created
     */
    public TaskCreationResponseModel create(TaskCreationRequestModel requestModel, String type) {
        // check for blank title input
        if (requestModel.getTitle().equals("")) return outputBoundary.prepareFailView("Please enter a title.");
        // generate unique Task ID
        String id = requestModel.getTitle() + "_" + user.getName() + "_" + courseName;
        // check if ID exists already (due to repeat titles)
        if (TaskMap.findTask(id) != null) return outputBoundary.prepareFailView("Please enter a unique title");

        // new task being created
        Task newTask;
        // create the new Task
        if (type.equals("Assignment")) { // Task is an Assignment
            newTask = new Assignment(requestModel.getTitle(), id, requestModel.getPriority(),
                    ((AssignmentCreationRequestModel)requestModel).getDueDate(),
                    ((AssignmentCreationRequestModel)requestModel).getWeightage());
        } else if (type.equals("Event")) { // Task is an Event
            EventCreationRequestModel request = (EventCreationRequestModel) requestModel;
            // check if frequency is valid (if recurring is checked)
            if (request.getRecurring() &&
                    !(request.getFrequency().equals("daily")
                            || request.getFrequency().equals("weekly")
                            || request.getFrequency().equals("monthly"))) {
                return outputBoundary.prepareFailView("Please enter a valid frequency (\"daily\", \"weekly\", \"monthly\" accepted)");
            }
            // check if times are valid (start time < end time)
            if (request.getStartTime().isAfter(request.getEndTime())) {
                return outputBoundary.prepareFailView("Please enter a valid time block");
            }

            // create new task
            newTask = new Event(requestModel.getTitle(), id, requestModel.getPriority(),
                    request.getStartTime(), request.getEndTime(), request.getRecurring(),
                    request.getFrequency());
        } else { // Task is a Test
            TestCreationRequestModel request = (TestCreationRequestModel) requestModel;
            // check if times are valid (start time < end time)
            if (request.getStartTime().isAfter(request.getEndTime())) {
                return outputBoundary.prepareFailView("Please enter a valid time block");
            }

            // create new task
            newTask = new Test(requestModel.getTitle(), id, requestModel.getPriority(),
                    request.getStartTime(), request.getEndTime(), request.getWeightage());
        }

        // Schedule task if user is a student
        if (user instanceof StudentUser) {
            SchedulerRequestModel scheduleRequestModel = new SchedulerRequestModel(newTask, (StudentUser) user);
            SchedulerResponseModel schedulerResponseModel = scheduler.schedule(scheduleRequestModel);

            if (schedulerResponseModel.isNotScheduleCancel()) {
                TaskMap.addTask(id, newTask);
                ((StudentUser)user).addTaskToList(id);
            } else {
                return outputBoundary.prepareFailView("Scheduling cancelled!");
            }
        } else {
            // save Task to TaskMap
            TaskMap.addTask(id, newTask);
        }

        // save TaskMap to file:
        taskMapRepository.save(TaskMap.getTaskMap());

        // display success to user
        TaskCreationResponseModel response = new TaskCreationResponseModel(requestModel.getTitle(), id, type);
        return outputBoundary.prepareSuccessView(response);
    }
}