package use_cases.collaborative_task_scheduling.scheduling_ct_use_case;
import java.io.IOException;

public interface ScheduleCTGateway {

    void save(ScheduleCTDSID data) throws IOException;
}
