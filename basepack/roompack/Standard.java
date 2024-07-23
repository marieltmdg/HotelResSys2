package basepack.roompack;

import basepack.Room;

/**
 * The room type Standard, extending from the abstract class: Room.
 */
public class Standard extends Room {

    /**
     * Constructs a Standard instance with the specified room name.
     *
     * @param roomName The name of the room.
     */
    public Standard(String roomName) {
        super(roomName);
    }

    public double getPriceAfterMultiplier(int date){
        return this.getBasePrice() * (super.getDatePricePercent(date-1)/100);
    }
}
