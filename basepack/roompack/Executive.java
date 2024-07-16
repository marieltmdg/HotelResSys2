package basepack.roompack;

import basepack.Room;

public class Executive extends Room {

    public Executive(String roomName) {
        super(roomName);
        super.setBasePrice(super.getBasePrice() * 1.35);
    }
}
