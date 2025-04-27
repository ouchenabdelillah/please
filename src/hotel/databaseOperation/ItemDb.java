package hotel.databaseOperation;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import hotel.classes.Item;

public class ItemDb {
    private DatabaseHelper dbHelper = new DatabaseHelper();

    public void insertItem(Item item) {
        String insertItem = "INSERT INTO item(name, description, price) VALUES (?, ?, ?)";
        if (dbHelper.executeUpdate(insertItem, new Object[]{
                item.getItemName(),
                item.getDescription(),
                item.getPrice()})) {
            JOptionPane.showMessageDialog(null, "Successfully inserted a new Item");
        } else {
            JOptionPane.showMessageDialog(null, "Insert Item Failed");
        }
    }

    public void updateItem(Item item) {
        String updateItem = "UPDATE item SET name = ?, description = ?, price = ? WHERE item_id = ?";
        if (dbHelper.executeUpdate(updateItem, new Object[]{
                item.getItemName(),
                item.getDescription(),
                item.getPrice(),
                item.getItemId()})) {
            JOptionPane.showMessageDialog(null, "Successfully updated Item");
        } else {
            JOptionPane.showMessageDialog(null, "Update Item Failed");
        }
    }

    public ResultSet getItems() {
        return dbHelper.executeQuery("SELECT * FROM item");
    }

    public void deleteItem(int itemId) {
        String deleteQuery = "DELETE FROM item WHERE item_id = ?";
        if (dbHelper.executeUpdate(deleteQuery, new Object[]{itemId})) {
            JOptionPane.showMessageDialog(null, "Deleted item");
        } else {
            JOptionPane.showMessageDialog(null, "Delete Item Failed");
        }
    }

    public void flushAll(PreparedStatement statement, ResultSet result) {
        dbHelper.flushAll(statement, result);
    }
}
