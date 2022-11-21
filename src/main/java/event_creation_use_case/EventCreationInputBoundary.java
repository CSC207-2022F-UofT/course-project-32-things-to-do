package event_creation_use_case;

public interface EventCreationInputBoundary {
    EventCreationResponseModel create(EventCreationRequestModel requestModel);
}
