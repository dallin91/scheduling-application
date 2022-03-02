package controller;

import DBAccess.DBCustomers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.sql.SQLException;

public class SchedulingPage {
    @FXML
    private Button addCustBtn;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> custID;
    @FXML
    private TableColumn<Customer, String> custName;
    @FXML
    private TableColumn<Customer, String> custAddress;
    @FXML
    private TableColumn<Customer, String> custZip;
    @FXML
    private TableColumn<Customer, Integer> custFirstLevelDivision;
    @FXML
    private TableColumn<Customer, String> custPhone;


    public void initialize() throws SQLException {

/*
        populateCustomerTableView();

        System.out.println("Yippee it worked!");

        //update customerArea and country with divisionID
        custID.setCellValueFactory(new PropertyValueFactory<>("id"));
        custName.setCellValueFactory(new PropertyValueFactory<>("name"));
        custAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        custZip.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        custFirstLevelDivision.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        custPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        //add country info here later
*/
        //try to replicate pulling data from database from webinar
    }

    public void populateCustomerTableView() throws SQLException {
        ObservableList<Customer> customerList = DBCustomers.getAllCustomers();
        customerTable.setItems(customerList);
    }

    public void toAddCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void toAddAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }
}
