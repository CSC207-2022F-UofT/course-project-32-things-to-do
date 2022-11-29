package use_cases.task_management.event_creation_use_case;


import java.time.LocalDateTime;

public class EventCreationResponseModel {
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public EventCreationResponseModel(String title, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
