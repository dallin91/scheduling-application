package DBAccess;

import Database.DBConnection;
import Database.DBUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * This class is the way to access the appointments table in the database
 *
 * @author Dallin Reeves
 * */
public class DBAppointments {

    /**
     * Returns all appointments
     * */
    public static ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId =rs.getInt("Contact_ID");
                String type = rs.getString("Type");
                LocalDateTime startTime = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endTime = rs.getTimestamp("End").toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");

                Appointment appointment = new Appointment(appointmentId, title, description, location, contactId, type,
                        startTime, endTime, customerId, userId);
                appointmentsList.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentsList;
    }

    /**
     * Returns all appointments that are scheduled to start in the next week
     * */
    public static ObservableList<Appointment> getWeekAppointments() throws SQLException {
        ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList();

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime nextWeek = today.plusDays(7);

        String sqlStatement = "SELECT * FROM appointments WHERE Start > ? AND Start < ?";

        DBUtility.setPreparedStatement(DBConnection.getConnection(), sqlStatement);
        PreparedStatement ps = DBUtility.getPreparedStatement();

        ps.setDate(1, java.sql.Date.valueOf(today.toLocalDate()));
        ps.setDate(2, java.sql.Date.valueOf(nextWeek.toLocalDate()));

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while(rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId =rs.getInt("Contact_ID");
                String type = rs.getString("Type");
                LocalDateTime startTime = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endTime = rs.getTimestamp("End").toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");

                Appointment appointment = new Appointment(appointmentId, title, description, location, contactId, type,
                        startTime, endTime, customerId, userId);
                appointmentObservableList.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentObservableList;
    }

    /**
     * Returns all appointments that are scheduled to start in the next month
     * */
    public static ObservableList<Appointment> getMonthAppointments() throws SQLException {
        ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList();

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime nextMonth = today.plusDays(30);

        String sqlStatement = "SELECT * FROM appointments WHERE Start > ? AND Start < ?";

        DBUtility.setPreparedStatement(DBConnection.getConnection(), sqlStatement);
        PreparedStatement ps = DBUtility.getPreparedStatement();

        ps.setDate(1, java.sql.Date.valueOf(today.toLocalDate()));
        ps.setDate(2, java.sql.Date.valueOf(nextMonth.toLocalDate()));

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while(rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId =rs.getInt("Contact_ID");
                String type = rs.getString("Type");
                LocalDateTime startTime = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endTime = rs.getTimestamp("End").toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");

                Appointment appointment = new Appointment(appointmentId, title, description, location, contactId, type,
                        startTime, endTime, customerId, userId);
                appointmentObservableList.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentObservableList;
    }
}
