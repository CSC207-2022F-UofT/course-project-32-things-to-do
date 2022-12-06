package use_cases.task_management.task_edit_use_case;

import entities.*;
import use_cases.calendar_scheduler.schedule_conflict_use_case.ScheduleConflictOutputBoundary;
import use_cases.calendar_scheduler.scheduler_use_case.SchedulerInteractor;
import use_cases.calendar_scheduler.scheduler_use_case.SchedulerRequestModel;
import use_cases.calendar_scheduler.scheduler_use_case.SchedulerResponseModel;
import use_cases.task_management.read_write.TaskMapGateway;

public class TaskEditInteractor implements TaskEditInputBoundary {
    private final TaskMapGateway taskMapGateway;
    private final TaskEditPresenter presenter;
    private final StudentUser student = (StudentUser) CurrentUser.getCurrentUser();

    // for connecting to Scheduler use case
    private SchedulerInteractor scheduler;

    /**
     * An interactor for editing Tasks
     * @param presenter - displays success/fail views
     */
    public TaskEditInteractor (TaskMapGateway taskMapGateway, TaskEditPresenter presenter,
                               ScheduleConflictOutputBoundary scheduleConflictOutputBoundary) {
        this.taskMapGateway = taskMapGateway;
        this.presenter = presenter;
        this.scheduler = new SchedulerInteractor(scheduleConflictOutputBoundary);
    }
    /**
     * Attempt to edit a Task
     * @param requestModel - the request model of the Task being edited
     * @param type - type of Task
     * @return - response model
     */
    @Override
    public TaskEditResponseModel edit(TaskEditRequestModel requestModel, String type) {
        if (type.equals("Event")) { // Event being edited
            EventEditRequestModel request = (EventEditRequestModel) requestModel;
            Event event = (Event) TaskMap.findTask(request.getId());

            // change event priority
            event.setPriority(requestModel.getPriority());

            // change event time block
            Event scheduleEvent = new Event(event.getTitle(), event.getId(), event.getPriority(), request.getStartTime(), request.getEndTime(), event.getRecurring(), event.getFrequency());
            SchedulerRequestModel scheduleRequestModel = new SchedulerRequestModel(scheduleEvent, student);
            SchedulerResponseModel schedulerResponseModel = scheduler.schedule(scheduleRequestModel);
            if (!schedulerResponseModel.isScheduleCancel()) {
                event.setTimeBlock(request.getStartTime(), request.getEndTime());
            }

            // change event recurring value + frequency (if applicable)
            event.setRecurring(request.getRecurring(), request.getFrequency());

        } else if (type.equals("Assignment")) { // Assignment being edited
            AssignmentEditRequestModel request = (AssignmentEditRequestModel) requestModel;
            Assignment assignment = (Assignment) TaskMap.findTask(request.getId());

            // change assignment priority
            assignment.setPriority(requestModel.getPriority());

            // change assignment due date
            assignment.setDueDate(request.getDueDate());

            // change assignment weightage
            assignment.setWeightage(request.getWeightage());

            // change time needed to do assignment
            assignment.setTimeNeeded(request.getTimeNeeded());

            // change time spent on assignment so far
            assignment.setTimeSpent(request.getTimeSpent());

        } else { // Test being edited
            TestEditRequestModel request = (TestEditRequestModel) requestModel;
            Test test = (Test) TaskMap.findTask(request.getId());

            // change test priority
            test.setPriority(requestModel.getPriority());

            // change test time block
            Test scheduleTest = new Test(test.getTitle(), test.getId(), test.getPriority(), request.getStartTime(), request.getEndTime(), test.getWeightage());
            SchedulerRequestModel scheduleRequestModel = new SchedulerRequestModel(scheduleTest, student);
            SchedulerResponseModel schedulerResponseModel = scheduler.schedule(scheduleRequestModel);
            if (!schedulerResponseModel.isScheduleCancel()) {
                test.setTimeBlock(request.getStartTime(), request.getEndTime());
            }

            // change test weightage
            test.setWeightage(request.getWeightage());

            // change time needed to study for test
            test.setTimeNeeded(request.getTimeNeeded());

            // change time spent studying
            test.setTimeSpent(request.getTimeSpent());
        }
        // check if the task has been marked complete
        // if it has, move it to the archive
        if (requestModel.getComplete()) {
            TaskMap.findTask(requestModel.getId()).setComplete();
            student.removeTaskFromList(requestModel.getId());
            student.addTaskToArchive(requestModel.getId());
        }

        // save changes
        taskMapGateway.save(TaskMap.getTaskMap());

        TaskEditResponseModel response = new TaskEditResponseModel(
                TaskMap.findTask(requestModel.getId()).getTitle(), requestModel.getId(), type);
        return presenter.prepareSuccessView(response);
    }
}