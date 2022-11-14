package entities;


// Entity Layer

public interface UserFactory {
    /** Entity Layer
     * Creates Users
     */

    User create(String name, String password);
}
