package model;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is the model for a customer
 *
 * @author Dallin Reeves
 * */
public class Customer {



    private int id;

    private String name;

    private String address;

    private String zipCode;

    private String phoneNumber;

    public int divisionID;


    /**
     * This creates a new Customer object
     *
     * @param id is the id
     * @param name is the name
     * @param address is the address
     * @param zipCode is the zip code
     * @param phoneNumber is the phone number
     * @param divisionID is the division id
     * */
    public Customer(int id, String name, String address, String zipCode, String phoneNumber, int divisionID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.divisionID = divisionID;
    }

    /**
     * Returns the id
     * */
    public int getId() {
        return id;
    }

    /**
     * Sets the id
     *
     * @param id is the id
     * */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name
     * */
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     *
     * @param name is the name
     * */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the address
     * */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address
     *
     * @param address is the address
     * */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the zip code
     * */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the zip code
     *
     * @param zipCode is the zip code
     * */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Returns the phone number
     * */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number
     *
     * @param phoneNumber is the phone number
     * */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the division id
     * */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Sets the division id
     *
     * @param divisionID is the division id
     * */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }


}
