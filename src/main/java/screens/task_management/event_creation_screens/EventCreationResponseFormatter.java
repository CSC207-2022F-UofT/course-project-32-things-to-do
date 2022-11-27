package screens.task_management.event_creation_screens;

import use_cases.task_management.event_creation_use_case.EventCreationPresenter;
import use_cases.task_management.event_creation_use_case.EventCreationResponseModel;

public class EventCreationResponseFormatter implements EventCreationPresenter {

    @Override
    public EventCreationResponseModel prepareSuccessView(EventCreationResponseModel e) {
        System.out.println("yessir");
        return e;
    }

    @Override
    public EventCreationResponseModel prepareFailView(String error) {
        throw new EventCreationFailed(error);
    }
}
