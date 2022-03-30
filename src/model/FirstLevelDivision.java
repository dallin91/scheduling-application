package model;

/**
 * This class is the model for a FirstLevelDivision
 *
 * @author Dallin Reeves
 * */
public class FirstLevelDivision {
    private int divisionID;
    private String divisionName;
    private int countryID;

    /**
     * This constructor creates a new FirstLevelDivision object.
     *
     * @param divisionID Is the divisionID for this object
     * @param divisionName is the name for this object
     * @param countryID is the country id for this object
     * */
    public FirstLevelDivision(int divisionID, String divisionName, int countryID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }

    /**
     * This returns the divisionID
     * */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * This sets the divisionID
     *
     * @param divisionID the divisionID
     * */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * This returns the division name
     * */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * This sets the division name
     *
     * @param divisionName is the division name
     * */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * This gets the country id
     * */
    public int getCountryID() {
        return countryID;
    }

    /**
     * This sets the country id
     *
     * @param countryID is the country id
     * */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
}
