package assignment_creation_use_case;

import entities.Assignment;
import entities.StudentUser;
import entities.TaskMap;
import read_write.TaskReadWrite;

import java.time.LocalDateTime;

public class AssignmentCreationInteractor implements AssignmentCreationInputBoundary {
    AssignmentCreationPresenter presenter;
    StudentUser student;
    String courseId;
    public AssignmentCreationInteractor(AssignmentCreationPresenter presenter, StudentUser student, String courseId) {
        this.presenter = presenter;
        this.student = student;
        this.courseId = courseId;
    }
    @Override
    public AssignmentCreationResponseModel create(AssignmentCreationRequestModel requestModel) {
        if (requestModel.getTitle().equals("") || requestModel.getDueDate() == null) {
            return presenter.prepareFailView("Please fill in all required information.");
        }

        String id = LocalDateTime.now() + "_" + student.getName() + "_" + courseId;
        Assignment assignment = new Assignment(requestModel.getTitle(), id, requestModel.getPriority(),
                requestModel.getDueDate(), requestModel.getWeightage());

        TaskReadWrite trw = new TaskReadWrite("src/Data/TaskMap");
        TaskMap.saveToFile(trw);

        AssignmentCreationResponseModel response = new AssignmentCreationResponseModel(requestModel.getTitle(),
                requestModel.getDueDate());

        return presenter.prepareSuccessView(response);
    }
}
