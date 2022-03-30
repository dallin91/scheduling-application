package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class serves as the way to access the customers table in the database
 *
 * @author Dallin Reeves
 * */
public class DBCustomers {

    /**
     * Returns all customers
     * */
    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> customersList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from customers";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String zipCode = rs.getString("Postal_Code");
                String phoneNumber = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");
                Customer customer = new Customer(customerID, customerName, customerAddress, zipCode, phoneNumber,
                        divisionID);
                customersList.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return customersList;
    }
}
