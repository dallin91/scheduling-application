package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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

    }
}
