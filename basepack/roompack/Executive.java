package basepack.roompack;

import basepack.Room;

/**
 * The room type Executive, extending from the abstract class: Room.
 */
public class Executive extends Room {
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
     * Get the price of an executive room.
     *
     * @return The price of a deluxe room.
     */
    public double getEPrice(){
        return ePrice;
    }

    public double getPriceAfterMultiplier(int date) {
        return ePrice * (super.getDatePricePercent(date-1) / 100);
    }
}
