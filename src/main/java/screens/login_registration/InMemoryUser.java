package screens.login_registration;

import use_cases.login_registration.login_usecase.*;
import use_cases.login_registration.logout_usecase.*;
import use_cases.login_registration.user_register_usecase.*;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUser implements UserRegGateway, LoginGateway, LogoutGateway {

    final private Map<String, UserRegSaveRequest> users = new HashMap<>();

    /**
     * @param identifier the user's username
     * @return whether the user exists
     */

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public String passOf(String name) {
        return users.get(name).getPass();
    }

    public Map<String, UserRegSaveRequest> getAccounts() {
        return users;
    }

    /**
     * @param requestModel the data to save
     */

    @Override
    public void save(UserRegSaveRequest requestModel) {
        System.out.println("Save " + requestModel.getName());
        users.put(requestModel.getName(), requestModel);
    }

}
