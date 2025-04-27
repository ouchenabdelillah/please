package hotel.databaseOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseUtility {

    // Executes a SELECT query and returns the result set
    public static ResultSet executeSelectQuery(Connection conn, String query, Object... params) {
        // Using try-with-resources to ensure the statement and result set are closed automatically
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            // Set the parameters dynamically
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            // Execute the query and return the result set
            return statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n error executing SELECT query");
            return null;
        }
    }


    // Executes an UPDATE or DELETE query
    public static void executeUpdateQuery(Connection conn, String query, Object... params) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            statement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n error executing UPDATE/DELETE query");
        } finally {
            flushStatementOnly(statement);
        }
    }

    // Close statement and result set
    public static void flushAll(PreparedStatement statement, ResultSet result) {
        try {
            if (statement != null) statement.close();
            if (result != null) result.close();
        } catch (SQLException ex) {
            System.err.print(ex.toString() + " >> CLOSING DB");
        }
    }

    // Close statement only
    public static void flushStatementOnly(PreparedStatement statement) {
        try {
            if (statement != null) statement.close();
        } catch (SQLException ex) {
            System.err.print(ex.toString() + " >> CLOSING DB");
        }
    }
}
