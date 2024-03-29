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
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * This class is the controller for AddCustomer.fxml
 *
 * @author Dallin Reeves
 * */
public class AddCustomer {
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


    /**
     * This initializes the form
     * */
    public void initialize() throws SQLException{

        setCustCountry();
        setCustState();

    }

    /**
     * This will save the information in the fields as a new customer and return to the scheduling page
     *
     * @param actionEvent
     * */
    public void toSchedulingSave(ActionEvent actionEvent) throws IOException, SQLException {


        addCustomer();

        Parent root = FXMLLoader.load(getClass().getResource("/view/SchedulingPage.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Scheduling Page");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * This will add the customer information to the database as well as conduct validation checks.
     * */
    public void addCustomer() throws SQLException {

        try {
            if (!custName.getText().equals("") && !addressField.getText().equals("") && !zipCode.getText().equals("")
            && !phoneNumber.getText().equals("") && !custState.getValue().equals("") &&
                    !custCountry.getValue().equals("")) {
                System.out.println("Can add customer");

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

                LocalDateTime newDateToAdd = LocalDateTime.now();
                String newCreatedBy = "admin";
                Timestamp newLastUpdate = Timestamp.valueOf(LocalDateTime.now());
                String newLastUpdatedBy = "admin";

                String sqlStatement = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, " +
                        "Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) " +
                        "VALUES (?,?,?,?,?,?,?,?,?)";

                DBUtility.setPreparedStatement(DBConnection.getConnection(), sqlStatement);
                PreparedStatement ps = DBUtility.getPreparedStatement();
                ps.setString(1, newName);
                ps.setString(2, newAddress);
                ps.setString(3, newZip);
                ps.setString(4, newPhone);
                ps.setTimestamp(5, Timestamp.valueOf(newDateToAdd));
                ps.setString(6, newCreatedBy);
                ps.setTimestamp(7, newLastUpdate);
                ps.setString(8, newLastUpdatedBy);
                ps.setInt(9, newDivision);
                ps.execute();
            }
            else {
                System.out.println("Not gonna work");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This will set the items for the country combo box
     * */
    public void setCustCountry() throws SQLException {

        ObservableList<Country> countryList = DBCountries.getAllCountries();
        ObservableList<String> countryNames = FXCollections.observableArrayList();

        for (Country c : countryList) {
            countryNames.add(c.getCountryName());
        }

        custCountry.setItems(countryNames);
    }


    /**
     * This will set the items for the state combo box
     * */
    public void setCustState() throws SQLException {

        ObservableList<FirstLevelDivision> divisionList = DBFirstLevelDivision.getFirstLevelDivisions();
        ObservableList<String> divisionNames = FXCollections.observableArrayList();

        for (FirstLevelDivision d : divisionList) {
            divisionNames.add(d.getDivisionName());
        }

        custState.setItems(divisionNames);
    }

    /**
     * This filters the state combo box so it only shows states/provinces for the country selected
     * */
    public void filterCustState() throws SQLException {
        ObservableList<FirstLevelDivision> divisionList = DBFirstLevelDivision.getFirstLevelDivisions();
        ObservableList<String> divisionNamesToSet = FXCollections.observableArrayList();
        ObservableList<Country> countryList = DBCountries.getAllCountries();
        ObservableList<String> countryNames = FXCollections.observableArrayList();
        String selectedCountry = custCountry.getSelectionModel().getSelectedItem();
        int countryID = 0;


        for (Country c : countryList) {
            if (c.getCountryName().equals(selectedCountry)) {
                countryID = c.getCountryID();
                break;
            }
        }

        for (FirstLevelDivision f : divisionList) {
            if(f.getCountryID() == countryID) {
                divisionNamesToSet.add(f.getDivisionName());
            }
        }

        custState.setItems(divisionNamesToSet);
    }

    /**
     * Returns to the scheduling page without saving
     * */
    public void toSchedulingCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/SchedulingPage.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Scheduling Page");
        stage.setScene(scene);
        stage.show();
    }
}
