package screens.calendar_scheduler;

import entities.Task;
import use_cases.calendar_scheduler.scheduler_use_case.SchedulerPresenter;
import use_cases.calendar_scheduler.scheduler_use_case.SchedulerResponseModel;

import javax.swing.*;

public class SchedulerResponseFormatter implements SchedulerPresenter {

    /**
     * Alert the user to the task scheduling failure
     * @param responseModel - the given output
     */
    @Override
    public SchedulerResponseModel prepareFailView(SchedulerResponseModel responseModel) {
        JOptionPane.showMessageDialog(null, "Task creation failed!");
        return responseModel;
    }

    /**
     * Alert the user to the task scheduling success
     * @param responseModel - the given output
     */
    @Override
    public SchedulerResponseModel prepareSuccessView(SchedulerResponseModel responseModel) {
        Task task = responseModel.getTask();
        JOptionPane.showMessageDialog(null, task.getTitle() + " created!");

        return responseModel;
    }
}
