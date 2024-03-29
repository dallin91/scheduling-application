package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * This class establishes the connection with the database
 *
 * @author Dallin Reeves
 * */
public class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//DESKTOP-BT4131D:3306/";
    //add dbName later
    private static final String dbName = "client_schedule";

    private static final String jdbcURL = protocol + vendorName + ipAddress + dbName + "?connectionTimeZone=SERVER";

    private static final String MYSQLJBCDriver = "com.mysql.cj.jdbc.Driver";

    //add username later
    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";
    private static Connection conn = null;

    /**
     * Starts connection with database
     * */
    public static Connection startConnection() {
        System.out.println("Connecting...");
        try {
            Class.forName(MYSQLJBCDriver);
            conn = DriverManager.getConnection(jdbcURL, username, password);

            System.out.println("Connection successful");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Returns connection with database
     * */
    public static Connection getConnection() {
        return conn;
    }

    /**
     * Closes connection with database
     * */
    public static void closeConnection() {
        try {
            conn.close();
        }
        catch (Exception e) {
            //do nothing
        }
    }
}
