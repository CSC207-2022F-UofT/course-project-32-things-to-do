package use_cases.task_management.task_edit_use_case;

import entities.Assignment;
import entities.Event;
import entities.TaskMap;
import entities.Test;

public class TaskEditInteractor implements TaskEditInputBoundary {
    private final TaskEditPresenter presenter;

    public TaskEditInteractor (TaskEditPresenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public TaskEditResponseModel edit(TaskEditRequestModel requestModel, String type) {
        if (type.equals("Event")) { // Event being edited
            EventEditRequestModel request = (EventEditRequestModel) requestModel;
            Event event = (Event) TaskMap.findTask(request.getId());
            // change event title
            event.setTitle(requestModel.getTitle());
            // change event priority
            event.setPriority(requestModel.getPriority());
            // change event time block
            event.setTimeBlock(request.getStartTime(), request.getEndTime());
            // change event recurring value + frequency (if applicable)
            event.setRecurring(request.getRecurring(), request.getFrequency());

        } else if (type.equals("Assignment")) { // Assignment being edited
            AssignmentEditRequestModel request = (AssignmentEditRequestModel) requestModel;
            Assignment assignment = (Assignment) TaskMap.findTask(request.getId());
            // change assignment title
            assignment.setTitle(requestModel.getTitle());
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
            // change test title
            test.setTitle(requestModel.getTitle());
            // change test priority
            test.setPriority(requestModel.getPriority());
            // change test time block
            test.setTimeBlock(request.getStartTime(), request.getEndTime());
            // change test weightage
            test.setWeightage(request.getWeightage());
            // change time needed to study for test
            test.setTimeNeeded(request.getTimeNeeded());
            // change time spent studying
            test.setTimeSpent(request.getTimeSpent());
        }
        TaskEditResponseModel response = new TaskEditResponseModel(requestModel.getTitle(), type);
        return presenter.prepareSuccessView(response);
    }
}
