package basepack.roompack;

import basepack.Room;

import java.io.Serializable;

/**
 * The room type Executive, extending from the abstract class: Room.
 */
public class Executive extends Room implements Serializable {
    private double ePrice;

    /**
     * Constructs an Executive instance with the specified room name.
     * Initializes the dPrice to 135% of the base price.
     *
     * @param roomName The name of the room.
     */
    public Executive(String roomName) {
        super(roomName);
        ePrice = super.getBasePrice() * 1.35;
    }

    /**
     * The getEPrice() method gets the price of an executive room.
     *
     * @return The price of an executive room.
     */
    public double getEPrice(){
        return ePrice;
    }

    /**
     * The getPriceAfterMultiplier() method gets the price per night, given the
     * price multiplier.
     *
     * @return The price after multiplier of an executive room.
     */
    public double getPriceAfterMultiplier(int date) {
        return ePrice * (super.getDatePricePercent(date-1) / 100);
    }
}
