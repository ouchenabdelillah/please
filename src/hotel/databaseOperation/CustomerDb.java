package hotel.databaseOperation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import hotel.classes.UserInfo;

public class CustomerDb {
    private Connection conn;

    public CustomerDb() {
        conn = DataBaseConnection.connectTODB();
    }

    public void insertCustomer(UserInfo user) {
        String insertQuery = "INSERT INTO userInfo (name, address, phone, type) VALUES (?, ?, ?, ?)";
        DatabaseUtility.executeUpdateQuery(conn, insertQuery, user.getName(), user.getAddress(), user.getPhoneNo(), user.getType());
        JOptionPane.showMessageDialog(null, "Successfully inserted new Customer");
    }

    public void updateCustomer(UserInfo user) {
        String updateQuery = "UPDATE userInfo SET name = ?, address = ?, phone = ?, type = ? WHERE user_id = ?";
        DatabaseUtility.executeUpdateQuery(conn, updateQuery, user.getName(), user.getAddress(), user.getPhoneNo(), user.getType(), user.getCustomerId());
        JOptionPane.showMessageDialog(null, "Successfully updated Customer");
    }

    public void deleteCustomer(int userId) {
        String deleteQuery = "DELETE FROM userInfo WHERE user_id = ?";
        DatabaseUtility.executeUpdateQuery(conn, deleteQuery, userId);
        JOptionPane.showMessageDialog(null, "Deleted user");
    }

    public ResultSet getAllCustomer() {
        String query = "SELECT * FROM userInfo";
        return DatabaseUtility.executeSelectQuery(conn, query);
    }

    public ResultSet searchUser(String user) {
        String query = "SELECT user_id, name, address FROM userInfo WHERE name LIKE ?";
        return DatabaseUtility.executeSelectQuery(conn, query, "%" + user + "%");
    }

    public ResultSet searchAnUser(int id) {
        String query = "SELECT * FROM userInfo WHERE user_id = ?";
        return DatabaseUtility.executeSelectQuery(conn, query, id);
    }

    public int getCustomerId(UserInfo user) {
        String query = "SELECT user_id FROM userInfo WHERE name = ? AND phone = ?";
        ResultSet rs = DatabaseUtility.executeSelectQuery(conn, query, user.getName(), user.getPhoneNo());
        try {
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\n error fetching user id");
        }
        return -1;
    }
}
