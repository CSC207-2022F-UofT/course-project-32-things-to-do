package task_deletion_use_case;

import entities.Course;
import entities.StudentUser;
import entities.Task;
import entities.TaskMap;
import read_write.TaskReadWrite;

public class TaskDeletionInteractor implements TaskDeletionInputBoundary {
    final TaskDeletionPresenter presenter;
    public TaskDeletionInteractor(TaskDeletionPresenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public TaskDeletionResponseModel deleteStudentTask(TaskDeletionRequestModel requestModel) {
        requestModel.getStudent().removeFromToDoList(requestModel.getTask());
        requestModel.getStudent().addToArchive(requestModel.getTask());

        TaskReadWrite trw = new TaskReadWrite("src/data/TaskMap");
        TaskMap.saveToFile(trw);

        TaskDeletionResponseModel responseModel = new TaskDeletionResponseModel(requestModel.getTask().getTitle());
        return presenter.prepareSuccessView(responseModel);
    }
    public TaskDeletionResponseModel deleteCourseTask(TaskDeletionRequestModel requestModel) {
        requestModel.getCourse().removeTask(requestModel.getTask());
        TaskMap.removeTask(requestModel.getTask());

        TaskDeletionResponseModel responseModel = new TaskDeletionResponseModel(requestModel.getTask().getTitle());
        return presenter.prepareSuccessView(responseModel);
    }
}
