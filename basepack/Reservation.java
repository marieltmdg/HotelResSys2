package basepack;

import basepack.roompack.*;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * The Reservation class represents a reservation made by a guest.
 */
public class Reservation implements Serializable {
    private String guestName;
    private String[] breakdown;
    private int checkIn;
    private int checkOut;
    private Room room;
    private double totalPrice;

    /**
     * Constructs a Reservation instance with the specified guest name, check-in and check-out dates, and room.
     *
     * @param guestName The name of the guest.
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     * @param room The room associated with the reservation.
     * @param breakdown The breakdown of the reservation.
     */
    public Reservation(String guestName, int checkIn, int checkOut, Room room, String[] breakdown) {
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.room = room;
        this.totalPrice = 0;
        this.breakdown = breakdown;
    }

    /**
     * The getTotalPrice() method calculates the total price for a stay based on the base price of the room for each
     * night between the check-in and check-out dates.
     * 
     * @return The total price for the room for the duration of the stay.
     */
    public double getTotalPrice(){
        return this.totalPrice;
    }

    /**
     * The getRoom() method returns the room object.
     * 
     * @return The Room object to where the reservation belongs to.
     */
    public Room getRoom(){
        return room;
    }

    /**
     * The getCheckIn() method returns the checkIn value of the reservation.
     * 
     * @return The checkIn int value.
     */
    public int getCheckIn(){
        return checkIn;
    }

    /**
     * The getCheckOut() method returns the checkOut value of the reservation.
     * 
     * @return The checkOut int value.
     */
    public int getCheckOut(){
        return checkOut;
    }

    /**
     * The getGuestName() method returns the guestName value of the reservation.
     * 
     * @return The guestName String value.
     */
    public String getGuestName(){
        return guestName;
    }

    /**
     * The printReservation() method prints out details of a guest's reservation including guest name,
     * stay length, room details, and total price.
     *
     * @return An array of strings representing the reservation details.
     */
    public String[] printReservation(){
        int length = this.checkOut - this.checkIn;


        String[] resInfo = new String[4];
        resInfo[0] = ("Guest Name: " + this.guestName);
        resInfo[1] = ("Stay length: " + length + " days (" + this.checkIn + " - " + this.checkOut + ")");
        resInfo[2] = ("Room Details: " + room.getRoomName());

        double totalPrice = this.getTotalPrice() ;
        DecimalFormat df = new DecimalFormat("#,##0.00");    
        String totalPriceString = df.format(totalPrice);  
        resInfo[3] = ("Total Price: " + totalPriceString);

        return resInfo;
    }

    /**
     * The setTotalPrice() method sets the total price of the reservation after applying any discounts.
     *
     * @param promoValidity The validity period of the promotion.
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     */
    public void setTotalPrice(int promoValidity, int checkIn, int checkOut){
        this.totalPrice = room.getTotalPriceAfterDiscount(promoValidity, checkIn, checkOut);
    }

    /**
     * The getReservationBreakdown() method returns the breakdown of the reservation.
     *
     * @return An array of strings representing the breakdown of the reservation.
     */
    public String[] getReservationBreakdown(){
        return this.breakdown;
    }
}