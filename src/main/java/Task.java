/**
 *
 */
public abstract class Task {
    protected String title;
    protected int priority;
    protected boolean complete = false;

    /**
     * Create a new Task that has a title and default priority value
     * @param title - the title of the Task
     */
    public Task(String title) {
        this.title = title;
        this.priority = 0;
    }

    /**
     * Create a new Task that has a title and a priority value
     * @param title - the title of the Task
     * @param priority - the Task's priority value
     */
    public Task(String title, int priority) {
        this.title = title;
        this.priority = priority;
    }

    /**
     * Retrieves the title of the Task
     * @return - the title of the Task
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
     * Retrieve the priority value of the Task
     * @return - the priority value of the Task
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Change the priority value of the Task
     * @param priority - the new priority value of the Task
     */
    protected void setPriority(int priority) {

    }

    /**
     * Sets the Task to complete
     */
    protected void setComplete() {
        this.complete = true;
    }

    /**
     * Deletes the Task by moving it to the user's archive
     * @return - whether the Task has been successfully deleted
     */
    abstract boolean delete();

    /**
     * Save the Task to the user's data
     * @return - whether the Task has been successfully saved
     */
    abstract boolean save();

    /**
     * Edit the features of the Task
     */
    abstract void edit();
}
