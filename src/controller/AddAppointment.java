package controller;

import DBAccess.DBContacts;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {

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
    private ComboBox<String> custID;
    @FXML
    private ComboBox<String> userID;
    @FXML
    private TextField apptID;
    @FXML
    private ComboBox<String> contactID;
    @FXML
    private ComboBox<String> apptStartTime;
    @FXML
    private ComboBox<String> apptEndTime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Contact> contactObservableList = DBContacts.getAllContacts();
        ObservableList<String> contactNames = FXCollections.observableArrayList();
        contactObservableList.forEach(contact -> contactNames.add(contact.getContactName()));
        contactID.setItems(contactNames);
        contactID.setEditable(true);
        contactID.getEditor().setEditable(false);
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
