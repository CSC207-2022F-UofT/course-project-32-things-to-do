package event_edit_use_case;

import java.util.ArrayList;

public class EventEditInteractor implements EventEditInputBoundary{
    private ArrayList<String> changes;
    EventEditPresenter presenter;

    public EventEditInteractor(EventEditPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public EventEditResponseModel edit(EventEditRequestModel requestModel) {
        requestModel.getEvent().setTitle(requestModel.getTitle());
        requestModel.getEvent().setPriority(requestModel.getPriority());
        requestModel.getEvent().setTimeBlock(requestModel.getStartTime(), requestModel.getEndTime());
        requestModel.getEvent().setRecurring(requestModel.getRecurring(), requestModel.getFrequency());

        EventEditResponseModel responseModel = new EventEditResponseModel("good job");
        return presenter.prepareSuccessView(responseModel);
    }
}
