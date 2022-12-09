//package test_collaborative_task_management;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import screens.collaborative_task_management.collaborative_task_creation_screens.CollaborativeTaskCreationResponseFormatter;
//import screens.task_management.FileTaskMap;
//import use_cases.collaborative_task_management.collaborative_task_creation_use_case.CollaborativeTaskCreationInteractor;
//import use_cases.collaborative_task_management.collaborative_task_creation_use_case.CollaborativeTaskCreationRequestModel;
//import use_cases.collaborative_task_management.collaborative_task_creation_use_case.CollaborativeTaskCreationResponseModel;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CollaborativeTaskCreationInteractorTest {
//
//    static CollaborativeTaskCreationInteractor interactor;
//
//    @BeforeAll
//    static void beforeAll() {
//        // initialize new interactor
//        FileTaskMap taskMapGateway = new FileTaskMap("src/main/java/data/TaskMap.txt");
//        CollaborativeTaskCreationResponseFormatter outputBoundary = new CollaborativeTaskCreationResponseFormatter();
//        interactor = new CollaborativeTaskCreationInteractor(taskMapGateway, outputBoundary);
//    }
//
////    @Test
////    void create() {
////        LocalDateTime startTime = LocalDateTime.of(2022, 12, 2, 4, 38);
////        LocalDateTime endTime = LocalDateTime.of(2022, 12, 4, 4, 38);
////        LocalDateTime deadline = LocalDateTime.of(2022, 12, 7, 4, 38);
////        CollaborativeTaskCreationRequestModel requestModel = new CollaborativeTaskCreationRequestModel("requestModel", 0, false, "", startTime, endTime, deadline);
////        CollaborativeTaskCreationResponseFormatter outputBoundary = new CollaborativeTaskCreationResponseFormatter();
////        CollaborativeTaskCreationResponseModel response = new CollaborativeTaskCreationResponseModel("requestModel", "requestModelID");
////        assertEquals(interactor.create(requestModel), outputBoundary.prepareSuccessView(response));
////    }
//}