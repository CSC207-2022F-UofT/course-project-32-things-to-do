package Entities;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A map of courseID to the Course
 */

public class CourseMap implements Serializable {
    private static HashMap<String, Course> courseMap;

    /**
     * Find a course using its unique ID
     * @param id the unique ID of the course
     * @return the Course object associated with the ID
     */
    public Course findCourse(String id) {
        return courseMap.get(id);
    }

    /**
     * @param id the unique ID of the Course object
     * @param course the course associated with Course
     * @return if the course has been added
     */
    public static boolean addCourse(String id, Course course) {
        if (courseMap.containsKey(id)) {
            return false;
        }
        courseMap.put(id, course);
        return true;
    }

    public void save() {

    }

    public void load() {

    }
}
