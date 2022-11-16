package user_register_usecase;

// Use case layer

public interface UserRegGateway {

    boolean existsByName(String identifier);

    void save(UserRegSaveRequest requestModel);
}
