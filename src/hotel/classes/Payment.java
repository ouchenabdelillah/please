package hotel.classes;

import java.util.ArrayList;

import I3.Classes.Booking;
import I3.Classes.ExtraOrders;

/**
 *
 * @author Faysal Ahmed
 */
public class Payment {
    
    private Booking booking;
    ArrayList<ExtraOrders> orders;
    int totalRentPrice;
    int daysStayed;
    private String paymentDate;
    private String paymentMethod;
    
    private boolean hasDiscount;
    private float discount;
    
    int totalBill;
    
    public Payment(Booking b)
    {
        booking = b;
        
        
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String date) {
        this.paymentDate = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String method) {
        this.paymentMethod = method;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
    
    
    public int calculateTotalBill()
    {
        int orderTotal = 0;
        
        for(ExtraOrders order: orders)
        {
            orderTotal += order.getQuantity() * order.getItem().getPrice();
        }
        totalBill = orderTotal+ totalRentPrice;
        
        return totalBill;
    }
    
    
    
    
    
}
