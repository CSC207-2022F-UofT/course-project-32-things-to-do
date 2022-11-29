package event_creation_screens;

import task_creation_use_case.TaskCreationPresenter;
import task_creation_use_case.TaskCreationResponseModel;

public class EventCreationResponseFormatter implements TaskCreationPresenter {

    @Override
    public TaskCreationResponseModel prepareSuccessView(TaskCreationResponseModel e) {
        System.out.println("yessir");
        return e;
    }

    @Override
    public TaskCreationResponseModel prepareFailView(String error) {
        throw new EventCreationFailed(error);
    }
}
