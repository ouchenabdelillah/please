package hotel.databaseOperation;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import hotel.classes.Room;
import hotel.classes.RoomFare;

public class RoomDb {
    private DatabaseHelper dbHelper = new DatabaseHelper();

    public void insertRoom(Room room) {
        String insertQuery = "INSERT INTO room (room_no, bed_number, tv, wifi, gizer, phone, room_class, meal_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        if (dbHelper.executeUpdate(insertQuery, new Object[]{
                room.getRoomNo(),
                room.getBedNumber(),
                room.isHasTV(),
                room.isHasWIFI(),
                room.isHasGizer(),
                room.isHasPhone(),
                room.getRoomClass().getRoomType(),
                room.getMealId()})) {
            JOptionPane.showMessageDialog(null, "Successfully inserted a new room");
        } else {
            JOptionPane.showMessageDialog(null, "Insert Room Failed");
        }
    }

    public ResultSet getRooms() {
        return dbHelper.executeQuery("SELECT * FROM room");
    }

    public int getNoOfRooms() {
        ResultSet result = dbHelper.executeQuery("SELECT COUNT(room_no) AS noRoom FROM room");
        try {
            if (result.next()) {
                return result.getInt("noRoom");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString() + "\nError counting rooms");
        }
        return -1;
    }

    public ResultSet getAllRoomNames() {
        return dbHelper.executeQuery("SELECT room_no FROM room");
    }

    public void deleteRoom(int roomId) {
        String deleteQuery = "DELETE FROM room WHERE room_id = ?";
        if (dbHelper.executeUpdate(deleteQuery, new Object[]{roomId})) {
            JOptionPane.showMessageDialog(null, "Room deleted successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Delete Room Failed");
        }
    }

    public void updateRoom(Room room) {
        String updateQuery = "UPDATE room SET room_no = ?, bed_number = ?, tv = ?, wifi = ?, gizer = ?, phone = ?, room_class = ?, meal_id = ? "
                + "WHERE room_id = ?";
        if (dbHelper.executeUpdate(updateQuery, new Object[]{
                room.getRoomNo(),
                room.getBedNumber(),
                room.isHasTV(),
                room.isHasWIFI(),
                room.isHasGizer(),
                room.isHasPhone(),
                room.getRoomClass().getRoomType(),
                room.getMealId(),
                room.getRoomId()})) {
            JOptionPane.showMessageDialog(null, "Successfully updated room");
        } else {
            JOptionPane.showMessageDialog(null, "Update Room Failed");
        }
    }

    public void insertRoomType(RoomFare roomType) {
        String insertQuery = "INSERT INTO roomType (type, price) VALUES (?, ?)";
        if (dbHelper.executeUpdate(insertQuery, new Object[]{
                roomType.getRoomType(),
                roomType.getPricePerDay()})) {
            JOptionPane.showMessageDialog(null, "Successfully inserted a new room type");
        } else {
            JOptionPane.showMessageDialog(null, "Insert Room Type Failed");
        }
    }

    public ResultSet getRoomType() {
        return dbHelper.executeQuery("SELECT * FROM roomType");
    }

    public void updateRoomType(RoomFare roomType) {
        String updateQuery = "UPDATE roomType SET price = ? WHERE type = ?";
        if (dbHelper.executeUpdate(updateQuery, new Object[]{
                roomType.getPricePerDay(),
                roomType.getRoomType()})) {
            JOptionPane.showMessageDialog(null, "Successfully updated room type");
        } else {
            JOptionPane.showMessageDialog(null, "Update Room Type Failed");
        }
    }

    public void flushAll(PreparedStatement statement, ResultSet result) {
        dbHelper.flushAll(statement, result);
    }
}
