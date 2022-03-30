package controller;

import DBAccess.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;

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

    public void populateAppointmentTableView() throws SQLException {
        ObservableList<Appointment> appointmentList = DBAppointments.getAllAppointments();
        appointmentsTable.setItems(appointmentList);
    }

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

    public void populateTypeCombo() {
        ObservableList<Appointment> appointmentList = DBAppointments.getAllAppointments();
        ObservableList<String> allTypes = FXCollections.observableArrayList();

        for (Appointment a : appointmentList) {
            if (!allTypes.contains(a.getType())) {
                String type = a.getType();
                allTypes.add(type);
            }
        }

        apptTypeCombo.setItems(allTypes);
    }

    public void populateCountryCombo() {
        ObservableList<Country> countryList = DBCountries.getAllCountries();
        ObservableList<String> allCountryNames = FXCollections.observableArrayList();

        for (Country c : countryList) {
            String name = c.getCountryName();
            allCountryNames.add(name);
        }

        countryCombo.setItems(allCountryNames);
    }

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

    public void countryTotal() throws SQLException {

        ObservableList<Country> allCountries = DBCountries.getAllCountries();
        ObservableList<Customer> allCustomers = DBCustomers.getAllCustomers();
        ObservableList<FirstLevelDivision> allDivisions = DBFirstLevelDivision.getFirstLevelDivisions();
        String countryName = countryCombo.getValue();
        int countryID = 0;
        int divisionID = 0;
        int total = 0;

        for (Country c : allCountries) {
            if (c.getCountryName().equals(countryName)) {
                countryID = c.getCountryID();
            }
            for (FirstLevelDivision d : allDivisions) {
                if (d.getCountryID() == countryID) {
                    divisionID = d.getDivisionID();
                }
                for (Customer customer : allCustomers) {
                    if (customer.getDivisionID() == divisionID) {
                        total = total + 1;
                    }
                }
            }

        }


        totalCustLabel.setText(String.valueOf(total));



    }

}
