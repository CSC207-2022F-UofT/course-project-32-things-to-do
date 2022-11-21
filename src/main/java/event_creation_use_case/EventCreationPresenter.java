package event_creation_use_case;

public interface EventCreationPresenter {
    EventCreationResponseModel prepareSuccessView(EventCreationResponseModel e);
    EventCreationResponseModel prepareFailView(String error);
}