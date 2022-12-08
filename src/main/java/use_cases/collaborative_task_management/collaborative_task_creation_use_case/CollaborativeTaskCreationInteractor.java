package use_cases.collaborative_task_management.collaborative_task_creation_use_case;

import entities.*;
import screens.task_management.FileTaskMap;

import java.io.IOException;
import java.time.LocalDateTime;

public class CollaborativeTaskCreationInteractor implements CollaborativeTaskCreationInputBoundary {
    private final CollaborativeTaskCreationPresenter presenter;
    private final StudentUser student;

    public CollaborativeTaskCreationInteractor(CollaborativeTaskCreationPresenter collaborativeTaskPresenter, StudentUser student) {
        this.presenter = collaborativeTaskPresenter;
        this.student = student;
    }

    @Override
    public CollaborativeTaskCreationResponseModel create(CollaborativeTaskCreationRequestModel requestModel) throws IOException {
        if (requestModel.getTitle().equals("") || requestModel.getStartTime() == null || requestModel.getEndTime() == null || requestModel.getDeadline() == null || (requestModel.getRecurring() && requestModel.getFrequency().equals(""))) {
            return presenter.prepareFailView("Please fill in all required information.");
        }

        String id = LocalDateTime.now() + "_" + student.getName() + "_none";

        CollaborativeTask collaborativeTask = new CollaborativeTask(requestModel.getTitle(), id, requestModel.getPriority(), requestModel.getRecurring(), requestModel.getFrequency(), requestModel.getStartTime(), requestModel.getEndTime(), requestModel.getDeadline(), student);

        FileTaskMap trw = new FileTaskMap("src/main/java/data/TaskMap");
        trw.save(TaskMap.getTaskMap());

        CollaborativeTaskCreationResponseModel collaborativeTaskResponseModel = new CollaborativeTaskCreationResponseModel(requestModel.getTitle(), requestModel.getStartTime(), requestModel.getEndTime(), requestModel.getDeadline(), requestModel.getLeader());
        return presenter.prepareSuccessView(collaborativeTaskResponseModel);
    }
}