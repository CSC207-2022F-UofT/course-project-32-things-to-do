package task_creation_use_case;

import entities.*;
import read_write.TaskReadWrite;

import java.time.LocalDateTime;

public class TaskCreationInteractor implements TaskCreationInputBoundary {
    private final TaskCreationPresenter presenter;
    private final StudentUser student;
    private final String courseId;

    public TaskCreationInteractor(TaskCreationPresenter presenter, StudentUser student, String courseId) {
        this.presenter = presenter;
        this.student = student;
        this.courseId = courseId;
    }

    /**
     * Create a Task
     * @param requestModel - request model for Task
     * @param type - type of Task (one of "Event", "Test", "Assignment")
     * @return - response model after Task is created
     */
    public TaskCreationResponseModel create(TaskCreationRequestModel requestModel, String type) {
        // check for blank title input
        if (requestModel.getTitle().equals("")) return presenter.prepareFailView("Please enter a title.");

        // generate unique Task ID
        String id = LocalDateTime.now() + "_" + student.getName() + "_" + courseId;

        // create the new Task
        if (type.equals("Assignment")) {
            Assignment assignment = new Assignment(requestModel.getTitle(), id, requestModel.getPriority(),
                    ((AssignmentCreationRequestModel)requestModel).getDueDate(),
                    ((AssignmentCreationRequestModel)requestModel).getWeightage());
            // save assignment to TaskMap
            TaskMap.addTask(id, assignment);
        } else if (type.equals("Event")) {
            Event event = new Event(requestModel.getTitle(), id, requestModel.getPriority(),
                    ((EventCreationRequestModel)requestModel).getStartTime(), ((EventCreationRequestModel)requestModel).getEndTime(),
                    ((EventCreationRequestModel)requestModel).getRecurring(),
                    ((EventCreationRequestModel)requestModel).getFrequency());
            // save Event to TaskMap
            System.out.println(id);
            System.out.println(event);
            TaskMap.addTask(id, event);
        } else {
            Test test = new Test(requestModel.getTitle(), id, requestModel.getPriority(),
                    ((TestCreationRequestModel)requestModel).getStartTime(),
                    ((TestCreationRequestModel)requestModel).getEndTime(),
                    ((TestCreationRequestModel)requestModel).getWeightage());
            // save Test to TaskMap
            TaskMap.addTask(id, test);
        }
        // save TaskMap.txt to file:
        TaskReadWrite trw = new TaskReadWrite("src/data/TaskMap.txt");
        TaskMap.saveToFile(trw);

        // display success to user
        TaskCreationResponseModel response = new TaskCreationResponseModel(requestModel.getTitle(), type);
        return presenter.prepareSuccessView(response);
    }
}
