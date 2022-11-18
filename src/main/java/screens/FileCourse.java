package screens;

import course_creation_use_case.courseCreationDsGateway;
import course_creation_use_case.courseCreationDsRequestModel;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileCourse implements courseCreationDsGateway {
    private final File csvFile;
    private final Map<String, Integer> headers = new HashMap<>();
    private final Map<String, courseCreationDsRequestModel> courses = new HashMap<>();

    public FileCourse(String csvPath) throws IOException {
        csvFile = new File(csvPath);

        headers.put("courseName", 0);
        headers.put("course instructor", 1);
        headers.put("tasks?", 2);

        if (csvFile.length() == 0) {
            saveCourse();
        } else {

            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            reader.readLine(); // skip the header

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String courseName = String.valueOf(col[headers.get("courseName")]);
                String courseInstructor = String.valueOf(col[headers.get("courseInstructor")]);
                ArrayList<String> tasks = new ArrayList<String>(); // idk what im doing
//                        /* String.valueOf(col[headers.get("tasks?")]); */
                courseCreationDsRequestModel course = new courseCreationDsRequestModel(courseName, courseInstructor, tasks);
                courses.put(courseName, course);
            }

            reader.close();

        }

    }

    /**
     * Add requestModel to storage
     * @param requestModel the user information to save
     */
    @Override
    public void saveCourse(courseCreationDsRequestModel requestModel) {
        courses.put(requestModel.getCourseName() ,requestModel);
        this.saveCourse();
    }

    private void saveCourse() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (courseCreationDsRequestModel course : courses.values()) {
                String line = "%s,%s,%s".format(
                        course.getCourseName(), course.getCourseInstructor(), course.getTasks());
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean existsByCourseID(String identifier) {
        return courses.containsKey(identifier);
    }

}
