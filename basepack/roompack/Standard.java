package basepack.roompack;

import basepack.Room;

<<<<<<< Updated upstream
public class Standard extends Room {
=======
import java.io.Serializable;

/**
 * The room type Standard, extending from the abstract class: Room.
 */
public class Standard extends Room implements Serializable {
>>>>>>> Stashed changes

    public Standard(String roomName) {
        super(roomName);
    }
}
