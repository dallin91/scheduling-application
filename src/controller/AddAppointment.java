package controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
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
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * This class is the controller for AddAppointment.fxml
 *
 * @author Dallin Reeves
 * */
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

    /**
     * Initializes the form.
     *
     * @param resourceBundle
     * @param url
     * */
    @Override
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

    /**
     * This will save what is in the fields as a new appointment and return to the main scheduling page.
     *
     * @param actionEvent
     * */
    public void toSchedulingSave(ActionEvent actionEvent) throws IOException, SQLException {
        addAppointment();

        Parent root = FXMLLoader.load(getClass().getResource("/view/SchedulingPage.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Scheduling Page");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This will add the appointment to the database as well as conduct validation checks to make sure it is a valid appointment.
     *
     * */
    public void addAppointment() throws SQLException {
        try {
            if (!apptTitle.getText().equals("") && !apptDescription.getText().equals("") && !apptLocation.getText().equals("")
            && !apptType.getText().equals("") && (apptStartDate.getValue() != null) && (apptEndDate.getValue() != null)
                    && !custID.getValue().equals("") && !userID.getValue().equals("") && !contactID.getValue().equals("")
                    && !apptStartTime.getValue().equals("") && !apptEndTime.getValue().equals("")) {



                String newTitle = apptTitle.getText();
                String newDescription = apptDescription.getText();
                String newLocation = apptLocation.getText();
                String newType = apptType.getText();
                int newCustID = custID.getValue();
                int newUserID = userID.getValue();

                //setting contact ID value
                String newContactName = contactID.getValue();
                int newContactID = 0;
                ObservableList<Contact> contactObservableList = DBContacts.getAllContacts();
                for (Contact c : contactObservableList) {
                    if (newContactName.equals(c.contactName)) {
                        newContactID = c.getContactID();
                    }
                }

                //setting Start and End values
                LocalDate startLocalDate = apptStartDate.getValue();
                LocalDate endLocalDate = apptEndDate.getValue();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime startLocalTime = LocalTime.parse(apptStartTime.getValue(), dateTimeFormatter);
                LocalTime endLocalTime = LocalTime.parse(apptEndTime.getValue(), dateTimeFormatter);
                LocalDateTime newStart = LocalDateTime.of(startLocalDate, startLocalTime);
                LocalDateTime newEnd = LocalDateTime.of(endLocalDate, endLocalTime);

                LocalDateTime newCreateDate = LocalDateTime.now();
                String newCreateBy = "admin";
                Timestamp newLastUpdate = Timestamp.valueOf(LocalDateTime.now());
                String newLastUpdatedBy = "admin";

                //set business hours EST
                LocalTime businessOpen = LocalTime.of(8, 0);
                LocalTime businessClose = LocalTime.of(22, 0);
                int dayOpen = DayOfWeek.MONDAY.getValue();
                int dayClose = DayOfWeek.FRIDAY.getValue();

                //converting local time to EST and back
                //start
                ZonedDateTime localStartToZone = ZonedDateTime.of(newStart, ZoneId.systemDefault());
                ZonedDateTime zoneStartToEST = localStartToZone.withZoneSameInstant(ZoneId.of("America/New_York"));
                LocalTime checkStartTime = zoneStartToEST.toLocalTime();
                DayOfWeek checkStartDay = zoneStartToEST.toLocalDate().getDayOfWeek();
                int checkStartDayInt = checkStartDay.getValue();
                //end
                ZonedDateTime localEndToZone = ZonedDateTime.of(newEnd, ZoneId.systemDefault());
                ZonedDateTime zoneEndToEST = localEndToZone.withZoneSameInstant(ZoneId.of("America/New_York"));
                LocalTime checkEndTime = zoneEndToEST.toLocalTime();
                DayOfWeek checkEndDay = zoneEndToEST.toLocalDate().getDayOfWeek();
                int checkEndDayInt = checkEndDay.getValue();

                //check if appointment to add is out of business hours
                if (checkStartTime.isBefore(businessOpen) || checkStartTime.isAfter(businessClose) ||
                checkEndTime.isBefore(businessOpen) || checkEndTime.isAfter(businessClose)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Start/End time error");
                    alert.setContentText("Business hours are 8:00AM - 10:00PM EST Monday-Friday. Please schedule during this time.");
                    alert.showAndWait();
                    return;
                }
                if (checkStartDayInt < dayOpen || checkStartDayInt > dayClose || checkEndDayInt < dayOpen ||
                        checkEndDayInt > dayClose) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Start/End Day Error");
                    alert.setContentText("Business hours are 8:00AM - 10:00PM EST Monday-Friday. Please schedule during this time.");
                    alert.showAndWait();
                    return;
                }

                //check for overlapping customer appointments
                ObservableList<Appointment> appointmentObservableList = DBAppointments.getAllAppointments();
                for (Appointment a : appointmentObservableList) {
                    LocalDateTime startTimes = a.getStartTime();
                    LocalDateTime endTimes = a.getEndTime();
                    int customerIDs = a.getCustomerId();

                    if (newCustID == customerIDs && (newStart.isEqual(startTimes) || newStart.isAfter(startTimes)) &&
                            (newStart.isEqual(endTimes) || newStart.isBefore(endTimes))) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Appointment Overlap");
                        alert.setContentText("Customer already has appointment scheduled during this time. Appointment not added.");
                        alert.showAndWait();
                        return;
                    }
                    if (newCustID == customerIDs && (newEnd.isEqual(startTimes) || newEnd.isAfter(startTimes)) &&
                            (newEnd.isEqual(endTimes) || newEnd.isBefore(endTimes))) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Appointment Overlap");
                        alert.setContentText("Customer already has appointment scheduled during this time. Appointment not added.");
                        alert.showAndWait();
                        return;
                    }

                }

                String sqlStatement = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, " +
                        "Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

                DBUtility.setPreparedStatement(DBConnection.getConnection(), sqlStatement);
                PreparedStatement ps = DBUtility.getPreparedStatement();
                ps.setString(1, newTitle);
                ps.setString(2, newDescription);
                ps.setString(3, newLocation);
                ps.setString(4, newType);
                ps.setTimestamp(5, Timestamp.valueOf(newStart));
                ps.setTimestamp(6, Timestamp.valueOf(newEnd));
                ps.setTimestamp(7, Timestamp.valueOf(newCreateDate));
                ps.setString(8, newCreateBy);
                ps.setTimestamp(9, newLastUpdate);
                ps.setString(10, newLastUpdatedBy);
                ps.setInt(11, newCustID);
                ps.setInt(12, newUserID);
                ps.setInt(13, newContactID);
                ps.execute();


            }
            else {
                System.out.println("You cannot add an appointment");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This will return to the scheduling page without saving any info
     *
     * @param actionEvent
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
