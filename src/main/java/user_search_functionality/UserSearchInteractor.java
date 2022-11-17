package user_search_functionality;

// Use Case Layer

public class UserSearchInteractor {

    /** Use Case Layer
     * Searches the User Database for this User */

    final UserSearchGateway userGateway;


    public UserSearchInteractor(UserSearchGateway gateway) {
        this.userGateway = gateway;
    }

    boolean searchUser(String name) {
        /**
         * Return true iff this User exists in the system. Might change to return the User
         * itself later, or "User does not exist" if that's the case.
         */
        if (userGateway.existsByName(name)) {
            return true;
        } else {
            return false;
        }
    }
}
