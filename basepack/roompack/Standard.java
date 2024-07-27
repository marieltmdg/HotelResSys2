package basepack.roompack;

import basepack.Room;

import java.io.Serializable;

/**
 * The room type Standard, extending from the abstract class: Room.
 */
public class Standard extends Room implements Serializable {

    /**
     * Constructs a Standard instance with the specified room name.
     *
     * @param roomName The name of the room.
     */
    public Standard(String roomName) {
        super(roomName);
    }

    /**
     * The getPriceAfterMultiplier() method gets the price per night, given the
     * price multiplier.
     *
     * @return The price after multiplier of a standard room.
     */
    public double getPriceAfterMultiplier(int date){
        //date - 1 since its an index
        return this.getBasePrice() * (super.getDatePricePercent(date-1)/100);
    }
}
