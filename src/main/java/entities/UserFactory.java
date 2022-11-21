package entities;


// Entity Layer

public interface UserFactory {
    /**
     * Creates Users with the specified name and password
     */
    User create(String name, String password);
}
