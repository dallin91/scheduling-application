package model;

public class Customer {

    private int id;

    private String name;

    private String address;

    private String zipCode;

    private String phoneNumber;

    private String country;

    private String customerArea;


    //constructor
    public Customer(int id, String name, String address, String zipCode, String phoneNumber, String country,
                    String customerArea) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.customerArea = customerArea;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCustomerArea() {
        return customerArea;
    }

    public void setCustomerArea(String customerArea) {
        this.customerArea = customerArea;
    }
}
