package use_cases.calendar_scheduler.working_hours_use_case;

import use_cases.course_tracker.grade_calculator_use_case.GradeCalculatorRequestModel;

public interface WorkingHoursInputBoundary {

    void setWorkingHours(WorkingHoursRequestModel requestModel);
}
