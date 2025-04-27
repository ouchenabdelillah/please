package hotel.tableModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import hotel.databaseOperation.DatabaseOperation;
import hotel.databaseOperation.RoomDb;

public class BookingTableModel extends AbstractTableModel {

    private String[] columnNames;
    private Date date;
    private transient Object[][] data;

    public BookingTableModel(long start, long end) {
        iniColNames();
        fetchDataFromDB(start, end);
    }

    private void iniColNames() {
        date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("d");
        int today = (Integer.parseInt(ft.format(date)) - 1) % getMonthLimit(date);
        columnNames = new String[11];
        columnNames[0] = "#";
        for (int i = 1; i < 11; i++) {
            today = today % getMonthLimit(date);
            today++;
            columnNames[i] = String.valueOf(today);
        }
    }

    private int getMonthLimit(Date x) {
        SimpleDateFormat ft = new SimpleDateFormat("M");
        int y = Integer.parseInt(ft.format(x));
        if (y == 2) return 28;
        else if (y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10 || y == 12) return 31;
        else return 30;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void fetchDataFromDB(long start, long end) {
        try {
            RoomDb roomDb = new RoomDb();
            int rows = roomDb.getNoOfRooms();
            initializeData(rows);

            ResultSet roomNames = roomDb.getAllRoomNames();
            populateRoomNames(roomNames, rows, start, end);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "from Booking table model class\n " + ex.toString());
        }
    }

    private void initializeData(int rows) {
        data = new Object[rows][11];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = "";
            }
        }
    }

    private void populateRoomNames(ResultSet roomNames, int rows, long start, long end) throws SQLException {
        for (int i = 0; i < rows; i++) {
            if (roomNames.next()) {
                String roomName = roomNames.getString("room_no");
                data[i][0] = roomName;
                processBookings(start, end, roomName, i);
            }
        }
    }

    private void processBookings(long start, long end, String roomName, int rowIndex) throws SQLException {
        ResultSet result = new DatabaseOperation().getBookingInfo(start, end, roomName);
        while (result.next()) {
            long check_in = Long.parseLong(result.getString("check_in"));
            long check_out = Long.parseLong(result.getString("check_out"));

            if (check_in <= start && (check_out == 0 || check_out <= end)) {
                data[rowIndex][1] = "<<";
            } else if (check_in > start && check_out < end) {
                int checkInDay = getDay(check_in);
                int checkOutDay = getDay(check_out);
                int today = getDay(start);
                data[rowIndex][(checkInDay - today) + 1] = ">";
                data[rowIndex][(checkOutDay - today) + 1] = "<";
            } else if (check_in <= end && (check_out == 0 || check_out > end)) {
                int checkInDay = getDay(check_in);
                int today = getDay(start);
                data[rowIndex][(checkInDay - today) + 1] = ">>";
            }
        }
    }

    private int getDay(long timestamp) {
        return Integer.parseInt(new SimpleDateFormat("d").format(new Date(timestamp * 1000)));
    }
}
