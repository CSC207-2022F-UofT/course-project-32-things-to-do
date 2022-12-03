package use_cases.login_registration.logout_usecase;

import java.io.IOException;

public interface LogoutInputBoundary {

    /** Save a User's information into the database before they log out
     * @throws IOException if saving not successful
     */
    void create() throws IOException;
}
