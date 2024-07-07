package basepack.roompack;

import basepack.Room;

public class Standard extends Room {
    private double sPrice;

    public Standard(String roomName) {
        super(roomName);
        sPrice = super.getBasePrice();
    }

    public double getStandardPrice(){
        return sPrice;
    }
}
