package basepack.roompack;

import basepack.Room;

<<<<<<< Updated upstream
public class Executive extends Room {
=======
import java.io.Serializable;

/**
 * The room type Executive, extending from the abstract class: Room.
 */
public class Executive extends Room implements Serializable {
>>>>>>> Stashed changes
    private double ePrice;

    public Executive(String roomName) {
        super(roomName);
        ePrice = super.getBasePrice() * 1.35;
    }

    public double getEPrice(){
        return ePrice;
    }
}
