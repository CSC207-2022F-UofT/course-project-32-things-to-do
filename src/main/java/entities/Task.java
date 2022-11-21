package entities;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private String title;
    private int priority;
    private boolean complete = false;
    private String id;

    /**
     * Create a new Task that has a title and default priority value
     * @param title - the title of the Task
     * @param id - the unique ID of the Task
     */
    public Task(String title, String id) {
        this.title = title;
        this.id = id;
        this.priority = 0;

        save(); // save to task map
    }

    /**
     * Create a new Task that has a title and a priority value
     * @param title - the title of the Task
     * @param id - the unique ID of the Task
     * @param priority - the Task's priority value
     */
    public Task(String title, String id, int priority) {
        this.title = title;
        this.id = id;
        this.priority = priority;

        save(); // save to task map
    }

    /**
     * Retrieve the unique ID of the Task
     * @return - the ID of the Task
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set a new Task ID (used for adding course code)
     * @param id - the new ID
     */
    public void setId(String id) {
        this.id = id;
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
    public void setTitle(String title) {
        this.title = title;
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
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Get whether the Task is complete
     * @return - whether the Task is complete
     */
    public boolean getComplete() {
        return this.complete;
    }

    /**
     * Sets the Task to complete
     */
    public void setComplete() {
        this.complete = true;
    }

    /**
     * Save the Task to the taskMap
     */
    public void save() {
        TaskMap.addTask(this.id, this);
    }
}
