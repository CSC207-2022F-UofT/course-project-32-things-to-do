package use_cases.task_management.todolist_use_case;

/**
 * Output Boundary for the ToDoList Use Case
 * Inverts the dependency (interactor to presenter)
 */

public interface ToDoListOutputBoundary {
    ToDoListResponseModel display(ToDoListResponseModel responseModel);

    ToDoListResponseModel failView(String error);
}
