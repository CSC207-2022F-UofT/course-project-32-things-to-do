package screens.task_management.event_creation_screens;

import use_cases.task_management.task_creation_use_case.TaskCreationPresenter;
import use_cases.task_management.task_creation_use_case.TaskCreationResponseModel;

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
