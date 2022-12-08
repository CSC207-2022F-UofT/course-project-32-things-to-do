package screens.login_registration;
import entities.StudentUser;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentUserDsGateway;
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

public class FileUser implements UserRegGateway, LoginGateway, LogoutGateway, CourseEnrolmentUserDsGateway {

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
//            this.accounts = readFile();
            accounts = readFile();
        } else {
//            this.accounts = new HashMap<String, UserRegSaveRequest>();
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

        FileOutputStream fileWriter;
//        if (Files.exists(Path.of(filePath))) {
//            fileWriter = new FileOutputStream(filePath);
//        } else {
//            fileWriter = new FileOutputStream("src/main/java/data/users.ser");
//        }
        fileWriter = new FileOutputStream(filePath);
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

    public Map<String, UserRegSaveRequest> getAccounts() {
//        return this.accounts;
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
    public void addTasksToTodolist(String studentID, ArrayList<String> courseTasks) throws IOException {
        // casting to student save request
        ((StudentSaveRequest) accounts.get(studentID)).getToDoList().addAll(courseTasks);
        save();
        // in interactor, update CurrentUser
        // make a new StudentSaveRequest with CurrentUser
        // call Gateway.save(StudentSaveRequest)
    }

    /**
     * For course enrolment use case (course tasks to do list gateway)
     * Adds the course id to the student's 'courses' parameter
     * @param courseID the course the student enrolled in
     * @param studentID the username of student enrolled
     */
    @Override
    public void addCourseToStudent(String courseID, String studentID) throws IOException {
        // casting to student save request
        // initialize current
//        StudentUser s = accounts.get(studentID);
        ((StudentSaveRequest) accounts.get(studentID)).getCourses().add(courseID);
        save();
        // if adding
//         StudentUser s = (StudentUser) CurrentUser.getCurrentUser()
//         s.addCourse
    }
}
