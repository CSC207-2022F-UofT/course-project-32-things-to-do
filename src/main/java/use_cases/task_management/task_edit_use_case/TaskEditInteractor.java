package use_cases.task_management.task_edit_use_case;

public class TaskEditInteractor implements TaskEditInputBoundary {
    private final TaskEditPresenter presenter;

    public TaskEditInteractor (TaskEditPresenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public TaskEditResponseModel edit(TaskEditRequestModel requestModel, String type) {
        if (type.equals("Event")) {
            // change event title
            ((EventEditRequestModel)requestModel).getEvent().setTitle(requestModel.getTitle());
            // change event priority
            ((EventEditRequestModel)requestModel).getEvent().setPriority(requestModel.getPriority());
            // change event time block
            ((EventEditRequestModel)requestModel).getEvent().setTimeBlock(
                    ((EventEditRequestModel)requestModel).getStartTime(),
                    ((EventEditRequestModel)requestModel).getEndTime());
            // change event recurring value + frequency (if applicable)
            ((EventEditRequestModel)requestModel).getEvent().setRecurring(
                    ((EventEditRequestModel)requestModel).getRecurring(),
                    ((EventEditRequestModel)requestModel).getFrequency());
        } else if (type.equals("Assignment")) {
            // change assignment title
            ((AssignmentEditRequestModel)requestModel).getAssignment().setTitle(requestModel.getTitle());
            // change assignment priority
            ((AssignmentEditRequestModel)requestModel).getAssignment().setPriority(requestModel.getPriority());
            // change assignment due date
            ((AssignmentEditRequestModel)requestModel).getAssignment().setDueDate(
                    ((AssignmentEditRequestModel)requestModel).getDueDate());
            // change assignment weightage
            ((AssignmentEditRequestModel)requestModel).getAssignment().setWeightage(
                    ((AssignmentEditRequestModel)requestModel).getWeightage());
        } else {
            // change test title
            ((TestEditRequestModel)requestModel).getTest().setTitle(requestModel.getTitle());
            // change test priority
            ((TestEditRequestModel)requestModel).getTest().setPriority(requestModel.getPriority());
            // change test time block
            ((TestEditRequestModel)requestModel).getTest().setTimeBlock(
                    ((TestEditRequestModel)requestModel).getStartTime(),
                    ((TestEditRequestModel)requestModel).getEndTime());
            // change test weightage
            ((TestEditRequestModel)requestModel).getTest().setWeightage(
                    ((TestEditRequestModel)requestModel).getWeightage());
        }
        TaskEditResponseModel response = new TaskEditResponseModel(requestModel.getTitle(), type);
        return presenter.prepareSuccessView(response);
    }
}
