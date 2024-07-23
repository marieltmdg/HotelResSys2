package basepack.roompack;

import basepack.Room;

/**
 * The room type Deluxe, extending from the abstract class: Room.
 */
public class Deluxe extends Room {
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
     * Gets the price of a deluxe room per night.
     *
     * @return The price of a deluxe room.
     */
    public double getDPrice(){
        return dPrice;
    }

    public double getPriceAfterMultiplier(int date) {
        return dPrice * (super.getDatePricePercent(date-1) / 100);
    }
}
