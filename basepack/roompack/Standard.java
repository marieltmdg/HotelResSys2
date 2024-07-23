package basepack.roompack;

import basepack.Room;

public class Standard extends Room {

    public Standard(String roomName) {
        super(roomName);
    }

    public double getPriceAfterMultiplier(int date){
        return this.getBasePrice() * (super.getDatePricePercent(date-1)/100);
    }
}
