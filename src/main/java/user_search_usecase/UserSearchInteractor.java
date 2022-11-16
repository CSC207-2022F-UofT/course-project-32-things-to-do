package user_search_usecase;

// Use Case Layer

import entities.UserFactory;
import user_register_usecase.UserRegGateway;
import user_register_usecase.UserRegPresenter;

public class UserSearchInteractor {

    /** Use Case Layer
     * Searches the User Database for this User */

    final UserRegGateway userGateway;

    final UserRegPresenter userPresenter;

    final UserFactory userFactory;

    public UserSearchInteractor(UserRegGateway gateway, UserRegPresenter userRegPresenter,
                             UserFactory userFactory) {
        this.userGateway = gateway;
        this.userPresenter = userRegPresenter;
        this.userFactory = userFactory;
    }

    public String searchUser(String name) {
        return null;
    }
}
