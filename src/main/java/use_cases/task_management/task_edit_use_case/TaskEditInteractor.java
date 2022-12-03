package use_cases.task_management.task_edit_use_case;

import entities.*;

public class TaskEditInteractor implements TaskEditInputBoundary {
    private final TaskEditPresenter presenter;
    private final StudentUser student = (StudentUser) CurrentUser.getCurrentUser();

    /**
     * An interactor for editing Tasks
     * @param presenter - displays success/fail views
     */
    public TaskEditInteractor (TaskEditPresenter presenter) {
        this.presenter = presenter;
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
            event.setTimeBlock(request.getStartTime(), request.getEndTime());
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


        TaskEditResponseModel response = new TaskEditResponseModel(
                TaskMap.findTask(requestModel.getId()).getTitle(), type);
        return presenter.prepareSuccessView(response);
    }
}
