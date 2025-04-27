package hotel.databaseOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import hotel.classes.Food;

/**
 *
 * @author Faysal Ahmed
 */
public class FoodDb {

    Connection conn = DataBaseConnection.connectTODB();
    PreparedStatement statement = null;
    ResultSet result = null;

    public void insertFood(Food food) {
        try {
            String insertFood = "INSERT INTO food(name, price) VALUES (?, ?)";
            statement = conn.prepareStatement(insertFood);
            statement.setString(1, food.getName());
            statement.setDouble(2, food.getPrice());

            statement.execute();

            JOptionPane.showMessageDialog(null, "Successfully inserted a new Food Type");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n" + "InsertQuery of Food Failed");
        } finally {
            flushStatmentOnly();
        }
    }

    public ResultSet getFoods() {
        try {
            String query = "SELECT * FROM food";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError retrieving all food DB Operation");
        }

        return result;
    }

    public void updateFood(Food food) {
        try {
            String updateFood = "UPDATE food SET name = ?, price = ? WHERE food_id = ?";
            statement = conn.prepareStatement(updateFood);
            statement.setString(1, food.getName());
            statement.setDouble(2, food.getPrice());
            statement.setInt(3, food.getFoodId());

            statement.execute();

            JOptionPane.showMessageDialog(null, "Successfully updated Food");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nUpdateFood of Food Failed");
        } finally {
            flushStatmentOnly();
        }
    }

    public void deleteFood(int foodId) {
        try {
            String deleteQuery = "DELETE FROM food WHERE food_id = ?";
            statement = conn.prepareStatement(deleteQuery);
            statement.setInt(1, foodId);
            statement.execute();
            JOptionPane.showMessageDialog(null, "Deleted food");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nDelete query Food Failed");
        } finally {
            flushStatmentOnly();
        }
    }

    public void flushAll() {
        try {
            if (statement != null) statement.close();
            if (result != null) result.close();
        } catch (SQLException ex) {
            System.err.print(ex.toString() + " >> CLOSING DB");
        }
    }

    private void flushStatmentOnly() {
        try {
            if (statement != null) statement.close();
        } catch (SQLException ex) {
            System.err.print(ex.toString() + " >> CLOSING DB");
        }
    }
}
