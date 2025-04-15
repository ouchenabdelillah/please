package hotel.classes;



/**
 *
 * @author Faysal Ahmed
 */
public class ExtraOrders {

    private int orderId;
    private int customerId;
    private String dateTime;
    private int quantity;
    private Item item;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    public int calculateTotal()
    {
       return item.getPrice() * quantity;
    }

    
    
    
    
}
