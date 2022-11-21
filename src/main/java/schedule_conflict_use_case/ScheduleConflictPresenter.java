package schedule_conflict_use_case;

public interface ScheduleConflictPresenter {

    /**
     * Alert the user to a scheduling conflict
     * @param requestModel - the given input
     */
    ScheduleConflictResponseModel alertConflict(ScheduleConflictRequestModel requestModel);
}
