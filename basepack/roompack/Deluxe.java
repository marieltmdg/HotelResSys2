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

    public double getPriceAfterMultiplier(int date) {
        return dPrice * (super.getDatePricePercent(date-1) / 100);
    }
}
