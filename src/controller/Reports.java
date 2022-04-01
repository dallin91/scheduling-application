package controller;

import DBAccess.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;

/**
 * This is the controller for Reports.fxml
 *
 * @author Dallin Reeves
 * */
public class Reports {

    @FXML
    private Label totalApptsLabel;
    @FXML
    private ComboBox<String> apptTypeCombo;
    @FXML
    private ComboBox<String> apptMonthCombo;
    @FXML
    private ComboBox<Integer> contactIDCombo;
    @FXML
    private ComboBox<String> countryCombo;
    @FXML
    private Label totalCustLabel;
    @FXML
    private TableView<Appointment> appointmentsTable;
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
    private TableColumn<Appointment, LocalDateTime> apptStart;
    @FXML
    private TableColumn<Appointment, LocalDateTime> apptEnd;
    @FXML
    private TableColumn<Appointment, Integer> apptCustID;
    @FXML
    private Button exitBtn;


    /**
     * This initializes the form and sets up the tableview.
     *
     * This also contains two lambda expressions.
     * The first lambda expression simplifies the code to set items for the contact combo box.
     * The second lambda expression simplifies the code to set items for the appointment type combo box.
     * */
    public void initialize() throws SQLException {


        populateAppointmentTableView();
        populateMonthCombo();
        populateCountryCombo();

        ObservableList<Contact> allContacts = DBContacts.getAllContacts();
        ObservableList<Integer> contactIDs = FXCollections.observableArrayList();

        //first lambda expression
        allContacts.forEach(contact -> contactIDs.add(contact.getContactID()));
        contactIDCombo.setItems(contactIDs);

        ObservableList<Appointment> allAppointments = DBAppointments.getAllAppointments();
        ObservableList<String> allTypes = FXCollections.observableArrayList();

        //second lambda expression
        allAppointments.forEach(appointment -> allTypes.add(appointment.getType()));
        apptTypeCombo.setItems(allTypes);



        apptID.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptType.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStart.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        apptEnd.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        apptCustID.setCellValueFactory(new PropertyValueFactory<>("customerId"));

    }

    /**
     * This will populate the appointment tableview with all appointments
     * */
    public void populateAppointmentTableView() throws SQLException {
        ObservableList<Appointment> appointmentList = DBAppointments.getAllAppointments();
        appointmentsTable.setItems(appointmentList);
    }

    /**
     * This will populate the month combo box with each month that has at least one appointment scheduled
     * */
    public void populateMonthCombo() {
        ObservableList<Appointment> allAppointments = DBAppointments.getAllAppointments();
        ObservableList<String> allMonths = FXCollections.observableArrayList();

        for (Appointment a : allAppointments) {
            if (!allMonths.contains(String.valueOf(a.getStartTime().getMonth()))) {
                String month = String.valueOf(a.getStartTime().getMonth());
                allMonths.add(month);
            }
        }

        apptMonthCombo.setItems(allMonths);

    }

    /**
     * This will populate the country combo box with all countries
     * */
    public void populateCountryCombo() {
        ObservableList<Country> countryList = DBCountries.getAllCountries();
        ObservableList<String> allCountryNames = FXCollections.observableArrayList();

        for (Country c : countryList) {
            String name = c.getCountryName();
            allCountryNames.add(name);
        }

        countryCombo.setItems(allCountryNames);
    }

    /**
     * This defines the action of clicking the "Generate Total" button. It takes the month and type selected and
     * will display the total number of appointments that fit those criteria
     * */
    public void customerMonthClick() {

        ObservableList<Appointment> allAppointments = DBAppointments.getAllAppointments();
        String month = apptMonthCombo.getValue();
        String type = apptTypeCombo.getValue();
        int total = 0;

        for (Appointment a : allAppointments) {
            if (String.valueOf(a.getStartTime().getMonth()).equals(month) && a.getType().equals(type)) {
                total = total + 1;
            }
        }

        totalApptsLabel.setText(String.valueOf(total));
    }

    /**
     * This defines the action of clicking the "Go" button. It will take the contact ID from the box and display
     * only appointments for that contact in the tableview below.
     * */
    public void appointmentContactView() {

        int contactID = contactIDCombo.getValue();
        ObservableList<Appointment> allAppointments = DBAppointments.getAllAppointments();
        ObservableList<Appointment> contactAppointments = FXCollections.observableArrayList();

        for (Appointment a : allAppointments) {
            if (a.getContactId() == contactID) {
                contactAppointments.add(a);
            }
        }

        appointmentsTable.setItems(contactAppointments);
        appointmentsTable.refresh();

    }

    /**
     * This will display the total number of customers for the country selected in the country combo box
     * */
    public void countryTotal() throws SQLException {

        ObservableList<Country> allCountries = DBCountries.getAllCountries();
        ObservableList<Customer> allCustomers = DBCustomers.getAllCustomers();
        ObservableList<FirstLevelDivision> allDivisions = DBFirstLevelDivision.getFirstLevelDivisions();
        ObservableList<Integer> divisionsPresent = FXCollections.observableArrayList();
        ObservableList<Integer> countriesPresent = FXCollections.observableArrayList();
        ObservableList<String> countryNamesPresent = FXCollections.observableArrayList();
        String countryName = countryCombo.getValue();
        int countryID = 0;
        int divisionID = 0;
        int total = 0;

        //adds each division ID to list
        for (Customer customer : allCustomers) {
            divisionsPresent.add(customer.getDivisionID());
        }
        //associates each division with its country
        for (FirstLevelDivision d : allDivisions) {
            if (divisionsPresent.contains(d.getDivisionID())) {
                countriesPresent.add(d.getCountryID());
            }
        }
        //associates each country name
        for (Country country : allCountries) {
            countryNamesPresent.add(country.getCountryName());
        }

        for (Country c : allCountries) {
            if (c.getCountryName().equals(countryName)) {
                for (Integer p : countriesPresent) {
                    if (p == c.getCountryID()) {
                        total = total + 1;
                    }
                }
            }
        }

        totalCustLabel.setText(String.valueOf(total));
    }

    /**
     * Returns to the scheduling page
     *
     * @param actionEvent
     * */
    public void toSchedulingExit(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/SchedulingPage.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Scheduling Page");
        stage.setScene(scene);
        stage.show();
    }

}
