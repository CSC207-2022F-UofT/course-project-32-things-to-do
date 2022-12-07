package test_course_features.course_creation;

//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import screens.course_features.CourseCreationFailed;
//import screens.course_features.CourseCreationPresenter;
//import use_cases.course_features.course_creation_use_case.CourseCreationResponseModel;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * testing presenter fail and success views
// * currently failing oop
// */
//class CourseCreationPresenterTest {
//    private CourseCreationPresenter presenter;
//
//    @BeforeAll
//    static void setUp() {
//        CourseCreationPresenter presenter = new CourseCreationPresenter();
//
//        ArrayList<String> tasks = new ArrayList<>();
//        tasks.add("1");
//        tasks.add("2");
//        tasks.add("3");
//        presenter.prepareSuccessView(new CourseCreationResponseModel("id", tasks));
//    }
//
//    @Test
//    void prepareSuccessView() {
//        ArrayList<String> tasks = new ArrayList<>();
//        tasks.add("1");
//        tasks.add("2");
//        tasks.add("3");
//        CourseCreationResponseModel response = new CourseCreationResponseModel("id", tasks);
//        assertEquals(response, presenter.prepareSuccessView(response));
//    }
//
//    @Test
//    void prepareFailView() {
//        String error = "hm what should i put here";
//        Exception e = assertThrows(CourseCreationFailed.class, () -> presenter.prepareFailView(error));
//        assertEquals(error, e.getMessage());
//    }
//}
