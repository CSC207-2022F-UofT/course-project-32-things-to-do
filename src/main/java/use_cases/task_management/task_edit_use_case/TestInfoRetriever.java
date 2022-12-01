package use_cases.task_management.task_edit_use_case;

import entities.Test;

import java.time.LocalDateTime;

public class TestInfoRetriever implements TestDisplayer {
    private final Test test;
    /**
     * An object that retrieves the info of a Test to be displayed in edit screens
     * @param test - the Test whose info is being displayed
     */
    public TestInfoRetriever(Test test) {
        this.test = test;
    }
    @Override
    public String getTitle() {
        return test.getTitle();
    }

    @Override
    public String getId() {
        return test.getId();
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
