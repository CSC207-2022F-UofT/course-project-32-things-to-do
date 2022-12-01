import entities.CourseMap;
import screens.course_features.*;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentDsGateway;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentInputBoundary;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentInteractor;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentOutputBoundary;

import javax.swing.*;
import java.awt.*;

public class CourseEnrolmentMain {
    public static void main(String[] args) {

        // Build the main program window
        JFrame application = new JFrame("Course Enrolment Example!");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        // Create parts to plug into use case + entities engine
        CourseEnrolmentDsGateway course = null;

        CourseEnrolmentOutputBoundary presenter = new CourseEnrolmentPresenter();

        // not to be initialized, for the sake of running only
        CourseMap courseMap = new CourseMap();
        // not to be initialized, for the sake of running only

        CourseEnrolmentInputBoundary interactor = new CourseEnrolmentInteractor(
                course, presenter, courseMap, "studentid");
        CourseEnrolmentController controller = new CourseEnrolmentController(interactor);

        // Build GUI, plug in parts
        CourseEnrolmentScreen enrolmentScreen = new CourseEnrolmentScreen(controller);
        screens.add(enrolmentScreen, "Enrol in a course");
        cardLayout.show(screens, "enrol in course");
        application.pack();
        application.setVisible(true);
    }
}
