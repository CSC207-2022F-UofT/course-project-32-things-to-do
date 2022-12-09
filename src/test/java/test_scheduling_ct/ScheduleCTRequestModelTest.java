package test_scheduling_ct;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import use_cases.collaborative_task_scheduling.scheduling_ct_use_case.*;

public class ScheduleCTRequestModelTest {
    static ScheduleCTRequestModel scheduleCTRequestModel;

    @BeforeAll
    static void beforeAll() {
        scheduleCTRequestModel = new ScheduleCTRequestModel("taskName", "2022-12-07 10:00",
                "2022-12-07 11:00");
    }

    @Test
    void getTaskName() {
        Assertions.assertEquals("taskName", scheduleCTRequestModel.getTaskName());
    }

    @Test
    void getStart() {
        Assertions.assertEquals("2022-12-07 10:00", scheduleCTRequestModel.getStartTime());
    }

    @Test
    void getEnd() {
        Assertions.assertEquals("2022-12-07 11:00", scheduleCTRequestModel.getEndTime());
    }
}
