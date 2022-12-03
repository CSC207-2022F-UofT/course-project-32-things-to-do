package screens.task_management.todolist_screens;

import screens.course_progress.ProgressTrackingFail;

public class ToDoListFail extends RuntimeException{

    public ToDoListFail(String error) { super(error); }

}
