package use_cases.task_management.task_creation_use_case;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskCreationInteractorTest {
    static AssignmentCreationRequestModel aRequest =(AssignmentCreationRequestModel) AssignmentCreationRequestModelTest.assignmentCreationRequestModel;
    static EventCreationRequestModel eRequest = (EventCreationRequestModel) EventCreationRequestModelTest.eventCreationRequestModel;
    static TestCreationRequestModel tRequest = (TestCreationRequestModel) TestCreationRequestModelTest.testCreationRequestModel;

    // This creates an anonymous implementing class for the Output Boundary.
    TaskCreationOutputBoundary outputBoundary = new TaskCreationOutputBoundary() {
        @Override
        public TaskCreationResponseModel prepareSuccessView(TaskCreationResponseModel response) {
            // 4) Check that the Output Data and associated changes
            return null;
        }

        @Override
        public TaskCreationResponseModel prepareFailView(String error) {
            fail("Use case failure is unexpected.");
            return null;
        }
    };
    //TaskCreationInteractor interactor = new TaskCreationInteractor()

    @Test
    void createCorrectInput() {
    }
}