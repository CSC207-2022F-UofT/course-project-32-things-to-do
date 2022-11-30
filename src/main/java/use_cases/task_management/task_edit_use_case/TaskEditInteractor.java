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
        if (type.equals("Event")) {
            Event event = (Event) TaskMap.findTask(((EventEditRequestModel)requestModel).getId());
            // change event title
            event.setTitle(requestModel.getTitle());
            // change event priority
            event.setPriority(requestModel.getPriority());
            // change event time block
            event.setTimeBlock(
                    ((EventEditRequestModel)requestModel).getStartTime(),
                    ((EventEditRequestModel)requestModel).getEndTime());
            // change event recurring value + frequency (if applicable)
            event.setRecurring(
                    ((EventEditRequestModel)requestModel).getRecurring(),
                    ((EventEditRequestModel)requestModel).getFrequency());
        } else if (type.equals("Assignment")) {
            Assignment assignment = (Assignment) TaskMap.findTask(((AssignmentEditRequestModel)requestModel).getId());
            // change assignment title
            assignment.setTitle(requestModel.getTitle());
            // change assignment priority
            assignment.setPriority(requestModel.getPriority());
            // change assignment due date
            assignment.setDueDate(((AssignmentEditRequestModel)requestModel).getDueDate());
            // change assignment weightage
            assignment.setWeightage(((AssignmentEditRequestModel)requestModel).getWeightage());
        } else {
            Test test = (Test) TaskMap.findTask(((TestEditRequestModel)requestModel).getId());
            // change test title
            test.setTitle(requestModel.getTitle());
            // change test priority
            test.setPriority(requestModel.getPriority());
            // change test time block
            test.setTimeBlock(
                    ((TestEditRequestModel)requestModel).getStartTime(),
                    ((TestEditRequestModel)requestModel).getEndTime());
            // change test weightage
            test.setWeightage(((TestEditRequestModel)requestModel).getWeightage());
        }
        TaskEditResponseModel response = new TaskEditResponseModel(requestModel.getTitle(), type);
        return presenter.prepareSuccessView(response);
    }
}
