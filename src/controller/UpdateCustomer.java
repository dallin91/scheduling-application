package controller;

import DBAccess.DBCountries;
import DBAccess.DBFirstLevelDivision;
import Database.DBConnection;
import Database.DBUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UpdateCustomer {
    @FXML
    private TextField custID;
    @FXML
    private TextField custName;
    @FXML
    private TextField addressField;
    @FXML
    private TextField zipCode;
    @FXML
    private TextField phoneNumber;
    @FXML
    private ComboBox<String> custState;
    @FXML
    private ComboBox<String> custCountry;

    public void initialize() throws SQLException {

        setCustCountry();
        setCustState();

        Customer chosenCustomer = SchedulingPage.getCustomerToUpdate();

        custID.setText(String.valueOf(chosenCustomer.getId()));
        custName.setText(String.valueOf(chosenCustomer.getName()));
        addressField.setText(String.valueOf(chosenCustomer.getAddress()));
        zipCode.setText(String.valueOf(chosenCustomer.getZipCode()));
        phoneNumber.setText(String.valueOf(chosenCustomer.getPhoneNumber()));

        //populate combo boxes
        int divID = chosenCustomer.getDivisionID();
        String state = null;
        String country = null;
        int countryID = 0;
        ObservableList<Country> allCountries = DBCountries.getAllCountries();
        ObservableList<FirstLevelDivision> allDivisions = DBFirstLevelDivision.getFirstLevelDivisions();
        for (FirstLevelDivision d : allDivisions) {
            if (d.getDivisionID() == divID) {
                state = d.getDivisionName();
                countryID = d.getCountryID();
            }
        }
        for (Country c : allCountries) {
            if (c.getCountryID() == countryID) {
                country = c.getCountryName();
            }
        }
        custCountry.setValue(country);
        custState.setValue(state);
    }

    public void updateCustomer() throws SQLException {

        try {
            if (!custName.getText().equals("") && !addressField.getText().equals("") && !zipCode.getText().equals("")
                    && !phoneNumber.getText().equals("") && !custState.getValue().equals("") &&
                    !custCountry.getValue().equals("")) {
                System.out.println("Can add customer");

                String newID = custID.getText();
                String newName = custName.getText();
                String newAddress = addressField.getText();
                String newZip = zipCode.getText();
                String newPhone = phoneNumber.getText();

                int newDivision = 0;
                String selectedDivision = custState.getSelectionModel().getSelectedItem();
                ObservableList<FirstLevelDivision> allDivisions = DBFirstLevelDivision.getFirstLevelDivisions();
                for (FirstLevelDivision d : allDivisions) {
                    if (selectedDivision.equals(d.getDivisionName())) {
                        newDivision = d.getDivisionID();
                    }
                }

                Timestamp newLastUpdate = Timestamp.valueOf(LocalDateTime.now());
                String newLastUpdatedBy = "admin";

                String sqlStatement = "UPDATE customers SET Customer_ID = ?, Customer_Name = ?, Address = ?, Postal_Code = ?," +
                        "Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";

                DBUtility.setPreparedStatement(DBConnection.getConnection(), sqlStatement);
                PreparedStatement ps = DBUtility.getPreparedStatement();
                ps.setString(1, newID);
                ps.setString(2, newName);
                ps.setString(3, newAddress);
                ps.setString(4, newZip);
                ps.setString(5, newPhone);
                ps.setTimestamp(6, newLastUpdate);
                ps.setString(7, newLastUpdatedBy);
                ps.setInt(8, newDivision);
                ps.setString(9, newID);
                ps.execute();
            } else {
                System.out.println("Not going to work");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toSchedulingSave(ActionEvent actionEvent) throws IOException, SQLException {
        System.out.println("Ya still gotta do this part you doofus");

        updateCustomer();

        Parent root = FXMLLoader.load(getClass().getResource("/view/SchedulingPage.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Scheduling Page");
        stage.setScene(scene);
        stage.show();

    }

    public void toSchedulingCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/SchedulingPage.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Scheduling Page");
        stage.setScene(scene);
        stage.show();
    }

    public void setCustCountry() throws SQLException {

        ObservableList<Country> countryList = DBCountries.getAllCountries();
        ObservableList<String> countryNames = FXCollections.observableArrayList();

        for (Country c : countryList) {
            countryNames.add(c.getCountryName());
        }

        custCountry.setItems(countryNames);
    }

    public void setCustState() throws SQLException {

        ObservableList<FirstLevelDivision> divisionList = DBFirstLevelDivision.getFirstLevelDivisions();
        ObservableList<String> divisionNames = FXCollections.observableArrayList();

        for (FirstLevelDivision d : divisionList) {
            divisionNames.add(d.getDivisionName());
        }

        custState.setItems(divisionNames);
    }
}
