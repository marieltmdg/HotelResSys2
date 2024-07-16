package basepack.roompack;

import basepack.Room;

public class Deluxe extends Room {
    private double dPrice;

    public Deluxe(String roomName) {
        super(roomName);
        dPrice = super.getBasePrice() * 1.2;
    }
    
    public double getDPrice(){
        return dPrice;
    }
}
