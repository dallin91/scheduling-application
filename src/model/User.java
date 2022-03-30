package model;

/**
 * The User class is a model for a user
 *
 * @author Dallin Reeves
 * */
public class User {
    private int userID;
    private String userName;
    private String userPassword;

    /**
     * This constructor creates a new User object
     *
     * @param userID
     * @param userName
     * @param userPassword
     * */
    public User(int userID, String userName, String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /**
     * This is the getter for the user ID
     *
     * Returns userID
     * */
    public int getUserID() {
        return userID;
    }

    /**
     * This sets the userID
     *
     * @param userID
     * */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * This returns the userName
     * */
    public String getUserName() {
        return userName;
    }

    /**
     * This sets the userName
     *
     * @param userName
     * */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This returns the userPassword
     * */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * This sets the userPassword
     *
     * @param userPassword
     * */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
