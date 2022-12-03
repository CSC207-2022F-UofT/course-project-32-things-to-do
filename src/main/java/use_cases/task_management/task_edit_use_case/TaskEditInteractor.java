package use_cases.task_management.task_edit_use_case;

import entities.*;
import use_cases.calendar_scheduler.schedule_conflict_use_case.ScheduleConflictOutputBoundary;
import use_cases.calendar_scheduler.scheduler_use_case.SchedulerInteractor;
import use_cases.calendar_scheduler.scheduler_use_case.SchedulerRequestModel;
import use_cases.calendar_scheduler.scheduler_use_case.SchedulerResponseModel;

public class TaskEditInteractor implements TaskEditInputBoundary {
    private final TaskEditPresenter presenter;
    private final StudentUser student;

    // for connecting to Scheduler use case
    private SchedulerInteractor scheduler;

    public TaskEditInteractor (TaskEditPresenter presenter, StudentUser student,
                               ScheduleConflictOutputBoundary scheduleConflictOutputBoundary) {
        this.presenter = presenter;
        this.student = student;
        this.scheduler = new SchedulerInteractor(scheduleConflictOutputBoundary);
    }
    @Override
    public TaskEditResponseModel edit(TaskEditRequestModel requestModel, String type) {
        if (type.equals("Event")) { // Event being edited
            EventEditRequestModel request = (EventEditRequestModel) requestModel;
            Event event = (Event) TaskMap.findTask(request.getId());
            // change event priority
            event.setPriority(requestModel.getPriority());
            // change event time block
            event.setTimeBlock(request.getStartTime(), request.getEndTime());
            SchedulerRequestModel scheduleRequestModel = new SchedulerRequestModel(event, student);
            SchedulerResponseModel schedulerResponseModel = scheduler.schedule(scheduleRequestModel);
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
            test.setTimeBlock(request.getStartTime(), request.getEndTime());
            SchedulerRequestModel scheduleRequestModel = new SchedulerRequestModel(test, student);
            SchedulerResponseModel schedulerResponseModel = scheduler.schedule(scheduleRequestModel);
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
        TaskEditResponseModel response = new TaskEditResponseModel(
                TaskMap.findTask(requestModel.getId()).getTitle(), type);
        return presenter.prepareSuccessView(response);
    }
}
