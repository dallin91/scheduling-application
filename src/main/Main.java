package main;

import Database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;

/**
 * This program is an app that will manage customer and appointment information as well as provide useful reports.
 * Any updates made to customers or appointments will be stored on the database for future use.
 *
 * @author Dallin Reeves
 * */
public class Main extends Application {


    /**
     * This method loads the FXML page and sets the scene
     *
     * @param primaryStage
     *
     * */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        primaryStage.setTitle("Please Login");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }

    /**
     * The main method launches the application and establishes a connection with the database.
     *
     * @param args
     *
     * */
    public static void main(String[] args) {

        //test for french
        //Locale.setDefault(new Locale("fr"));

        DBConnection.startConnection();

        launch(args);
        DBConnection.closeConnection();
    }
}
