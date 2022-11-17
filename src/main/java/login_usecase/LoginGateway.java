package login_usecase;

public interface LoginGateway {

    boolean existsByName(String name);

    String passOf(String name);

}
