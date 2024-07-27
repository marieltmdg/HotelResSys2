package basepack.roompack;

import basepack.Room;

<<<<<<< Updated upstream
public class Deluxe extends Room {
=======
import java.io.Serializable;

/**
 * The room type Deluxe, extending from the abstract class: Room.
 */
public class Deluxe extends Room implements Serializable {
>>>>>>> Stashed changes
    private double dPrice;

    public Deluxe(String roomName) {
        super(roomName);
        dPrice = super.getBasePrice() * 1.2;
    }
    
    public double getDPrice(){
        return dPrice;
    }
}
