package use_cases.task_management.task_deletion_use_case;

import entities.TaskMap;
import use_cases.task_management.read_write.ReadWriter;
import use_cases.task_management.read_write.TaskReadWrite;

public class TaskDeletionInteractor implements TaskDeletionInputBoundary {
    final TaskDeletionPresenter presenter;
    public TaskDeletionInteractor(TaskDeletionPresenter presenter) {
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
        requestModel.getStudent().removeTaskFromList(requestModel.getTaskId());
        requestModel.getStudent().addTaskToArchive(requestModel.getTaskId());

        // save changes
        ReadWriter trw = new TaskReadWrite("src/main/java/data/TaskMap.txt");
        TaskMap.saveToFile(trw);

        // create response model
        TaskDeletionResponseModel responseModel = new TaskDeletionResponseModel(TaskMap.findTask(requestModel.getTaskId()).getTitle());

        // indicate success
        return presenter.prepareSuccessView(responseModel);
    }
}
