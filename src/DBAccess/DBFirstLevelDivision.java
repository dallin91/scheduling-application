package DBAccess;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class serves as the way to access the first level divisions table in the database
 *
 * @author Dallin Reeves
 * */
public class DBFirstLevelDivision {

    /**
     * Returns all first level divisions
     * */
    public static ObservableList<FirstLevelDivision> getFirstLevelDivisions() throws SQLException {
        ObservableList<FirstLevelDivision> firstLevelDivisionList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from first_level_divisions";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryID = rs.getInt("COUNTRY_ID");
                FirstLevelDivision firstLevelDivision = new FirstLevelDivision(divisionID, divisionName, countryID);
                firstLevelDivisionList.add(firstLevelDivision);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return firstLevelDivisionList;
    }
}
