package screens;

import entities.Task;
import schedule_conflict_use_case.ScheduleConflictPresenter;
import schedule_conflict_use_case.ScheduleConflictRequestModel;
import schedule_conflict_use_case.ScheduleConflictResponseModel;

import javax.swing.*;

public class ScheduleConflictResponseFormatter implements ScheduleConflictPresenter {

    /**
     * Alert the user to a scheduling conflict
     * @param requestModel - the given input
     */
    @Override
    public ScheduleConflictResponseModel alertConflict(ScheduleConflictRequestModel requestModel) {

        Task conflictingTask = requestModel.getConflictingTask();

        // Display dialog alerting user to scheduling conflict
        var scheduleChoice = JOptionPane.showConfirmDialog(null,
                "Time conflict with " + conflictingTask.getTitle() + "! Schedule anyways?",
                null, JOptionPane.YES_NO_OPTION);

        // Return user's choice on proceeding with scheduling conflict
        boolean userChoice = scheduleChoice == JOptionPane.YES_OPTION;

        return new ScheduleConflictResponseModel(userChoice);
    }
}
