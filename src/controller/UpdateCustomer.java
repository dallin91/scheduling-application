package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

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

        Customer chosenCustomer = SchedulingPage.getCustomerToUpdate();

        custID.setText(String.valueOf(chosenCustomer.getId()));
        custName.setText(String.valueOf(chosenCustomer.getName()));
        addressField.setText(String.valueOf(chosenCustomer.getAddress()));
        zipCode.setText(String.valueOf(chosenCustomer.getZipCode()));
        phoneNumber.setText(String.valueOf(chosenCustomer.getPhoneNumber()));

        int divID = chosenCustomer.getDivisionID();
        //custState.setSelectionModel(String.valueOf(chosenCustomer.getDivisionID()));
    }

    public void toSchedulingSave() {

    }

    public void toSchedulingCancel() {

    }
}
