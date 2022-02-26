package model;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {

    private static ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

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

    public static ObservableList<Customer> getCustomerObservableList() throws SQLException {
        String sql = "SELECT * from customers";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int id = rs.getInt("Customer_ID");
            String name = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String zipCode = rs.getString("Postal_Code");
            String phoneNumber = rs.getString("Phone");
            int divisionID = rs.getInt("Division_ID");
            Customer customer = new Customer(id, name, address, zipCode, phoneNumber, divisionID);
            customerObservableList.add(customer);
        }
        return customerObservableList;
    }
}
