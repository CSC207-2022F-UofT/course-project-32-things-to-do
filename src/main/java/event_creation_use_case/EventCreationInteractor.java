package event_creation_use_case;

import entities.Event;
import entities.StudentUser;
import entities.TaskMap;
import read_write.*;

import java.time.LocalDateTime;

public class EventCreationInteractor implements EventCreationInputBoundary {
    final EventCreationPresenter presenter;
    final StudentUser student;
    final String courseCode;

    public EventCreationInteractor(EventCreationPresenter eventPresenter, StudentUser student, String courseCode) {
        this.presenter = eventPresenter;
        this.student = student;
        this.courseCode = courseCode;
    }

    @Override
    public EventCreationResponseModel create(EventCreationRequestModel requestModel) {
        if (requestModel.getTitle().equals("") || requestModel.getStartTime() == null ||
                requestModel.getEndTime() == null || (requestModel.getRecurring() && requestModel.getFrequency().equals(""))) {
            return presenter.prepareFailView("Please fill in all required information.");
        }

        // check if id exists for students lol
        String id = LocalDateTime.now() + "_" + student.getName() + "_" + courseCode;

        Event event = new Event(requestModel.getTitle(), id, requestModel.getPriority(),
                requestModel.getStartTime(), requestModel.getEndTime(), requestModel.getRecurring(),
                requestModel.getFrequency());


        TaskReadWrite trw = new TaskReadWrite();
        TaskMap.saveToFile(trw);

        EventCreationResponseModel eventResponseModel = new EventCreationResponseModel(
                requestModel.getTitle(), requestModel.getStartTime(), requestModel.getEndTime());
        return presenter.prepareSuccessView(eventResponseModel);
    }
}
