package hotel.databaseOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import hotel.classes.UserInfo;

public class DatabaseOperation {
    private Connection conn;

    public DatabaseOperation() {
        conn = DataBaseConnection.connectTODB();
    }

    public void insertCustomer(UserInfo user) throws SQLException {
        String insertQuery = "INSERT INTO userInfo (name, address, phone, type) VALUES (?, ?, ?, ?)";
        DatabaseUtility.executeUpdate(conn, insertQuery, user.getName(), user.getAddress(), user.getPhoneNo(), user.getType());
        JOptionPane.showMessageDialog(null, "Successfully inserted new Customer");
    }

    public void updateCustomer(UserInfo user) {
        String updateQuery = "UPDATE userInfo SET name = ?, address = ?, phone = ?, type = ? WHERE user_id = ?";
        DatabaseUtility.executeUpdate(conn, updateQuery, user.getName(), user.getAddress(), user.getPhoneNo(), user.getType(), user.getCustomerId());
        JOptionPane.showMessageDialog(null, "Successfully updated Customer");
    }

    public void deleteCustomer(int userId) throws SQLException {
        String deleteQuery = "DELETE FROM userInfo WHERE user_id = ?";
        DatabaseUtility.executeUpdate(conn, deleteQuery, userId);
        JOptionPane.showMessageDialog(null, "Deleted user");
    }

    public ResultSet getAllCustomer() {
        String query = "SELECT * FROM userInfo";
        return DatabaseUtility.executeQuery(conn, query);
    }

    public ResultSet searchUser(String user) {
        String query = "SELECT user_id, name, address FROM userInfo WHERE name LIKE ?";
        return DatabaseUtility.executeQuery(conn, query, "%" + user + "%");
    }

    public ResultSet searchAnUser(int id) {
        String query = "SELECT * FROM userInfo WHERE user_id = ?";
        return DatabaseUtility.executeQuery(conn, query, id);
    }

    public ResultSet getAvailableRooms(long checkInTime) {
        String query = "SELECT room_no FROM room LEFT OUTER JOIN booking ON room.room_no = booking.booking_room WHERE booking.booking_room IS NULL OR ? < booking.check_in OR booking.check_out < ? GROUP BY room.room_no ORDER BY room_no";
        return DatabaseUtility.executeQuery(conn, query, checkInTime, checkInTime);
    }

    public ResultSet getBookingInfo(long startDate, long endDate, String roomNo) {
        String query = "SELECT * FROM booking WHERE booking_room = ? AND ("
                + "(check_in <= ? AND (check_out = 0 OR check_out <= ?)) OR "
                + "(check_in > ? AND check_out < ?) OR "
                + "(check_in <= ? AND (check_out = 0 OR check_out > ?)))";
        return DatabaseUtility.executeQuery(conn, query, roomNo, startDate, endDate, startDate, endDate, endDate, endDate);
    }

    public int getCustomerId(UserInfo user) {
        String query = "SELECT user_id FROM userInfo WHERE name = ? AND phone = ?";
        ResultSet rs = DatabaseUtility.executeQuery(conn, query, user.getName(), user.getPhoneNo());
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
