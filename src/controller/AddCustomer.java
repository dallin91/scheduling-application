package controller;

import DBAccess.DBCountries;
import DBAccess.DBFirstLevelDivision;
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
import java.sql.SQLException;
import java.util.ResourceBundle;

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


    public void initialize() throws SQLException{

        setCustCountry();
        setCustState();

    }

    public void toSchedulingSave(ActionEvent actionEvent) throws IOException, SQLException {
        System.out.println("Ya still gotta do this part you doofus");

        addCustomer();

        Parent root = FXMLLoader.load(getClass().getResource("/view/SchedulingPage.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Scheduling Page");
        stage.setScene(scene);
        stage.show();

    }

    public void addCustomer() throws SQLException {

        try {
            if (!custName.getText().equals("") && !addressField.getText().equals("") && !zipCode.getText().equals("")
            && !phoneNumber.getText().equals("") && !custState.getValue().equals("") &&
                    !custCountry.getValue().equals("")) {
                System.out.println("Can add customer");
            }
            else {
                System.out.println("Not gonna work");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void toSchedulingCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/SchedulingPage.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Scheduling Page");
        stage.setScene(scene);
        stage.show();
    }
}
