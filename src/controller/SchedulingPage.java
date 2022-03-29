package controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Database.DBConnection;
import Database.DBUtility;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

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
    private static Customer customerToUpdate;
    private static Appointment appointmentToUpdate;


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

    public void toUpdateAppointment(ActionEvent actionEvent) throws IOException {
        appointmentToUpdate = appointmentTable.getSelectionModel().getSelectedItem();

        if (appointmentToUpdate != null) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/UpdateAppointment.fxml"));
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Update Appointment");
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Appointment Selected");
            alert.setContentText("No appointment selected to update. Please select an appointment.");
            alert.showAndWait();
        }
    }

    public static Appointment getAppointmentToUpdate() {
        return appointmentToUpdate;
    }

    public void toUpdateCustomer(ActionEvent actionEvent) throws IOException {
        customerToUpdate = customerTable.getSelectionModel().getSelectedItem();

        if (customerToUpdate != null) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/UpdateCustomer.fxml"));
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Update Customer");
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Customer Selected");
            alert.setContentText("No customer selected to update. Please select a customer.");
            alert.showAndWait();
        }
    }

    public static Customer getCustomerToUpdate() {
        return customerToUpdate;
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

    public void deleteAppt(ActionEvent event) throws SQLException {
        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        int selectedID = selectedAppointment.getAppointmentId();
        String selectedType = selectedAppointment.getType();

        if (selectedAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Deletion Error");
            alert.setContentText("No appointment selected. Please select an appointment to cancel.");
            alert.showAndWait();
        } else {
            Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION);
            alert3.setTitle("Confirm Delete");
            alert3.setContentText("Are you sure you want to cancel this appointment?");
            Optional<ButtonType> confirm = alert3.showAndWait();

            if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                alert4.setTitle("Appointment Canceled");
                alert4.setContentText("Appointment with ID " + selectedID + " and type " + selectedType + " has been canceled!" );
                alert4.showAndWait();

                appointmentTable.getItems().remove(selectedAppointment);

                //now to delete from DB
                int apptIDToDelete = selectedAppointment.getAppointmentId();

                String sqlStatement = "DELETE from appointments WHERE Appointment_ID = ?";

                DBUtility.setPreparedStatement(DBConnection.getConnection(), sqlStatement);
                PreparedStatement ps = DBUtility.getPreparedStatement();
                ps.setInt(1, apptIDToDelete);
                ps.execute();
            }
        }
    }

    public void deleteCustomer(ActionEvent event) throws SQLException {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        int custIDToDelete = selectedCustomer.getId();

        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delection Error");
            alert.setContentText("No customer selected. Please select a customer to delete.");
            alert.showAndWait();
        } else {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Confirm Delete");
            alert2.setContentText("Are you sure you want to delete this customer?");
            Optional<ButtonType> confirm = alert2.showAndWait();

            if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                ObservableList<Appointment> appointmentObservableList = DBAppointments.getAllAppointments();
                boolean custInAppt = false;

                for (Appointment a : appointmentObservableList) {
                    if (a.getCustomerId() == custIDToDelete) {
                        custInAppt = true;
                        break;
                    }
                }

                if (!custInAppt) {

                    Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                    alert4.setTitle("Customer Removed");
                    alert4.setContentText("The customer has been successfully deleted!" );
                    alert4.showAndWait();

                    customerTable.getItems().remove(selectedCustomer);

                    //now for the sql bit
                    String sqlStatement = "DELETE from customers WHERE Customer_ID = ?";

                    DBUtility.setPreparedStatement(DBConnection.getConnection(), sqlStatement);
                    PreparedStatement ps = DBUtility.getPreparedStatement();
                    ps.setInt(1, custIDToDelete);
                    ps.execute();

                } else {
                    Alert alert3 = new Alert(Alert.AlertType.ERROR);
                    alert3.setTitle("Cannot Delete");
                    alert3.setContentText("Customer has appointments scheduled. Please delete customer's appointments before deleting customer.");
                    alert3.showAndWait();
                }


            }
        }
    }
}
