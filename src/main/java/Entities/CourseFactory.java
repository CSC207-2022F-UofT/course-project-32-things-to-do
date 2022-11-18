package Entities;

// Entity layer

import java.util.ArrayList;

public class CourseFactory {
    public Course create(String courseName, String courseInstructor, ArrayList<String> tasks) {
        return new Course(courseName, courseInstructor, tasks);
    }
}
