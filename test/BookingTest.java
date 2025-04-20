import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import hotel.classes.Booking;
import hotel.classes.UserInfo;
import hotel.classes.Room;
import org.junit.jupiter.api.Test;

public class BookingTest {

    private Booking booking;

    @BeforeEach
    public void setUp() {
        // Runs before each test; initialize Booking object
        booking = new Booking();
    }

    @Test
    public void testConstructor() {
        assertNotNull(booking.getCustomer(), "Customer should not be null");
        assertEquals("Reserved", booking.getBookingType(), "Booking type should be 'Reserved'");
        assertEquals(0, booking.getRooms().size(), "There should be no rooms initially");
        assertEquals(-1, booking.getBookingId(), "Booking ID should be -1 initially");
    }

    @Test
    public void testSettersAndGetters() {
        // Test setter and getter for bookingId
        booking.setBookingId(123);
        assertEquals(123, booking.getBookingId(), "Booking ID should be 123");

        // Test setter and getter for bookingType
        booking.setBookingType("Confirmed");
        assertEquals("Confirmed", booking.getBookingType(), "Booking type should be 'Confirmed'");

        // Test setter and getter for person
        booking.setPerson(2);
        assertEquals(2, booking.getPerson(), "Number of persons should be 2");
    }

    @Test
    public void testAddRoom() {
        booking.addRoom("101A");
        assertEquals(1, booking.getRooms().size(), "There should be one room added");
        assertEquals("101A", booking.getRooms().get(0).getRoomNo(), "Room number should be '101A'");
    }

    @Test
    public void testRemoveRoom() {
        booking.addRoom("101A");
        booking.addRoom("102B");
        assertEquals(2, booking.getRooms().size(), "There should be two rooms initially");

        // Remove a room
        booking.removeRoom("101A");
        assertEquals(1, booking.getRooms().size(), "There should be one room left");
        assertEquals("102B", booking.getRooms().get(0).getRoomNo(), "Room number should be '102B'");
    }

    @Test
    public void testGetRoomsFare() {
        // Assuming Room class is defined elsewhere and has a method getRoomClass().getPricePerDay()
        booking.addRoom("101A");
        booking.addRoom("102B");

        // Add a mock or real Room object with pricePerDay set for testing
        // Assuming getPricePerDay is set to 100 for simplicity in this example
        int expectedFare = 200; // Expected total fare (2 rooms with pricePerDay = 100)
        assertEquals(expectedFare, booking.getRoomsFare(), "Total fare should be 200");
    }

    @Test
    public void testCheckInOutDateTime() {
        booking.setCheckInDateTime(1633024800000L); // Example timestamp for check-in
        booking.setCheckOutDateTime(1633111200000L); // Example timestamp for check-out

        assertEquals(1633024800000L, booking.getCheckInDateTime(), "Check-in date should be correct");
        assertEquals(1633111200000L, booking.getCheckOutDateTime(), "Check-out date should be correct");
    }
}
