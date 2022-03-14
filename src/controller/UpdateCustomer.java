package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;

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

    public void toSchedulingCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/SchedulingPage.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Scheduling Page");
        stage.setScene(scene);
        stage.show();
    }
}
