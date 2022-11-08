package Entities;

public abstract class Task {
    private String title;
    private int priority;
    private boolean complete = false;
    private String id = "user name" + 0; // concatenate user's name with length of their toDoList

    /**
     * Create a new Entities.Task that has a title and default priority value
     * @param title - the title of the Entities.Task
     */
    public Task(String title) {
        this.title = title;
        this.priority = 0;
    }

    /**
     * Create a new Entities.Task that has a title and a priority value
     * @param title - the title of the Entities.Task
     * @param priority - the Entities.Task's priority value
     */
    public Task(String title, int priority) {
        this.title = title;
        this.priority = priority;
    }

    /**
     * Retrieve the unique ID of the Entities.Task
     * @return - the ID of the Entities.Task
     */
    public String getId() {
        return id;
    }
    /**
     * Retrieves the title of the Entities.Task
     * @return - the title of the Entities.Task
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Change the title of the task
     * @param title - the new title of the task
     */
    protected void setTitle(String title) {

    }

    /**
     * Retrieve the priority value of the Entities.Task
     * @return - the priority value of the Entities.Task
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Change the priority value of the Entities.Task
     * @param priority - the new priority value of the Entities.Task
     */
    protected void setPriority(int priority) {

    }

    /**
     * Sets the Entities.Task to complete
     */
    protected void setComplete() {
        this.complete = true;
    }

    /**
     * Deletes the Entities.Task by moving it to the user's archive
     * @return - whether the Entities.Task has been successfully deleted
     */
    abstract boolean delete();

    /**
     * Save the Entities.Task to the user's data
     * @return - whether the Entities.Task has been successfully saved
     */
    abstract boolean save();

    /**
     * Edit the features of the Entities.Task
     */
    abstract void edit();
}
