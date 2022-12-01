import screens.course_features.*;
import use_cases.course_features.course_creation_use_case.CourseCreationDsGateway;
import use_cases.course_features.course_creation_use_case.CourseCreationInputBoundary;
import use_cases.course_features.course_creation_use_case.CourseCreationInteractor;
import use_cases.course_features.course_creation_use_case.CourseCreationOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CourseCreationMain {
    public static void main(String[] args) {

        // Build the main program window
        JFrame application = new JFrame("Course Creation Example!");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        // Create parts to plug into use case + entities engine
        CourseCreationDsGateway course;
        try {
            // for testing:
            // course = new InMemoryCourse();
            course = new FileCourse("courses.ser");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Could not create file.");
        }

        CourseCreationOutputBoundary presenter = new CourseCreationPresenter();
        CourseCreationInputBoundary interactor = new CourseCreationInteractor(course, presenter);
        CourseCreationController controller = new CourseCreationController(interactor);

        // Build the GUI, plugging in the parts
        CourseCreationScreen createScreen = new CourseCreationScreen(controller, screens, cardLayout);
        screens.add(createScreen, "Create a new course");
        cardLayout.show(screens, "create a new course");
        application.pack();
        application.setVisible(true);
    }
}
