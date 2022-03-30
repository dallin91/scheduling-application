package controller;

import DBAccess.DBAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;

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

        System.out.println("Hello there");


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

}
