package hotel.databaseOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import hotel.classes.Order;

/**
 *
 * @author Faysal
 */
public class OrderDb {

    // Database connection object
    Connection conn = DataBaseConnection.connectTODB();
    PreparedStatement statement = null;
    ResultSet result = null;

    /**
     * Method to insert a new order into the database.
     * @param order The order object containing the order details.
     */
    public void insertOrder(Order order) {
        try {
            // Parameterized SQL query to prevent SQL injection
            String insertOrder = "INSERT INTO orderItem(booking_id, item_food, price, quantity, total) VALUES (?, ?, ?, ?, ?)";

            // Prepare the statement
            statement = conn.prepareStatement(insertOrder);

            // Set values for the placeholders
            statement.setInt(1, order.getBookingId());
            statement.setString(2, order.getFoodItem());
            statement.setDouble(3, order.getPrice());
            statement.setInt(4, order.getQuantity());
            statement.setDouble(5, order.getTotal());

            // Execute the query
            statement.executeUpdate();

            // Show success message
            JOptionPane.showMessageDialog(null, "Successfully inserted a new order");

        } catch (SQLException ex) {
            // Show error message in case of an exception
            JOptionPane.showMessageDialog(null, ex.toString() + "\n" + "Order Failed");
        } finally {
            // Clean up resources
            flushStatementOnly();
        }
    }

    /**
     * Method to close all database-related resources.
     */
    public void flushAll() {
        try {
            // Close statement and result set if they are open
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        } catch (SQLException ex) {
            // Print error in case of an exception while closing
            System.err.println(ex.toString() + " >> CLOSING DB");
        }
    }

    /**
     * Method to flush only the statement (close it).
     */
    private void flushStatementOnly() {
        try {
            // Close the prepared statement if it is open
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            // Print error in case of an exception while closing
            System.err.println(ex.toString() + " >> CLOSING DB");
        }
    }
}
