package use_cases.collaborative_task_management.collaborative_task_creation_use_case;

import entities.*;
import use_cases.task_management.read_write.TaskMapGateway;

/**
 * Collaborative Task Creation Use Case Interactor (use case layer)
 * Implements business logic on entities
 */

public class CollaborativeTaskCreationInteractor implements CollaborativeTaskCreationInputBoundary {
    private final TaskMapGateway taskMapRepository;
    private final CollaborativeTaskCreationOutputBoundary outputBoundary;

    /**
     * Interactor for Collaborative Task.
     * @param taskMapRepository- for saving the task to a file.
     * @param outputBoundary - the output boundary for displaying results.
     */
    public CollaborativeTaskCreationInteractor(TaskMapGateway taskMapRepository, CollaborativeTaskCreationOutputBoundary outputBoundary) {
        this.taskMapRepository = taskMapRepository;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Create a Collaborative Task
     * @param requestModel - request model for Collaborative Task
     * @return - response model after Collaborative Task is created
     */
    public CollaborativeTaskCreationResponseModel create(CollaborativeTaskCreationRequestModel requestModel) {
        StudentUser student = (StudentUser) CurrentUser.getCurrentUser();
        if(requestModel.getTitle() == null || requestModel.getStartTime() == null || requestModel.getEndTime() == null) return outputBoundary.prepareFailView("Please fill all fields!");
        if (requestModel.getTitle().equals("")) return outputBoundary.prepareFailView("Please enter a title.");
        String id = requestModel.getTitle() + "_" + student.getName() + "_collaborative";
        if (TaskMap.findTask(id) != null) return outputBoundary.prepareFailView("Please enter a unique title.");
        if (requestModel.getStartTime() == null) return outputBoundary.prepareFailView("Please enter a start time.");
        if (requestModel.getEndTime() == null) return outputBoundary.prepareFailView("Please enter an end time.");
        if (requestModel.getStartTime().isAfter(requestModel.getEndTime()))
            return outputBoundary.prepareFailView("Please enter a valid time block.");
        if (requestModel.getEndTime().isAfter(requestModel.getDeadline()))
            return outputBoundary.prepareFailView("Please enter a valid deadline.");
        if (requestModel.getDeadline() == null) return outputBoundary.prepareFailView("Please enter a deadline.");
        if (requestModel.getRecurring() && requestModel.getFrequency().equals(""))
            return outputBoundary.prepareFailView("Please enter a frequency.");
        if (requestModel.getRecurring() && !(requestModel.getFrequency().equals("daily") || requestModel.getFrequency().equals("monthly") || requestModel.getFrequency().equals("weekly")))
            return outputBoundary.prepareFailView("Please enter a valid frequency (\"daily\", \"weekly\", \"monthly\" accepted.)");

        CollaborativeTask newCollaborativeTask = new CollaborativeTask(requestModel.getTitle(), id, requestModel.getPriority(), requestModel.getRecurring(), requestModel.getFrequency(), requestModel.getStartTime(), requestModel.getEndTime(), requestModel.getDeadline(), student);

        // save task to task map and add task to student's todolist
        TaskMap.addTask(id, newCollaborativeTask); // NEED TO MAKE SURE THIS WORKS!!
        student.addTaskToList(id);

        // save TaskMap to file:
        taskMapRepository.save(TaskMap.getTaskMap());

        // display success to user
        CollaborativeTaskCreationResponseModel response = new CollaborativeTaskCreationResponseModel(requestModel.getTitle(), id);
        return outputBoundary.prepareSuccessView(response);
    }
}