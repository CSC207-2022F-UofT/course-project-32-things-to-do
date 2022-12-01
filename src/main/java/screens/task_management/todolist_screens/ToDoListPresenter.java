package screens.task_management.todolist_screens;

import entities.StudentUser;
import use_cases.task_management.todolist_use_case.TaskType;
import use_cases.task_management.todolist_use_case.*;

import java.util.ArrayList;

public class ToDoListPresenter  implements ToDoListOutputBoundary {

    private ToDoListInputBoundary toDoListInput;

    //TODO private final StudentSaveRequest studentSaveRequest;
    private final StudentUser studentUser;

    public ToDoListPresenter(StudentUser studentUser) {
        this.studentUser = studentUser;
    }

    public void setToDoListInput(ToDoListInputBoundary toDoListInput) {
        this.toDoListInput = toDoListInput;
    }

    //TODO need UserSaveRequest from Natalie!!
    // ProgressTrackerResponseModel getToDoList(UserSaveRequest userSaveRequest) {}

    public ToDoListResponseModel getToDoList() {
        ToDoListRequestModel requestModel = new ToDoListRequestModel(studentUser);

        return toDoListInput.getToDoList(requestModel);
    }

    @Override
    public ToDoListResponseModel display(ToDoListResponseModel responseModel) {
        ArrayList<ArrayList<String>> toDoListView = new ArrayList<>();

        for (ToDoListItem item: responseModel.getToDoList()) {
            String itemType;
            if (item.getTaskType().equals(TaskType.ASSIGNMENT)) {
                itemType = "Assignment";
            } else if (item.getTaskType().equals(TaskType.TEST)) {
                itemType = "Test";
            } else {
                itemType = "Event";
            }
            ArrayList<String> itemView = new ArrayList<>();
            itemView.add(item.getTaskTitle());
            itemView.add(item.getTaskId());
            itemView.add(itemType);

            toDoListView.add(itemView);
        }

        responseModel.setToDoListView(toDoListView);

        return responseModel;

    }

}
