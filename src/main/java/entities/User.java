package entities;

import java.io.Serializable;

// Entity
public interface User extends Serializable {

    /** Entity Layer
     * A general User of this program */

    boolean checkPassword();

    String getName();

    public String getPass();

}
