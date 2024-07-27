package basepack.roompack;

import basepack.Room;

import java.io.Serializable;

/**
 * The room type Deluxe, extending from the abstract class: Room.
 */
public class Deluxe extends Room implements Serializable {
    private double dPrice;

    /**
     * Constructs a Deluxe instance with the specified room name.
     * Initializes the dPrice to 120% of the base price.
     *
     * @param roomName The name of the room.
     */
    public Deluxe(String roomName) {
        super(roomName);
        dPrice = super.getBasePrice() * 1.2;
    }

    /**
     * The getDPrice() method gets the price of a deluxe room per night.
     *
     * @return The price of a deluxe room.
     */
    public double getDPrice(){
        return dPrice;
    }

    /**
     * The getPriceAfterMultiplier() method gets the price per night, given the
     * price multiplier.
     *
     * @return The price after multiplier of a deluxe room.
     */
    public double getPriceAfterMultiplier(int date) {
        return dPrice * (super.getDatePricePercent(date-1) / 100);
    }
}
