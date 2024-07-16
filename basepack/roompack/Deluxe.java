package basepack.roompack;

import basepack.Room;

public class Deluxe extends Room {

    public Deluxe(String roomName) {
        super(roomName);
        super.setBasePrice(super.getBasePrice() * 1.2);
    }
    
}
