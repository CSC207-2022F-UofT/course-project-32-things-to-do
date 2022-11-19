package course_creation_use_case;

// Use case layer

/* Notes
VERY CONFUSED --> NEED COURSE FACTORY?
- ok i think the problem is i need to decide how to store date:
    1. csv file with rows (i want, but how to store courseID as well?)
    2. coursefactory ???? whatever that is
    3. courseMAp ...... not really useful for me?
  - and problemo is: which one of them is persistent / serializable?

** the class that runs the use case (subclass of input boundary)
the highest level concept; the most protected
 */

//import Entities.CourseFactory;
import Entities.CourseMap;

public class CourseCreationInteractor implements CourseCreationInputBoundary {
    final CourseCreationDsGateway courseCreationDSGateway;
    final CourseCreationPresenter courseCreationPresenter;
//    final CourseFactory courseFactory;
    final CourseMap courseMap;

    public CourseCreationInteractor(CourseCreationDsGateway courseCreationDSGateway, CourseCreationPresenter courseCreationPresenter,
                                    CourseMap courseMap) {
        this.courseCreationDSGateway = courseCreationDSGateway;
        this.courseCreationPresenter = courseCreationPresenter;
        this.courseMap = courseMap;
    }

//    public courseCreationInteractor(courseCreationDsGateway courseCreationDSGateway, courseCreationPresenter courseCreationPresenter,
//                                    CourseFactory courseFactory) {
//        this.courseCreationDSGateway = courseCreationDSGateway;
//        this.courseCreationPresenter = courseCreationPresenter;
//        this.courseFactory = courseFactory;
//    }

    @Override
    public CourseCreationResponseModel create(CourseCreationRequestModel requestModel) {
        /* At least one info box left blank */
        if (requestModel.getCourseName().equals("") || requestModel.getCourseInstructor().equals("") || requestModel.getTasks().isEmpty()) {
            return courseCreationPresenter.prepareFailView("Please fill in all required information.");
        }
        /*
        Note: no need to check if is instructor; users would have different
        views because they are in different use cases
        If the course id (same course name and instructor name) already exists, new
        course will not be made.
        Else success
         */
        if (courseCreationDSGateway.existsByCourseID(requestModel.getCourseID())) {
            return courseCreationPresenter.prepareFailView("Course already exists.");
        }
        return null;

        /*
         * what is a courseFactory
         */

        /** confused lol
        Course course = CourseMap.addCourse(requestModel.getCourseID(), course);
        Course course1 = CourseMap.addCourse(requestModel.getCourseID(), requestModel.getTasks());
        */

        /** checks passed, course successfully created and saved
        courseCreationRequestModel courseCreationModel = new courseCreationRequestModel(course.getCourseName(), course.getCourseInstructor(), course.getTasks());
        courseCreationDSGateway.saveCourse(courseCreationModel);
//        courseCreationDSRequestModel courseCreationDSModel = new courseCreationDSRequestModel(course.getCourseName(), course.getCourseInstructor(), course.getTasks());
//        courseCreationDSGateway.saveCourse(courseCreationDSModel);
        */
//
        /** checks passed, course sent to presenter */
//        courseCreationResponseModel courseResponseModel = new courseCreationResponseModel(
//                course.getCourseID(), course.getTasks());
//        return courseCreationPresenter.prepareSuccessView(courseResponseModel);


        /**
         * below is not it */
//        courseCreationResponseModel courseResponseModel = new courseCreationResponseModel(
//                                                            course.getCourseID(), course.getTasks());
//        return courseCreationPresenter.prepareFailViewSuccessView(courseResponseModel);
    }
}
