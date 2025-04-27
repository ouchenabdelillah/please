import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import hotel.classes.Booking;
import hotel.databaseOperation.db.BookingDb;
import hotel.databaseOperation.db.CustomerDb;
import hotel.databaseOperation.db.DatabaseOperation;
import hotel.classes.UserInfo;
import hotel.ui.ControlPanel;

import javax.swing.text.JTextComponent;
import javax.swing.DefaultListModel;

import static org.mockito.Mockito.*; // for mock, when, verify, any

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerPanelTest {
    @Test
    public void testIsAnyRequiredFieldEmpty() {
        ControlPanel panel = new ControlPanel();

        // Test with all fields empty
        assertTrue(panel.isAnyRequiredFieldEmpty());

        // Test with some fields filled
        panel.tfName.setText("John Doe");
        assertTrue(panel.isAnyRequiredFieldEmpty());

        // Test with all required fields filled
        panel.tfAddress.setText("123 Main St");
        panel.tfContact.setText("5551234567");
        panel.tfGuestNo.setText("2");
        panel.tfRooms.setText("101");
        assertFalse(panel.isAnyRequiredFieldEmpty());
    }
    @Test
    public void testSearchCustomerHelper() {
        ControlPanel panel = new ControlPanel();

        // Test that the combo box model is properly initialized
        assertNotNull(panel.comboUsers.getModel());

        // Test that key listener is attached
        JTextComponent editor = (JTextComponent) panel.comboUsers.getEditor().getEditorComponent();
        assertTrue(editor.getKeyListeners().length > 0);
    }

    @Test
    public void testUserComboFill() throws SQLException {
        ControlPanel panel = new ControlPanel();
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("name")).thenReturn("John Doe");
        when(mockResultSet.getString("address")).thenReturn("123 Main St");
        when(mockResultSet.getString("user_id")).thenReturn("1");

        panel.userComboFill(mockResultSet);

        assertEquals(1, panel.customerList.size());
        assertEquals("John Doe, 123 Main St,1", panel.customerList.get(0));
    }
    @Test
    public void testBookingObjectCreation_NewCustomer() {
        ControlPanel panel = new ControlPanel();
        panel.tfName.setText("John Doe");
        panel.tfAddress.setText("123 Main St");
        panel.tfContact.setText("5551234567");
        panel.tfGuestNo.setText("2");
        panel.tfRooms.setText("101");
        panel.dateCheckIn.setDate(new Date());
        panel.comboReservationType.setSelectedItem("On Spot");
        panel.existingCustomer = false;

        // Mock database operations
        BookingDb mockBookingDb = mock(BookingDb.class);
        CustomerDb mockCustomerDb = mock(CustomerDb.class);
        DatabaseOperation mockDb = mock(DatabaseOperation.class);

        panel.bookingdb = mockBookingDb;
        panel.customerdb = mockCustomerDb;
        panel.db = mockDb;

        when(mockDb.getCustomerId(any(UserInfo.class))).thenReturn(1);

        panel.bookingObjectCreation();

        assertNotNull(panel.booking);
        assertEquals(2, panel.booking.getPerson());
        assertEquals("On Spot", panel.booking.getBookingType());
        assertEquals(1, panel.booking.getCustomer().getCustomerId());

        verify(mockCustomerDb).insertCustomer(any(UserInfo.class));
        verify(mockBookingDb).insertBooking(any(Booking.class));
    }

    @Test
    public void testRoomsToRoomObjectList() {
        ControlPanel panel = new ControlPanel();
        panel.booking = new Booking();

        // Test single room
        panel.roomsToRoomObjectList("101");
        assertEquals(1, panel.booking.getRooms().size());

        // Test multiple rooms
        panel.booking = new Booking();
        panel.roomsToRoomObjectList("101,102,103");
        assertEquals(3, panel.booking.getRooms().size());
    }
    @Test
    public void testRoomSelectionAction() {
        ControlPanel panel = new ControlPanel();

        // Setup mock list model
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("101");
        model.addElement("102");
        panel.roomsList.setModel(model);

        // Select rooms programmatically
        panel.roomsList.setSelectedIndices(new int[]{0, 1});

        // Trigger the button action
        panel.btn_room_upActionPerformed(null);

        assertEquals("101,102", panel.tfRooms.getText());
    }
    @Test
    public void testClearAllFields() {
        ControlPanel panel = new ControlPanel();

        // Populate fields
        panel.tfName.setText("John Doe");
        panel.tfAddress.setText("123 Main St");
        panel.tfContact.setText("5551234567");
        panel.tfGuestNo.setText("2");
        panel.tfRooms.setText("101");
        panel.dateCheckIn.setDate(new Date());
        panel.dateCheckOut.setDate(new Date());
        panel.comboReservationType.setSelectedIndex(1);
        panel.existingCustomer = true;

        panel.clearAllFields();

        assertTrue(panel.tfName.getText().isEmpty());
        assertTrue(panel.tfAddress.getText().isEmpty());
        assertTrue(panel.tfContact.getText().isEmpty());
        assertTrue(panel.tfGuestNo.getText().isEmpty());
        assertTrue(panel.tfRooms.getText().isEmpty());
        assertNotNull(panel.dateCheckIn.getDate());
        assertNull(panel.dateCheckOut.getDate());
        assertEquals(0, panel.comboReservationType.getSelectedIndex());
        assertFalse(panel.existingCustomer);
    }
}