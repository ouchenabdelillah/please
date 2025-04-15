package hotel.classes;

import java.util.ArrayList;

/**
 *
 * @author Faysal Ahmed
 */
public class Booking {
    
       
    private UserInfo customer;
    ArrayList<Room> rooms;
    
    
    
    private int bookingId;
    private long checkInDateTime;
    private long checkOutDateTime;
    private String bookingType;
    private int person;
    
    
    
    
    public Booking()
    {
        customer = new UserInfo();
        rooms = new ArrayList<>();
        bookingId = -1;
        bookingType = "Reserved";
        
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

   
    
    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }
    
    
    
    
    public void addRoom(String roomNo)
    {
        rooms.add(new Room(roomNo));
        
    }
    
    public void removeRoom(String roomNo)
    {
        for(Room a: rooms)
        {
            if(a.getRoomNo().equals(roomNo))
            {
                rooms.remove(a);
            }
        }
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
    
    public int getRoomsFare()
    {
        int total = 0;
        for(Room room:rooms)
        {
            total += room.getRoomClass().getPricePerDay();
        }
        return total;
    }

    public UserInfo getCustomer() {
        return customer;
    }

    public void setCustomer(UserInfo customer) {
        this.customer = customer;
    }

    

    public void setCheckOutDateTime(int checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

    public long getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(long checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }

    public long getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public void setCheckOutDateTime(long checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

 
    
    
    
            
    
}
