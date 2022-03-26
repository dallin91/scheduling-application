package controller;

import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contact;
import model.Customer;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class UpdateAppointment implements Initializable {

    @FXML
    private TextField apptTitle;
    @FXML
    private TextField apptDescription;
    @FXML
    private TextField apptLocation;
    @FXML
    private TextField apptType;
    @FXML
    private DatePicker apptStartDate;
    @FXML
    private DatePicker apptEndDate;
    @FXML
    private ComboBox<Integer> custID;
    @FXML
    private ComboBox<Integer> userID;
    @FXML
    private TextField apptID;
    @FXML
    private ComboBox<String> contactID;
    @FXML
    private ComboBox<String> apptStartTime;
    @FXML
    private ComboBox<String> apptEndTime;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        //populate Contact combo box
        ObservableList<Contact> contactObservableList = DBContacts.getAllContacts();
        ObservableList<String> contactNames = FXCollections.observableArrayList();
        contactObservableList.forEach(contact -> contactNames.add(contact.getContactName()));
        contactID.setItems(contactNames);

        //populate User ID combo box
        ObservableList<User> userObservableList = DBUsers.getAllUsers();
        ObservableList<Integer> userIDList = FXCollections.observableArrayList();
        userObservableList.forEach(user -> userIDList.add(user.getUserID()));
        userID.setItems(userIDList);

        //populate Customer ID combo box
        ObservableList<Customer> customerObservableList = DBCustomers.getAllCustomers();
        ObservableList<Integer> customerIDList = FXCollections.observableArrayList();
        customerObservableList.forEach(customer -> customerIDList.add(customer.getId()));
        custID.setItems(customerIDList);

        //populate time combo boxes
        ObservableList<String> apptTimes = FXCollections.observableArrayList();
        LocalTime firstApptTime = LocalTime.MIN.plusHours(8);
        LocalTime lastApptTime = LocalTime.MAX.minusHours(1).minusMinutes(59);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        while (firstApptTime.isBefore(lastApptTime)) {
            apptTimes.add(dateTimeFormatter.format(firstApptTime));
            firstApptTime = firstApptTime.plusMinutes(30);
        }
        apptStartTime.setItems(apptTimes);
        apptEndTime.setItems(apptTimes);


    }

    public void toSchedulingSave(ActionEvent actionEvent) throws IOException, SQLException {
        //addAppointment();

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
}
