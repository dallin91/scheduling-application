package controller;

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
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        zoneAndLanguage();
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Login");
            alert.setContentText("Invalid username and/or password. Please check username and password and try again.");
            alert.showAndWait();
        }

    }

    public void zoneAndLanguage() {
        ZoneId userZone = ZoneId.systemDefault();
        zoneIdLabel.setText("Current Location: " + userZone);
        System.out.println(userZone);
/*
        Locale france = new Locale("fr", "FR");

        ResourceBundle rb = ResourceBundle.getBundle("controller/LanguageFile_fr.properties", Locale.getDefault());

        if(Locale.getDefault().getLanguage().equals("fr")) {
            Locale.setDefault(france);
            loginWelcomeLabel.setText(rb.getString("Please,enter,your,login,information").replaceAll(",", " "));
            userIdLabel.setText(rb.getString("User,ID").replaceAll(",", " "));
            passwordLabel.setText(rb.getString("Password").replaceAll(",", " "));
            loginBtn.setText(rb.getString("Login").replaceAll(",", " "));
            cancelBtn.setText(rb.getString("Cancel").replaceAll(",", " "));
        }
        */
    }
}
