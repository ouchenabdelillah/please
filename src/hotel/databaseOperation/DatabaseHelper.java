package hotel.databaseOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseHelper {
    private Connection conn = DataBaseConnection.connectTODB();

    /**
     * Helper method to execute a prepared statement and handle exceptions.
     */
    public boolean executeUpdate(String query, Object[] params) {
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
            return false;
        }
    }

    /**
     * Method to execute a query and return the ResultSet.
     */
    public ResultSet executeQuery(String query) {
        ResultSet result = null;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError executing query");
        }
        return result;
    }

    /**
     * Method to close all database resources.
     */
    public void flushAll(PreparedStatement statement, ResultSet result) {
        try {
            if (statement != null) statement.close();
            if (result != null) result.close();
        } catch (SQLException ex) {
            System.err.print(ex.toString() + " >> CLOSING DB");
        }
    }

    /**
     * Method to close only the statement (not the result set).
     */
    public void closeStatementOnly(PreparedStatement statement) {
        try {
            if (statement != null) statement.close();
        } catch (SQLException ex) {
            System.err.print(ex.toString() + " >> CLOSING DB");
        }
    }
}
