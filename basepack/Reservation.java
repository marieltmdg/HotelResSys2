package basepack;

import basepack.roompack.*;

import java.io.Serializable;

/**
 * The Reservation class represents a reservation made by a guest.
 */
public class Reservation implements Serializable {
    private String guestName;
    private int checkIn;
    private int checkOut;
    private Room room;

    /**
     * Constructs a Reservation instance with the specified guest name, check-in and check-out dates, and room.
     *
     * @param guestName The name of the guest.
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     * @param room The room associated with the reservation.
     */
    public Reservation(String guestName, int checkIn, int checkOut, Room room) {
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.room = room;
    }

    /**
     * The getTotalPrice() method calculates the total price for a stay based on the base price of the room for each
     * night between the check-in and check-out dates.
     * 
     * @return The total price for the room for the duration of the stay.
     */
    public double getTotalPrice(){
        double total = 0.0;
        
        if (this.room instanceof Standard){
            for(int i=checkIn; i<checkOut; i++)
                total += ((Standard) room).getBasePrice() * room.getDatePricePercent(i);
        }
        else if (this.room instanceof Deluxe){
            for(int i=checkIn; i<checkOut; i++)
                total += ((Deluxe)room).getDPrice() * room.getDatePricePercent(i);
        }
        else if (this.room instanceof Executive){
            for(int i=checkIn; i<checkOut; i++)
                total += ((Executive) room).getEPrice() * room.getDatePricePercent(i);
        }
        return total;
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
    public void printReservation(){
        int length = this.checkOut - this.checkIn;

        System.out.println("Reservation Details:\n");
        System.out.println("Guest Name: " + this.guestName);
        System.out.println("Stay length: " + length + " days (" + this.checkIn + " - " + this.checkOut + ")");
        System.out.println("Room Details: " + room.getRoomName());
        System.out.println("Total Price: " + this.getTotalPrice());
        System.out.println("Breakdown of Price: " + room.getBasePrice() + "/day for " + (checkOut-checkIn) + " day/s");

    }
}