package controller;

import DBAccess.DBAppointments;
import DBAccess.DBUsers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginForm implements Initializable {
    @FXML
    private Label loginWelcomeLabel;
    @FXML
    private Label zoneIdLabel;
    @FXML
    private Label userIdLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField idField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginBtn;
    @FXML
    private Button cancelBtn;
    boolean validUser = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        zoneAndLanguage();
        checkUpcomingAppointments();
    }

    public void handleCancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void loginValidate(ActionEvent event) throws IOException, SQLException {
        String user = idField.getText();
        String password = passwordField.getText();
        ObservableList<User> allUsers = DBUsers.getAllUsers();
        boolean validUser = false;

        for (User u : allUsers) {
            if (u.getUserName().equals(user) && u.getUserPassword().equals(password)) {
                validUser = true;
                break;

            } else {
                validUser = false;

            }
        }

        if(validUser) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/SchedulingPage.fxml"));
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Scheduling Page");
            stage.setScene(scene);
            stage.show();
        } else {
            Locale france = new Locale("fr", "FR");

            ResourceBundle rb = ResourceBundle.getBundle("controller/Nat", Locale.FRENCH);

            if (Locale.getDefault().getLanguage().equals("fr")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("Invalid,Login").replaceAll(",", " "));
                alert.setContentText(rb.getString("Invalid,username,or,password.").replaceAll(",", " "));
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Login");
                alert.setContentText("Invalid username and/or password. Please check username and password and try again.");
                alert.showAndWait();
            }
        }

        trackLoginAttempts();

    }

    public void zoneAndLanguage() {
        ZoneId userZone = ZoneId.systemDefault();
        zoneIdLabel.setText("Current Location: " + userZone);
        System.out.println(userZone);

        Locale france = new Locale("fr", "FR");

        ResourceBundle rb = ResourceBundle.getBundle("controller/Nat", Locale.FRENCH);

        if(Locale.getDefault().getLanguage().equals("fr")) {
            Locale.setDefault(france);
            loginWelcomeLabel.setText(rb.getString("Please,enter,your,login,information").replaceAll(",", " "));
            userIdLabel.setText(rb.getString("User,ID").replaceAll(",", " "));
            passwordLabel.setText(rb.getString("Password").replaceAll(",", " "));
            loginBtn.setText(rb.getString("Login").replaceAll(",", " "));
            cancelBtn.setText(rb.getString("Cancel").replaceAll(",", " "));
        }

    }

    public void checkUpcomingAppointments() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime soon = now.plusMinutes(15);
        ObservableList<Appointment> appointmentObservableList = DBAppointments.getAllAppointments();
        ObservableList<User> userObservableList = DBUsers.getAllUsers();
        String userName = idField.getText();
        int idToCheck = 0;
        for (User u : userObservableList) {
            if (u.getUserName().equals(userName)) {
                idToCheck = u.getUserID();
                break;
            }
        }

        for (Appointment a : appointmentObservableList) {
            if (a.getUserId() == idToCheck && a.getStartTime().isBefore(soon)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Soon");
                alert.setContentText("Appointment scheduled within 15 minutes");
                alert.showAndWait();
                return;
            }
        }
    }

    public void trackLoginAttempts() throws IOException {
        LocalDate date = LocalDateTime.now().toLocalDate();
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        String user = idField.getText();
        FileWriter fw = new FileWriter("login_activity.txt", true);
        PrintWriter file = new PrintWriter(fw);
        if (validUser) {
            file.println("User: " + user + " successfully logged in at " + date + timestamp);
        } else {
            file.println("User: " + user + " gave invalid login at " + date + timestamp);
        }

        file.close();
    }


}
