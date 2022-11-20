package event_edit_use_case;

public interface EventEditPresenter {
    EventEditResponseModel prepareSuccessView(EventEditResponseModel requestModel);
    EventEditResponseModel prepareFailView(String error);
}
