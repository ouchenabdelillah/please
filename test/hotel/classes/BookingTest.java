package hotel.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    private Booking booking;

    @BeforeEach
    void setUp() {
        booking = new Booking();
    }

    @Test
    void testDefaultValues() {
        assertEquals("Reserved", booking.getBookingType());
        assertEquals(-1, booking.getBookingId());
        assertNotNull(booking.getRooms());
        assertNotNull(booking.getCustomer());
    }

    @Test
    void testSetAndGetBookingId() {
        booking.setBookingId(42);
        assertEquals(42, booking.getBookingId());
    }

    @Test
    void testSetAndGetBookingType() {
        booking.setBookingType("Confirmed");
        assertEquals("Confirmed", booking.getBookingType());
    }

    @Test
    void testAddRoom() {
        booking.addRoom("101");
        assertEquals(1, booking.getRooms().size());
        assertEquals("101", booking.getRooms().get(0).getRoomNo());
    }

    @Test
    void testRemoveRoom() {
        booking.addRoom("101");
        booking.addRoom("102");
        booking.removeRoom("101");
        assertEquals(1, booking.getRooms().size());
        assertEquals("102", booking.getRooms().get(0).getRoomNo());
    }

    @Test
    void testSetAndGetPerson() {
        booking.setPerson(3);
        assertEquals(3, booking.getPerson());
    }

    @Test
    void testSetAndGetCustomer() {
        UserInfo user = new UserInfo();
        booking.setCustomer(user);
        assertEquals(user, booking.getCustomer());
    }

    @Test
    void testSetAndGetCheckInCheckOutDates() {
        booking.setCheckInDateTime(123456789L);
        booking.setCheckOutDateTime(987654321L);
        assertEquals(123456789L, booking.getCheckInDateTime());
        assertEquals(987654321L, booking.getCheckOutDateTime());
    }

    @Test
    void testGetRoomsFare() {
        booking.addRoom("101"); // Price = 100
        booking.addRoom("102"); // Price = 100
        assertEquals(200, booking.getRoomsFare());
    }
}
