package scheduling_ct_use_case;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import use_cases.collaborative_task_scheduling.scheduling_ct_use_case.ScheduleCTResponseModel;

import java.util.ArrayList;

public class ScheduleCTResponseModelTest {
    static ScheduleCTResponseModel scheduleCTResponseModel;

    @BeforeAll
    static void beforeAll() {
        ArrayList<String> scheduledTimes = new ArrayList<>();
        scheduledTimes.add("2022-12-07 10:00 to 2022-12-07 11:00");
        scheduledTimes.add("2022-12-08 10:00 to 2022-12-08 11:00");
        scheduledTimes.add("2022-12-09 10:00 to 2022-12-09 11:00");

        scheduleCTResponseModel = new ScheduleCTResponseModel(scheduledTimes);
    }

    @Test
    void getScheduledTimes() {
        ArrayList<String> scheduledTimes = new ArrayList<>();
        scheduledTimes.add("2022-12-07 10:00 to 2022-12-07 11:00");
        scheduledTimes.add("2022-12-08 10:00 to 2022-12-08 11:00");
        scheduledTimes.add("2022-12-09 10:00 to 2022-12-09 11:00");
        Assertions.assertEquals(scheduledTimes, scheduleCTResponseModel.getScheduledTimes());
    }
}
