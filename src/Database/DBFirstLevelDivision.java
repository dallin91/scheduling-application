package Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBFirstLevelDivision {

    public static ObservableList<FirstLevelDivision> getFirstLevelDivisions() throws SQLException {
        ObservableList<FirstLevelDivision> firstLevelDivisionList = FXCollections.observableArrayList();
        String sql = "SELECT * from first_level_divisions";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            int countryID = rs.getInt("COUNTRY_ID");
            FirstLevelDivision firstLevelDivision = new FirstLevelDivision(divisionID, divisionName, countryID);
            firstLevelDivisionList.add(firstLevelDivision);
        }
        return firstLevelDivisionList;
    }
}
