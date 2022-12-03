package use_cases.task_management.task_creation_use_case;

import entities.Event;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskCreationInteractorTest {
    static AssignmentCreationRequestModel aRequest;
    static EventCreationRequestModel eRequest;
    static TestCreationRequestModel tRequest;

    @BeforeAll
    static void beforeAll() {
        aRequest =(AssignmentCreationRequestModel) AssignmentCreationRequestModelTest.assignmentCreationRequestModel;
        eRequest = (EventCreationRequestModel) EventCreationRequestModelTest.eventCreationRequestModel;
        tRequest = (TestCreationRequestModel) TestCreationRequestModelTest.testCreationRequestModel;
    }

    @Test
    void createCorrectInput() {
    }
}