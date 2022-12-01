package use_cases.task_management.task_deletion_use_case;

import entities.Task;
import entities.TaskMap;
import use_cases.task_management.read_write.ReadWriter;
import use_cases.task_management.read_write.TaskReadWrite;

public class TaskDeletionInteractor implements TaskDeletionInputBoundary {
    final TaskDeletionPresenter presenter;
    public TaskDeletionInteractor(TaskDeletionPresenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public TaskDeletionResponseModel delete(TaskDeletionRequestModel requestModel) {
        requestModel.getStudent().removeTaskFromList(requestModel.getTaskId());
        requestModel.getStudent().addTaskToArchive(requestModel.getTaskId());

        ReadWriter trw = new TaskReadWrite("src/data/TaskMap.txt");
        TaskMap.saveToFile(trw);

        TaskDeletionResponseModel responseModel = new TaskDeletionResponseModel(TaskMap.findTask(requestModel.getTaskId()).getTitle());
        return presenter.prepareSuccessView(responseModel);
    }
    /*
    removed but could bring back if needed

    public TaskDeletionResponseModel deleteCourseTask(TaskDeletionRequestModel requestModel) {
        requestModel.getCourse().removeTask(requestModel.getTask());
        TaskMap.removeTask(requestModel.getTask());

        TaskDeletionResponseModel responseModel = new TaskDeletionResponseModel(requestModel.getTask().getTitle());
        return presenter.prepareSuccessView(responseModel);
    }
    */
}
