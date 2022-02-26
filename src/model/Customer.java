package model;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {



    private int id;

    private String name;

    private String address;

    private String zipCode;

    private String phoneNumber;

    public int divisionID;


    //constructor
    public Customer(int id, String name, String address, String zipCode, String phoneNumber, int divisionID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.divisionID = divisionID;
    }

    //get id
    public int getId() {
        return id;
    }

    //set id
    public void setId(int id) {
        this.id = id;
    }

    //get name
    public String name() {
        return name;
    }

    //set name
    public void name(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }


}
