package event_creation_use_case;

import entities.Event;
import entities.StudentUser;
import entities.TaskMap;
import read_write.*;

import java.time.LocalDateTime;

public class EventCreationInteractor implements EventCreationInputBoundary {
    final EventCreationPresenter presenter;
    final StudentUser student;

    public EventCreationInteractor(EventCreationPresenter eventPresenter, StudentUser student) {
        this.presenter = eventPresenter;
        this.student = student;
    }

    @Override
    public EventCreationResponseModel create(EventCreationRequestModel requestModel) {
        if (requestModel.getTitle().equals("") || requestModel.getStartTime() == null ||
                requestModel.getEndTime() == null || (requestModel.getRecurring() && requestModel.getFrequency().equals(""))) {
            return presenter.prepareFailView("Please fill in all required information.");
        }
        // generate task id
        String id = LocalDateTime.now() + "_" + student.getName() + "_none";

        Event event = new Event(requestModel.getTitle(), id, requestModel.getPriority(),
                requestModel.getStartTime(), requestModel.getEndTime(), requestModel.getRecurring(),
                requestModel.getFrequency());

        TaskReadWrite trw = new TaskReadWrite("src/data/TaskMap");
        TaskMap.saveToFile(trw);

        EventCreationResponseModel eventResponseModel = new EventCreationResponseModel(
                requestModel.getTitle(), requestModel.getStartTime(), requestModel.getEndTime());
        return presenter.prepareSuccessView(eventResponseModel);
    }
}
