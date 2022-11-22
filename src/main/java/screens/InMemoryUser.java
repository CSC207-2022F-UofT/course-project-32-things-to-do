package screens;

import login_usecase.LoginGateway;
import user_register_usecase.UserRegSaveRequest;
import user_register_usecase.UserRegGateway;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUser implements UserRegGateway, LoginGateway {

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
        return null;
    }

    @Override
    public Map<String, UserRegSaveRequest> getAccounts() {
        return null;
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
