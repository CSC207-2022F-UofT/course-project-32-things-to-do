package use_cases.task_management.event_creation_use_case;

import entities.Event;
import entities.StudentUser;
import entities.TaskMap;
import use_cases.calendar_scheduler.schedule_conflict_use_case.ScheduleConflictPresenter;
import use_cases.calendar_scheduler.scheduler_use_case.SchedulerInteractor;
import use_cases.calendar_scheduler.scheduler_use_case.SchedulerPresenter;
import use_cases.calendar_scheduler.scheduler_use_case.SchedulerRequestModel;
import use_cases.task_management.read_write.TaskReadWrite;

import java.time.LocalDateTime;

public class EventCreationInteractor implements EventCreationInputBoundary {
    final EventCreationPresenter presenter;
    final StudentUser student;

    // For connecting to Scheduler use case
    final SchedulerInteractor scheduler;

    public EventCreationInteractor(EventCreationPresenter eventPresenter, StudentUser student,
                                   SchedulerPresenter schedulerPresenter, ScheduleConflictPresenter scheduleConflictPresenter) {
        this.presenter = eventPresenter;
        this.student = student;
        this.scheduler = new SchedulerInteractor(scheduleConflictPresenter, schedulerPresenter);
    }

    @Override
    public EventCreationResponseModel create(EventCreationRequestModel requestModel) {
        if (requestModel.getTitle().equals("") || requestModel.getStartTime() == null ||
                requestModel.getEndTime() == null || (requestModel.getRecurring() && requestModel.getFrequency().equals(""))) {
            return presenter.prepareFailView("Please fill in all required information.");
        }

        String id = LocalDateTime.now() + "_" + student.getName() + "_none";

        Event event = new Event(requestModel.getTitle(), id, requestModel.getPriority(),
                requestModel.getStartTime(), requestModel.getEndTime(), requestModel.getRecurring(),
                requestModel.getFrequency());

        EventCreationResponseModel eventResponseModel = new EventCreationResponseModel(
                requestModel.getTitle(), requestModel.getStartTime(), requestModel.getEndTime());

        // Schedule the newly created event
        SchedulerRequestModel scheduleRequestModel = new SchedulerRequestModel(event, student);
        scheduler.schedule(scheduleRequestModel);

        TaskReadWrite trw = new TaskReadWrite("src/data/TaskMap");
        TaskMap.saveToFile(trw);

        return presenter.prepareSuccessView(eventResponseModel);
    }
}
