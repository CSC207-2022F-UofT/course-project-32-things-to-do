package login_usecase;

import user_register_usecase.UserRegSaveRequest;

import java.util.Map;

public interface LoginGateway {

    /**
     * @param name the name of this User
     * @return whether the User exists in the User database
     */
    boolean existsByName(String name);

    /**
     * @param name the name of this User
     * @return the password of this User
     */
    String passOf(String name);

    /**
     * @return a Map that maps the usernames of all users in the User Database to UserRegSaveRequest objects, which
     * store the same information as each type of user.
     */
    Map<String, UserRegSaveRequest> getAccounts();

}
