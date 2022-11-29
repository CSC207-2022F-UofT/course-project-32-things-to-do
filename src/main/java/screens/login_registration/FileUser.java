package screens.login_registration;

import use_cases.login_registration.login_usecase.LoginGateway;
import use_cases.login_registration.logout_usecase.LogoutGateway;
import use_cases.login_registration.user_register_usecase.UserRegGateway;
import use_cases.login_registration.user_register_usecase.UserRegSaveRequest;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUser implements UserRegGateway, LoginGateway, LogoutGateway {

    private final HashMap<String, UserRegSaveRequest> accounts;

    private final String filePath;

    public FileUser(String path) throws IOException, ClassNotFoundException {
        /*
         * Read the User database file at filePath and store its contents (i.e. a HashMap that maps all usernames to
         * UserRegSaveRequest objects) in the accounts instance variable.
         */

        this.filePath = path;
        this.accounts = readFile();
    }

    private HashMap<String, UserRegSaveRequest> readFile() throws IOException, ClassNotFoundException {
        FileInputStream fileReader = new FileInputStream(this.filePath);
        ObjectInputStream in = new ObjectInputStream(fileReader);
        HashMap<String, UserRegSaveRequest> f = (HashMap<String, UserRegSaveRequest>) in.readObject();
        in.close();
        fileReader.close();
        return f;
    }

    /**
     * Add UserRegSaveRequest to storage.
     * @param requestModel the user information to save.
     */
    @Override
    public void save(UserRegSaveRequest requestModel) throws IOException {
        accounts.put(requestModel.getName(), requestModel);
        this.save();
    }

    private void save() throws IOException {
        /*
         * Write the new map of usernames to UserRegSaveRequest objects into the User database file.
         */

        FileOutputStream fileWriter = new FileOutputStream(filePath);
        ObjectOutputStream out = new ObjectOutputStream(fileWriter);
        out.writeObject(accounts);
        out.close();
        fileWriter.close();
    }

    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public String passOf(String name) {
        return accounts.get(name).getPass();
    }

    public Map<String, UserRegSaveRequest> getAccounts() {
        return this.accounts;
    }

//    /**
//     * @param name The username of the user
//     * @return the UserRegSaveRequest object associated with this username.
//     * Preconditions: name is a key in accounts
//     */
//    public UserRegSaveRequest getUserSaveReq(String name) throws KeyException, IOException {
////        accounts.get(name);
//        return accounts.get(name);
//    }
}
