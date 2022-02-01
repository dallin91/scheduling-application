package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
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
        if (user.equals("test") && password.equals("test")) {
            //transition to new scene

            Parent root = FXMLLoader.load(getClass().getResource("/view/SchedulingPage.fxml"));
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Scheduling Page");
            stage.setScene(scene);
            stage.show();
        }
        else {
            //add an error message
            System.out.println("Imposter detected. Prepare to be annihilated");
        }
    }

    public void zoneAndLanguage() {
        ZoneId userZone = ZoneId.systemDefault();
        zoneIdLabel.setText("Current Location: " + userZone);
        System.out.println(userZone);
    }
}
