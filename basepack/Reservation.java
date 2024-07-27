package basepack;

import basepack.roompack.*;

import java.io.Serializable;

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
     */
    public String[] printReservation(){
        int length = this.checkOut - this.checkIn;

        System.out.println("Reservation Details:\n");
        System.out.println("Guest Name: " + this.guestName);
        System.out.println("Stay length: " + length + " days (" + this.checkIn + " - " + this.checkOut + ")");
        System.out.println("Room Details: " + room.getRoomName());
        System.out.println("Total Price: " + this.getTotalPrice());
        System.out.println("Breakdown of Price: " + room.getBasePrice() + "/day for " + (checkOut-checkIn) + " day/s");

        String[] resInfo = new String[4];
        resInfo[0] = ("Guest Name: " + this.guestName);
        resInfo[1] = ("Stay length: " + length + " days (" + this.checkIn + " - " + this.checkOut + ")");
        resInfo[2] = ("Room Details: " + room.getRoomName());
        resInfo[3] = ("Total Price: " + this.getTotalPrice());

        return resInfo;
    }

    public void setTotalPrice(int promoValidity, int checkIn, int checkOut){
        this.totalPrice = room.getTotalPriceAfterDiscount(promoValidity, checkIn, checkOut);
    }

    public String[] getReservationBreakdown(){
        return this.breakdown;
    }

}