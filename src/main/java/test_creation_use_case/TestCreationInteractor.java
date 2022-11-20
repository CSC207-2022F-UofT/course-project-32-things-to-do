package test_creation_use_case;

import entities.StudentUser;
import entities.TaskMap;
import entities.Test;
import read_write.TaskReadWrite;

import java.time.LocalDateTime;

public class TestCreationInteractor implements TestCreationInputBoundary {
    TestCreationPresenter presenter;
    StudentUser student;
    String courseId;

    public TestCreationInteractor(TestCreationPresenter presenter, StudentUser student, String courseId) {
        this.presenter = presenter;
        this.student = student;
        this.courseId = courseId;
    }
    @Override
    public TestCreationResponseModel create(TestCreationRequestModel requestModel) {
        if (requestModel.getTitle().equals("") || requestModel.getStartTime() == null ||
                requestModel.getEndTime() == null) {
            return presenter.prepareFailView("Please fill in all required information.");
        }

        String id = LocalDateTime.now() + "_" + student.getName() + "_" + courseId;

        Test test = new Test(requestModel.getTitle(), id, requestModel.getPriority(), requestModel.getStartTime(),
                requestModel.getEndTime(), requestModel.getWeightage());

        TaskReadWrite trw = new TaskReadWrite("src/data/TaskMap");
        TaskMap.saveToFile(trw);

        TestCreationResponseModel response = new TestCreationResponseModel(requestModel.getTitle());
        return presenter.prepareSuccessView(response);
    }
}
