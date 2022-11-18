package screens;

import course_creation_use_case.courseCreationDsGateway;
//import course_creation_use_case.courseCreationDsRequestModel;
import course_creation_use_case.courseCreationRequestModel;


import java.util.HashMap;
import java.util.Map;

public class InMemoryCourse implements courseCreationDsGateway {
    final private Map<String, courseCreationRequestModel> courses = new HashMap<>();

    /**
     * @param identifier the course's course id
     * @return whether the course exists
     */
    @Override
    public boolean existsByCourseID(String identifier) {
        return courses.containsKey(identifier);
    }

    /**
     * @param requestModel the data to save
     */
    @Override
    public void saveCourse(courseCreationRequestModel requestModel) {
        System.out.println("Save " + requestModel.getCourseID());
    }
}

//public class InMemoryCourse implements courseCreationDsGateway {
//    final private Map<String, courseCreationDsRequestModel> courses = new HashMap<>();
//
//    /**
//     * @param identifier the course's course id
//     * @return whether the course exists
//     */
//    @Override
//    public boolean existsByCourseID(String identifier) {
//        return courses.containsKey(identifier);
//    }
//
//    /**
//     * @param requestModel the data to save
//     */
//    @Override
//    public void saveCourse(courseCreationDsRequestModel requestModel) {
//        System.out.println("Save " + requestModel.getCourseID());
//    }
//}
