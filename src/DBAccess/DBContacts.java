package DBAccess;

import Database.DBConnection;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContacts {

    public static ObservableList<Contact> getAllContacts() {
        ObservableList<Contact> contactsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from contacts";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Contact_Email");
                Contact contact = new Contact(contactID, contactName, contactEmail);
                contactsList.add(contact);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactsList;
    }
}
