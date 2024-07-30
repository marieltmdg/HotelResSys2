package basepack;

import java.io.Serializable;

/**
 * The Manager class represents a manager with a username and password.
 * This class implements the Serializable interface to allow its instances
 * to be serialized and deserialized.
 */
public class Manager implements Serializable {
    private String username;
    private String password;

    /**
     * Constructs a new Manager with the specified username and password.
     *
     * @param username the username of the manager
     * @param password the password of the manager
     */
    public Manager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * The getUsername() method returns the username of the manager.
     *
     * @return the username of the manager
     */
    public String getUsername() {
        return username;
    }

    /**
     * The getPassword() method returns the password of the manager.
     *
     * @return the password of the manager
     */
    public String getPassword() {
        return password;
    }
}
