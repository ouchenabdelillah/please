package hotel.databaseOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import hotel.classes.Room;
import hotel.classes.RoomFare;

/**
 * @author Faysal Ahmed
 */
public class RoomDb {
    Connection conn = DataBaseConnection.connectTODB();
    PreparedStatement statement = null;
    ResultSet result = null;
    private static final String DEBUG_PREFIX = ">>>>>>>>>> ";

    public void insertRoom(Room room) {
        try {
            String insertQuery = "INSERT INTO room (room_no, bed_number, tv, wifi, gizer, phone, room_class, meal_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            statement = conn.prepareStatement(insertQuery);
            statement.setString(1, room.getRoomNo());
            statement.setInt(2, room.getBedNumber());
            statement.setBoolean(3, room.isHasTV());
            statement.setBoolean(4, room.isHasWIFI());
            statement.setBoolean(5, room.isHasGizer());
            statement.setBoolean(6, room.isHasPhone());
            statement.setString(7, room.getRoomClass().getRoomType());
            statement.setInt(8, room.getMealId()); // Assuming Room has a getMealId() method

            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Successfully inserted a new room");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nInsertQuery of Room Class Failed");
        } finally {
            flushStatementOnly();
        }
    }

    public ResultSet getRooms() {
        try {
            String query = "SELECT * FROM room";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError returning all room DB operations");
        }

        return result;
    }

    public int getNoOfRooms() {
        int rooms = -1;
        try {
            String query = "SELECT COUNT(room_no) AS noRoom FROM room";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
            while (result.next()) {
                rooms = result.getInt("noRoom");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError counting room DB operations");
        }

        return rooms;
    }

    public ResultSet getAllRoomNames() {
        try {
            String query = "SELECT room_no FROM room";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError returning all room numbers");
        }

        return result;
    }

    public void deleteRoom(int roomId) {
        try {
            String deleteQuery = "DELETE FROM room WHERE room_id = ?";
            statement = conn.prepareStatement(deleteQuery);
            statement.setInt(1, roomId);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Room deleted successfully");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nDelete query failed");
        } finally {
            flushStatementOnly();
        }
    }

    public void updateRoom(Room room) {
        try {
            String updateQuery = "UPDATE room SET room_no = ?, bed_number = ?, tv = ?, wifi = ?, gizer = ?, phone = ?, room_class = ?, meal_id = ? "
                    + "WHERE room_id = ?";

            statement = conn.prepareStatement(updateQuery);
            statement.setString(1, room.getRoomNo());
            statement.setInt(2, room.getBedNumber());
            statement.setBoolean(3, room.isHasTV());
            statement.setBoolean(4, room.isHasWIFI());
            statement.setBoolean(5, room.isHasGizer());
            statement.setBoolean(6, room.isHasPhone());
            statement.setString(7, room.getRoomClass().getRoomType());
            statement.setInt(8, room.getMealId());  // Assuming Room has a getMealId() method
            statement.setInt(9, room.getRoomId()); // Assuming Room has a getRoomId() method

            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Successfully updated room");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nUpdate query failed");
        } finally {
            flushStatementOnly();
        }
    }

    public String boolToString(boolean value) {
        return value ? "true" : "false";
    }

    public void insertRoomType(RoomFare roomType) {
        try {
            String insertRoomTypeQuery = "INSERT INTO roomType (type, price) VALUES (?, ?)";

            statement = conn.prepareStatement(insertRoomTypeQuery);
            statement.setString(1, roomType.getRoomType());
            statement.setDouble(2, roomType.getPricePerDay());

            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Successfully inserted a new room type");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nInsert query failed");
        } finally {
            flushStatementOnly();
        }
    }

    public ResultSet getRoomType() {
        try {
            String query = "SELECT * FROM roomType";
            statement = conn.prepareStatement(query);
            result = statement.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError returning room types from DB");
        }

        return result;
    }

    public void updateRoomType(RoomFare roomType) {
        try {
            String updateRoomTypeQuery = "UPDATE roomType SET price = ? WHERE type = ?";

            statement = conn.prepareStatement(updateRoomTypeQuery);
            statement.setDouble(1, roomType.getPricePerDay());
            statement.setString(2, roomType.getRoomType());

            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Successfully updated room type");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nUpdate query failed");
        } finally {
            flushStatementOnly();
        }
    }

    public void flushAll() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        } catch (SQLException ex) {
            System.err.print(ex.toString() + " >> Closing DB resources");
        }
    }

    private void flushStatementOnly() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            System.err.print(ex.toString() + " >> Closing DB resources");
        }
    }
}
