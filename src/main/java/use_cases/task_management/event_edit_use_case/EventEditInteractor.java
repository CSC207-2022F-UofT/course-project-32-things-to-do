package use_cases.task_management.event_edit_use_case;

public class EventEditInteractor implements EventEditInputBoundary{
    EventEditPresenter presenter;

    public EventEditInteractor(EventEditPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public EventEditResponseModel edit(EventEditRequestModel requestModel) {
        if (requestModel.getTitle().equals("") || requestModel.getStartTime() == null || requestModel.getEndTime() == null) {
            return presenter.prepareFailView("Please fill in all required information.");
        }
        requestModel.getEvent().setTitle(requestModel.getTitle());
        requestModel.getEvent().setPriority(requestModel.getPriority());
        requestModel.getEvent().setTimeBlock(requestModel.getStartTime(), requestModel.getEndTime());
        requestModel.getEvent().setRecurring(requestModel.getRecurring(), requestModel.getFrequency());

        EventEditResponseModel responseModel = new EventEditResponseModel(requestModel.getTitle());
        return presenter.prepareSuccessView(responseModel);
    }
}
