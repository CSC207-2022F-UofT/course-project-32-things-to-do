package screens.login_registration;
import entities.Course;
import entities.StudentUser;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentDsGateway;
import use_cases.course_features.course_enrolment_use_case.CourseTasksToStudentTodolistDsGateway;
import use_cases.login_registration.login_usecase.LoginGateway;
import use_cases.login_registration.logout_usecase.LogoutGateway;
import use_cases.login_registration.user_register_usecase.StudentSaveRequest;
import use_cases.login_registration.user_register_usecase.UserRegGateway;
import use_cases.login_registration.user_register_usecase.UserRegSaveRequest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileUser implements UserRegGateway, LoginGateway, LogoutGateway, CourseTasksToStudentTodolistDsGateway {

//    private final HashMap<String, UserRegSaveRequest> accounts;
    private static HashMap<String, UserRegSaveRequest> accounts;

    private final String filePath;

    public FileUser(String path) throws IOException, ClassNotFoundException {
        /*
         * Read the User database file at filePath and store its contents (i.e. a HashMap that maps all usernames to
         * UserRegSaveRequest objects) in the accounts instance variable.
         */

        this.filePath = path;

        // method that checks if a path or file exists or not and then
        // writes an empty map to a new file if it doesn't exist
        // and reads the existing file if it does exist

        if (Files.exists(Path.of(path))) {
            accounts = readFile();
        } else {
            accounts = new HashMap<String, UserRegSaveRequest>();
            save();
        }
    }

    private HashMap<String, UserRegSaveRequest> readFile() throws IOException, ClassNotFoundException {
        FileInputStream fileReader = new FileInputStream(this.filePath);
        ObjectInputStream in = new ObjectInputStream(fileReader);
        HashMap<String, UserRegSaveRequest> f = (HashMap<String, UserRegSaveRequest>) in.readObject();
        in.close();
        fileReader.close();
        return f;
    }

    /**
     * Add UserRegSaveRequest to storage.
     * @param requestModel the user information to save.
     */
    @Override
    public void save(UserRegSaveRequest requestModel) throws IOException {
        accounts.put(requestModel.getName(), requestModel);
        this.save();
    }

    private void save() throws IOException {
        /*
         * Write the map of usernames to UserRegSaveRequest objects into the User database file.
         */

        // check if file exists already and if it doesn't, open a new file
        FileOutputStream fileWriter;
        if (Files.exists(Path.of(filePath))) {
            fileWriter = new FileOutputStream(filePath);
        } else {
            fileWriter = new FileOutputStream("src/main/java/data/users.ser");
        }
//        fileWriter = new FileOutputStream(filePath);
        ObjectOutputStream out = new ObjectOutputStream(fileWriter);
        out.writeObject(accounts);
        out.close();
        fileWriter.close();
    }

    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public String passOf(String name) {
        return accounts.get(name).getPass();
    }

    @Override
    public Map<String, UserRegSaveRequest> getAccounts() {
        return accounts;
    }

    /**
     * For course enrolment use case (course tasks to do list gateway)
     * Adds the course tasks to the student's to-do list
     *
     * @param studentID the username of the student whose parameters are being modified
     * @param courseTasks the course task ids what will be added to the student's 'to do list' parameter
     */
    @Override
    public void addSaveTasksToTodolist(String studentID, ArrayList<String> courseTasks) throws IOException {
        UserRegSaveRequest username = getAccounts().get(studentID);
        // casting to student save request
        ((StudentSaveRequest) username).getToDoList().addAll(courseTasks);
        this.save(); // saves the file with new changes
    }

    /**
     * For course enrolment use case (course tasks to do list gateway)
     * Adds the course id to the student's 'courses' parameter
     * @param studentCourse the course the student enrolled in
     * @param studentID the username of student enrolled
     */
    @Override
    public void addCourseToStudent(String studentCourse, String studentID) throws IOException {
        UserRegSaveRequest courseInStudent = getAccounts().get(studentID);
        // casting to student save request
        ((StudentSaveRequest) courseInStudent).getCourses().add(studentCourse);
        this.save(); // saves the file with new changes
    }
}
