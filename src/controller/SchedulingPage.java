package controller;

import DBAccess.DBAppointments;
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
import model.Appointment;
import model.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class SchedulingPage {
    @FXML
    private Button addCustBtn;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableView<Appointment> appointmentTable;
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
    @FXML
    private TableColumn<Appointment, Integer> apptID;
    @FXML
    private TableColumn<Appointment, String> apptTitle;
    @FXML
    private TableColumn<Appointment, String> apptDesc;
    @FXML
    private TableColumn<Appointment, String> apptLoc;
    @FXML
    private TableColumn<Appointment, Integer> apptContact;
    @FXML
    private TableColumn<Appointment, String> apptType;
    @FXML
    private TableColumn<Appointment, LocalDateTime> apptStartTime;
    @FXML
    private TableColumn<Appointment, LocalDateTime> apptEndTime;
    @FXML
    private TableColumn<Appointment, Integer> apptCustID;
    @FXML
    private TableColumn<Appointment, Integer> apptUserID;


    public void initialize() throws SQLException {

        System.out.println("Yippee it worked!");

        //populate customer table
        populateCustomerTableView();

        custID.setCellValueFactory(new PropertyValueFactory<>("id"));
        custName.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        custAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        custZip.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        custFirstLevelDivision.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        custPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        //populate appointment table
        populateAppointmentTableView();

        apptID.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptLoc.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptContact.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        apptType.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        apptEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        apptCustID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptUserID.setCellValueFactory(new PropertyValueFactory<>("userId"));

    }

    public void populateCustomerTableView() throws SQLException {
        ObservableList<Customer> customerList = DBCustomers.getAllCustomers();
        customerTable.setItems(customerList);
    }

    public void populateAppointmentTableView() throws SQLException {
        ObservableList<Appointment> appointmentList = DBAppointments.getAllAppointments();
        appointmentTable.setItems(appointmentList);
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
