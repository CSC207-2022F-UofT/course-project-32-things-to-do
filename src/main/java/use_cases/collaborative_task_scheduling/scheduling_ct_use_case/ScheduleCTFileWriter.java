package use_cases.collaborative_task_scheduling.scheduling_ct_use_case;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ScheduleCTFileWriter implements ScheduleCTGateway{

    private final String filename;

    public ScheduleCTFileWriter(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(ScheduleCTDSID data) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(this.filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(data.getUpdatedTaskMapDSID());
        out.close();
        fileOut.close();
    }

}
