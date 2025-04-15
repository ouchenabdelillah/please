package hotel.classes;


/**
 *
 * @author Faysal Ahmed
 */
public class Room {
    private int roomId;
    private String roomNo;
    private int bedNumber;
    
    private boolean hasTV;
    private boolean hasWIFI;
    private boolean hasGizer;
    private boolean hasPhone;
    
    
    private RoomFare roomClass;
    
    
    public Room(String room)
    {
        roomNo = room;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String room) {
        this.roomNo = room;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public boolean isHasTV() {
        return hasTV;
    }

    public void setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
    }

    public boolean isHasWIFI() {
        return hasWIFI;
    }

    public void setHasWIFI(boolean hasWIFI) {
        this.hasWIFI = hasWIFI;
    }

    public boolean isHasGizer() {
        return hasGizer;
    }

    public void setHasGizer(boolean hasGizer) {
        this.hasGizer = hasGizer;
    }

    public boolean isHasPhone() {
        return hasPhone;
    }

    public void setHasPhone(boolean hasPhone) {
        this.hasPhone = hasPhone;
    }


    public RoomFare getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomFare roomClass) {
        this.roomClass = roomClass;
    }

  
    
    
    
    
}
