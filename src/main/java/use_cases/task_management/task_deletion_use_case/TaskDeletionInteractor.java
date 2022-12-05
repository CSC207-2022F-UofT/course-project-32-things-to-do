package use_cases.task_management.task_deletion_use_case;

import entities.CurrentUser;
import entities.StudentUser;
import entities.TaskMap;
import use_cases.task_management.read_write.TaskMapGateway;
public class TaskDeletionInteractor implements TaskDeletionInputBoundary {
    final TaskMapGateway taskMapGateway;
    final TaskDeletionPresenter presenter;
    public TaskDeletionInteractor(TaskMapGateway taskMapGateway, TaskDeletionPresenter presenter) {
        this.taskMapGateway = taskMapGateway;
        this.presenter = presenter;
    }
    /**
     * Attempt to delete a Task
     * @param requestModel - request model for deletion
     * @return - response model
     */
    @Override
    public TaskDeletionResponseModel delete(TaskDeletionRequestModel requestModel) {
        // remove the Task
        ((StudentUser) CurrentUser.getCurrentUser()).removeTaskFromList(requestModel.getTaskId());
        ((StudentUser) CurrentUser.getCurrentUser()).addTaskToArchive(requestModel.getTaskId());

        // save changes
        taskMapGateway.save(TaskMap.getTaskMap());

        // create response model
        TaskDeletionResponseModel responseModel = new TaskDeletionResponseModel(TaskMap.findTask(requestModel.getTaskId()).getTitle(), requestModel.getTaskId());

        // indicate success
        return presenter.prepareSuccessView(responseModel);
    }
}
