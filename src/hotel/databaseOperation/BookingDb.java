package hotel.databaseOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import hotel.classes.Booking;
import hotel.classes.Order;

public class BookingDb {

    Connection conn;
    PreparedStatement statement = null;
    ResultSet result = null;

    public BookingDb() {
        conn = DataBaseConnection.connectTODB();
    }

    public void insertBooking(Booking booking) {
        for (int i = 0; i < booking.getRooms().size(); i++) {
            try {
                String insertQuery = "INSERT INTO booking "
                        + "(customer_id, booking_room, guests, check_in, check_out, booking_type, has_checked_out) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";

                statement = conn.prepareStatement(insertQuery);
                statement.setInt(1, booking.getCustomer().getCustomerId());
                statement.setString(2, booking.getRooms().get(i).getRoomNo());
                statement.setInt(3, booking.getPerson());
                statement.setLong(4, booking.getCheckInDateTime());
                statement.setLong(5, booking.getCheckOutDateTime());
                statement.setString(6, booking.getBookingType());
                statement.setInt(7, 0); // has_checked_out

                statement.execute();
                JOptionPane.showMessageDialog(null, "Successfully inserted new Booking");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString() + "\nInsert booking failed.");
            } finally {
                flushStatementOnly();
            }
        }
    }

    public ResultSet getBookingInformation() {
        try {
            String query = "SELECT * FROM booking";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError fetching booking information.");
        }
        return result;
    }

    public ResultSet getABooking(int bookingId) {
        try {
            String query = "SELECT * FROM booking WHERE booking_id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, bookingId);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError fetching single booking.");
        }
        return result;
    }

    public ResultSet bookingsReadyForOrder(String roomName) {
        try {
            String query = "SELECT booking_id, booking_room, name FROM booking "
                    + "JOIN userInfo ON booking.customer_id = userInfo.user_id "
                    + "WHERE booking_room LIKE ? AND has_checked_out = 0 "
                    + "ORDER BY booking_id DESC";

            statement = conn.prepareStatement(query);
            statement.setString(1, "%" + roomName + "%");
            result = statement.executeQuery();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError in bookingsReadyForOrder.");
        }
        return result;
    }

    public void updateCheckOut(int bookingId, long checkOutTime) {
        try {
            String updateQuery = "UPDATE booking SET has_checked_out = 1, check_out = ? WHERE booking_id = ?";
            statement = conn.prepareStatement(updateQuery);
            statement.setLong(1, checkOutTime);
            statement.setInt(2, bookingId);
            statement.execute();

            JOptionPane.showMessageDialog(null, "Successfully updated check-out.");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nCheck-out update failed.");
        } finally {
            flushStatementOnly();
        }
    }

    public int getRoomPrice(int bookingId) {
        int price = -1;
        try {
            String query = "SELECT price FROM booking "
                    + "JOIN room ON booking_room = room_no "
                    + "JOIN roomType ON type = room_class "
                    + "WHERE booking_id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, bookingId);
            result = statement.executeQuery();
            if (result.next()) {
                price = result.getInt("price");
            }
            flushAll();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError getting room price.");
        }

        return price;
    }

    public void insertOrder(Order order) {
        try {
            String insertOrder = "INSERT INTO orderItem "
                    + "(booking_id, item_food, price, quantity, total) "
                    + "VALUES (?, ?, ?, ?, ?)";

            statement = conn.prepareStatement(insertOrder);
            statement.setInt(1, order.getBookingId());
            statement.setString(2, order.getFoodItem());
            statement.setDouble(3, order.getPrice());
            statement.setInt(4, order.getQuantity());
            statement.setDouble(5, order.getTotal());

            statement.execute();
            JOptionPane.showMessageDialog(null, "Successfully inserted a new Order");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nOrder insertion failed.");
        } finally {
            flushStatementOnly();
        }
    }

    public ResultSet getAllPaymentInfo(int bookingId) {
        try {
            String query = "SELECT * FROM orderItem WHERE booking_id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, bookingId);
            result = statement.executeQuery();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError getting payment info.");
        }
        return result;
    }

    public void flushAll() {
        try {
            if (statement != null) statement.close();
            if (result != null) result.close();
        } catch (SQLException ex) {
            System.err.print(ex.toString() + " >> Error closing DB resources");
        }
    }

    public void flushStatementOnly() {
        try {
            if (statement != null) statement.close();
        } catch (SQLException ex) {
            System.err.print(ex.toString() + " >> Error closing statement");
        }
    }
}
