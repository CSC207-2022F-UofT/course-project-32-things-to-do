package use_cases.collaborative_task_management.collaborative_task_deletion_use_case;

import entities.CurrentUser;
import entities.StudentUser;
import entities.TaskMap;
import use_cases.task_management.read_write.TaskMapGateway;

/**
 * Collaborative Task Deletion Use Case Interactor (use case layer)
 * Implements business logic on entities
 */

public class CollaborativeTaskDeletionInteractor implements CollaborativeTaskDeletionInputBoundary {
    final TaskMapGateway taskMapGateway;
    final CollaborativeTaskDeletionPresenter presenter;

    /**
     * Interactor for Collaborative Task Deletion.
     * @param taskMapGateway - the gateway that interacts with the TaskMap Database.
     * @param presenter - displays success/fail views
     */
    public CollaborativeTaskDeletionInteractor(TaskMapGateway taskMapGateway, CollaborativeTaskDeletionPresenter presenter) {
        this.taskMapGateway = taskMapGateway;
        this.presenter = presenter;
    }
    /**
     * Attempt to delete a Collaborative Task
     * @param requestModel - request model for deletion
     * @return - response model
     */
    @Override
    public CollaborativeTaskDeletionResponseModel delete(CollaborativeTaskDeletionRequestModel requestModel) {
        ((StudentUser) CurrentUser.getCurrentUser()).removeTaskFromList(requestModel.getTaskId());
        ((StudentUser) CurrentUser.getCurrentUser()).addTaskToArchive(requestModel.getTaskId());


        taskMapGateway.save(TaskMap.getTaskMap());

        CollaborativeTaskDeletionResponseModel responseModel = new CollaborativeTaskDeletionResponseModel(TaskMap.findTask(requestModel.getTaskId()).getTitle(), requestModel.getTaskId());

        return presenter.prepareSuccessView(responseModel);
    }
}
