package entities;

/**
 * An entity to hold the current user that is logged into the program
 */

public class CurrentUser {

    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        CurrentUser.currentUser = currentUser;
    }

    /**
     * Return true if the currently logged-in user is a StudentUser, false if null or a different user
     * @return boolean representing whether the current user is a student
     */
    public boolean isStudent() {
        if (currentUser != null) {
            return currentUser instanceof StudentUser;
        } else {
            return false;
        }
    }

    /**
     * Return true if the currently logged-in user is an InstructorUser, false if null or a different user
     * @return boolean representing whether the current user is an instructor
     */
    public boolean isInstructor() {
        if (currentUser != null) {
            return currentUser instanceof InstructorUser;
        } else {
            return false;
        }
    }
}
