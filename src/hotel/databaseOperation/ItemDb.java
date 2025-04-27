package hotel.databaseOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import hotel.classes.Item;

/**
 * @author Faysal Ahmed
 */
public class ItemDb {
    Connection conn = DataBaseConnection.connectTODB();
    PreparedStatement statement = null;
    ResultSet result = null;

    // Method to insert an item
    public void insertItem(Item item) {
        try {
            String insertItem = "INSERT INTO item(name, description, price) VALUES (?, ?, ?)";
            statement = conn.prepareStatement(insertItem);
            statement.setString(1, item.getItemName());
            statement.setString(2, item.getDescription());
            statement.setDouble(3, item.getPrice());

            statement.execute();
            JOptionPane.showMessageDialog(null, "Successfully inserted a new Item");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nInsertQuery of Item Failed");
        } finally {
            flushStatmentOnly();
        }
    }

    // Method to update an item
    public void updateItem(Item item) {
        try {
            String updateItem = "UPDATE item SET name = ?, description = ?, price = ? WHERE item_id = ?";
            statement = conn.prepareStatement(updateItem);
            statement.setString(1, item.getItemName());
            statement.setString(2, item.getDescription());
            statement.setDouble(3, item.getPrice());
            statement.setInt(4, item.getItemId());

            statement.execute();
            JOptionPane.showMessageDialog(null, "Successfully updated Item");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nUpdate Item Failed");
        } finally {
            flushStatmentOnly();
        }
    }

    // Method to get all items
    public ResultSet getItems() {
        try {
            String query = "SELECT * FROM item";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError retrieving all items");
        }

        return result;
    }

    // Method to delete an item
    public void deleteItem(int itemId) {
        try {
            String deleteQuery = "DELETE FROM item WHERE item_id = ?";
            statement = conn.prepareStatement(deleteQuery);
            statement.setInt(1, itemId);
            statement.execute();
            JOptionPane.showMessageDialog(null, "Deleted item");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nDelete Item Failed");
        } finally {
            flushStatmentOnly();
        }
    }

    // Close all database resources
    public void flushAll() {
        try {
            if (statement != null) statement.close();
            if (result != null) result.close();
        } catch (SQLException ex) {
            System.err.print(ex.toString() + " >> CLOSING DB");
        }
    }

    // Close only the statement (not result)
    private void flushStatmentOnly() {
        try {
            if (statement != null) statement.close();
        } catch (SQLException ex) {
            System.err.print(ex.toString() + " >> CLOSING DB");
        }
    }
}
