//import Entities.CourseFactory;
import Entities.CourseMap;
import course_creation_use_case.CourseCreationDsGateway;
import course_creation_use_case.CourseCreationInputBoundary;
import course_creation_use_case.CourseCreationInteractor;
import course_creation_use_case.CourseCreationPresenter;
import screens.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

//    public static void main(String[] args) {
//
//        /** build main program window */
//        JFrame application = new JFrame("Course Enrolment Example");
//        CardLayout cardLayout = new CardLayout();
//        JPanel screens = new JPanel(cardLayout);
//        application.add(screens);
//
//        /** create parts to plug into the use case + entities engine */
//        courseEnrolmentDsGateway course = new Course(CourseMap.addCourse());
////        try {
////            course = new FileCourse("./courses.csv");
////        } catch (IOException e) {
////            throw new RuntimeException("Could not create file.");
////        }
//        courseEnrolmentPresenter presenter = new CourseEnrolmentResponseFormatter();
//        courseEnrolmentInputBoundary interactor = new courseEnrolmentInteractor(
//                course, presenter);
//        CourseEnrolmentController courseEnrolmentController = new CourseEnrolmentController(
//                interactor
//        );
//
//        // COURSE ENROLMENT build the GUI, plugging in the parts
//        CourseEnrolmentScreen courseEnrolmentScreen = new CourseEnrolmentScreen(courseEnrolmentController);
//        screens.add(courseEnrolmentScreen, "enter course name");
//        cardLayout.show(screens, "enrol in course");
//        application.pack();
//        application.setVisible(true);
//
//    }

//    /** course creation screen
    public static void main(String[] args) {

        // build main program window
        JFrame application = new JFrame("Course Creation Example");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        // create parts to plug into the use case + entities engine
        CourseCreationDsGateway course;
        try {
            course = new FileCourse("./courses.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        CourseCreationPresenter presenter = new CourseCreationResponseFormatter();
        CourseMap courseMap = new CourseMap();

//        CourseFactory courseFactory = new CourseFactory();

        CourseCreationInputBoundary interactor = new CourseCreationInteractor(
                course, presenter, courseMap);
        CourseCreationController courseCreationController = new CourseCreationController(
                interactor
        );

        // COURSE CREATION build the GUI, plugging in the parts
        CreateCourseScreen createCourseScreen = new CreateCourseScreen(courseCreationController);
        screens.add(createCourseScreen, "create a new course");
        cardLayout.show(screens, "create course");
        application.pack();
        application.setVisible(true);

    }
}
