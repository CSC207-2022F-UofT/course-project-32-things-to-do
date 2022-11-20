package screens;

import event_creation_use_case.EventCreationPresenter;
import event_creation_use_case.EventCreationResponseModel;

public class EventCreationResponseFormatter implements EventCreationPresenter {

    @Override
    public EventCreationResponseModel prepareSuccessView(EventCreationResponseModel e) {
        return e;
    }

    @Override
    public EventCreationResponseModel prepareFailView(String error) {
        throw new EventCreationFailed(error);
    }
}
