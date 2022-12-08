package screens.task_management.task_edit_delete_screens.test_edit_delete_screens;

import entities.*;
import use_cases.task_management.task_edit_use_case.*;

import java.time.LocalDateTime;

public class TestInfoRetriever implements TestDisplayer {
    private final Test test;
    private final String id;
    /**
     * An object that retrieves the info of a Test to be displayed in edit screens
     * @param id - ID of Test whose info is being displayed
     */
    public TestInfoRetriever(String id) {
        this.test = (Test) TaskMap.findTask(id);
        this.id = id;
    }
    @Override
    public String getTitle() {
        return test.getTitle();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getPriority() {
        return test.getPriority();
    }

    @Override
    public LocalDateTime getStartTime() {
        return test.getTimeBlock()[0];
    }

    @Override
    public LocalDateTime getEndTime() {
        return test.getTimeBlock()[1];
    }

    @Override
    public double getWeightage() {
        return test.getWeightage();
    }

    @Override
    public double getTimeNeeded() {
        return test.getTimeNeeded();
    }

    @Override
    public double getTimeSpent() {
        return test.getTimeSpent();
    }
}
