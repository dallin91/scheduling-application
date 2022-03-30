package model;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is a model for a country
 * */
public class Country {

    private int countryID;
    private String countryName;

    /**
     * This creates a new Country object
     *
     * @param countryID is the country id
     * @param countryName is the country name
     * */
    public Country(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /**
     * Returns the country id
     * */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Sets the country id
     *
     * @param countryID is the country id
     * */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * Returns the country name
     * */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the country name
     *
     * @param countryName is the country name
     * */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


}
