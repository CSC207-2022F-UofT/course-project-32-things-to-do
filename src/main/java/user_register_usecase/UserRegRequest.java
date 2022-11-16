package user_register_usecase;

// Use Case Layer

public class UserRegRequest {

    private String name;

    private String password;

    private String reenterPassword;

    public UserRegRequest(String name, String password, String reenterPassword) {
        this.name = name;
        this.password = password;
        this.reenterPassword = reenterPassword;
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

}
