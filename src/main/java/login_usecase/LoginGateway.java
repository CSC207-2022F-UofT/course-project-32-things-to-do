package login_usecase;

import user_register_usecase.UserRegSaveRequest;

import java.util.Map;

public interface LoginGateway {

    boolean existsByName(String name);

    String passOf(String name);

    Map<String, UserRegSaveRequest> getAccounts();

}
