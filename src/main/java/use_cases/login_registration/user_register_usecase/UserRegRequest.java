package use_cases.login_registration.user_register_usecase;

// Use Case Layer

public class UserRegRequest {
    /**
     * A request to register a new user with this name and password (from user input).
     */

    private String name;

    private String password;

    private String reenterPassword;

    private String typeOfUser;

    /**
     * @param name the name of this User
     * @param password the password
     * @param reenterPassword the re-entered password
     * @param userType the type of User (i.e. Student or Instructor)
     */
    public UserRegRequest(String name, String password, String reenterPassword, String userType) {
        this.name = name;
        this.password = password;
        this.reenterPassword = reenterPassword;
        this.typeOfUser = userType;
    }

    String getName() { return name; }

    void setName(String name) {
        this.name = name; }

    String getPassword() { return password; }

    void setPassword(String password) {
        this.password = password; }

    public String getReenterPassword() { return reenterPassword; }

    public void setReenterPassword(String reenterPassword) {
        this.reenterPassword = reenterPassword; }

    String getTypeOfUser() { return typeOfUser; }

    public String setTypeOfUser(String userType) {
        if (userType.equals("Instructor") | userType.equals("Student")) {
            this.typeOfUser = userType;
            return "Success";
        } else {
            return "User type can only be Instructor or Student";
        }
    }

}
