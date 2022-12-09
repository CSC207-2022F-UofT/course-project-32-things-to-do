package use_cases.collaborative_task_management.collaborative_task_edit_use_case;

import entities.CollaborativeTask;
import entities.CurrentUser;
import entities.StudentUser;
import entities.TaskMap;
import use_cases.login_registration.user_register_usecase.StudentSaveRequest;
import use_cases.login_registration.user_register_usecase.UserRegSaveRequest;
import use_cases.task_management.read_write.TaskMapGateway;

import java.util.HashMap;

/**
 * Collaborative Task Edit Use Case Interactor (use case layer)
 * Implements business logic on entities
 */

public class CollaborativeTaskEditInteractor implements CollaborativeTaskEditInputBoundary {
    private final CollaborativeTaskEditPresenter presenter;
    private final TaskMapGateway taskMapGateway;
    private final HashMap<String, UserRegSaveRequest> accounts;
    private final StudentUser student = (StudentUser) CurrentUser.getCurrentUser();

    /**
     * Interactor for Collaborative Task Editing.
     * @param collaborativeTaskPresenter - displays success/fail views
     * @param taskMapGateway - the gateway that interacts with the Task Map Database.
     * @param accounts - a transient data storage class that contains the same information as a User
     */
    public CollaborativeTaskEditInteractor(CollaborativeTaskEditPresenter collaborativeTaskPresenter, TaskMapGateway taskMapGateway, HashMap<String, UserRegSaveRequest> accounts) {
        this.presenter = collaborativeTaskPresenter;
        this.taskMapGateway = taskMapGateway;
        this.accounts = accounts;
    }

    @Override
    public CollaborativeTaskEditResponseModel edit(CollaborativeTaskEditRequestModel requestModel) {
        CollaborativeTask collaborativeTask = (CollaborativeTask) TaskMap.findTask(requestModel.getId());
        StudentUser leader = ((StudentSaveRequest)accounts.get(requestModel.getLeader())).initializeUser();
        if (!(student.getName().equals(collaborativeTask.getLeader().getName()))) return presenter.prepareFailView("Only the leader of the collaborative task can edit it.");
        if (requestModel.getStartTime() == null) return presenter.prepareFailView("Please enter a start time.");
        if (requestModel.getEndTime() == null) return presenter.prepareFailView("Please enter an end time.");
        if (requestModel.getStartTime().isAfter(requestModel.getEndTime())) return presenter.prepareFailView("Please enter a valid time block.");
        if (requestModel.getEndTime().isAfter(requestModel.getDeadline())) return presenter.prepareFailView("Please enter a valid deadline.");
        if (requestModel.getDeadline() == null) return presenter.prepareFailView("Please enter a deadline.");
        if (requestModel.getRecurring() && requestModel.getFrequency().equals("")) return presenter.prepareFailView("Please enter a frequency.");
        if (requestModel.getRecurring() && !(requestModel.getFrequency().equals("daily") || requestModel.getFrequency().equals("monthly") || requestModel.getFrequency().equals("weekly"))) return presenter.prepareFailView("Please enter a valid frequency (\"daily\", \"weekly\", \"monthly\" accepted.)");
        if (!(collaborativeTask.getTeammates().contains(leader)))  return presenter.prepareFailView("Please enter valid leader.");

        collaborativeTask.setPriority(requestModel.getPriority());
        collaborativeTask.setTimeBlock(requestModel.getStartTime(), requestModel.getEndTime());
        collaborativeTask.setDeadline(requestModel.getDeadline());
        collaborativeTask.setRecurringAndFrequency(requestModel.getRecurring(), requestModel.getFrequency());
        collaborativeTask.setLeader(leader);

        // save task to task map and add task to student's todolist
        TaskMap.removeTask(requestModel.getId());
        TaskMap.addTask(requestModel.getId(), collaborativeTask); // NEED TO MAKE SURE THIS WORKS!!
        for (StudentUser s : collaborativeTask.getTeammates()){
            s.removeTaskFromList(requestModel.getId());
            s.addTaskToList(requestModel.getId());
        }

        if (requestModel.getComplete()) {
            TaskMap.findTask(requestModel.getId()).setComplete();
            for (StudentUser s : collaborativeTask.getTeammates()) {
                s.removeTaskFromList(requestModel.getId());
                s.addTaskToArchive(requestModel.getId());
            }
        }

        // save changes
        taskMapGateway.save(TaskMap.getTaskMap());

        CollaborativeTaskEditResponseModel response = new CollaborativeTaskEditResponseModel(collaborativeTask.getTitle(), requestModel.getId());
        return presenter.prepareSuccessView(response);
    }
}