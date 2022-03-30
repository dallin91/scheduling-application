package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class allows the user to interact with the database
 *
 * @author Dallin Reeves
 * */
public class DBUtility {

    private static PreparedStatement preparedStatement;


    /**
     * Sets prepared statement
     *
     * @param conn
     * @param sqlStatement
     * */
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        preparedStatement = conn.prepareStatement(sqlStatement);
    }

    /**
     * Returns prepared statement
     * */
    public static PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }
}
