package screens;

import user_register_usecase.UserRegSaveRequest;
import user_register_usecase.UserRegGateway;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUser implements UserRegGateway {

    final private Map<String, UserRegSaveRequest> users = new HashMap<>();

    /**
     * @param identifier the user's username
     * @return whether the user exists
     */

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
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
