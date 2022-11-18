import Entities.CourseFactory;
import course_creation_use_case.courseCreationDsGateway;
import course_creation_use_case.courseCreationInputBoundary;
import course_creation_use_case.courseCreationInteractor;
import course_creation_use_case.courseCreationPresenter;
import screens.CourseCreationController;
import screens.CourseCreationResponseFormatter;
import screens.CreateCourseScreen;
import screens.FileCourse;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        // build main program window
        JFrame application = new JFrame("Course Creation Example");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        // create parts to plug into the use case + entities engine
        courseCreationDsGateway course;
        try {
            course = new FileCourse("./courses.csv");
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        courseCreationPresenter presenter = new CourseCreationResponseFormatter();
        CourseFactory courseFactory = new CourseFactory();
        courseCreationInputBoundary interactor = new courseCreationInteractor(
                course, presenter, courseFactory);
        CourseCreationController courseCreationController = new CourseCreationController(
                interactor
        );

        // build the GUI, plugging in the parts
        CreateCourseScreen createCourseScreen = new CreateCourseScreen(courseCreationController);
        screens.add(createCourseScreen, "create a new course");
        cardLayout.show(screens, "create course");
        application.pack();
        application.setVisible(true);

    }
}
