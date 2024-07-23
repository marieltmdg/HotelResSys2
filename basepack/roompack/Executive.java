package basepack.roompack;

import basepack.Room;

public class Executive extends Room {
    private double ePrice;

    public Executive(String roomName) {
        super(roomName);
        ePrice = super.getBasePrice() * 1.35;
    }

    public double getEPrice(){
        return ePrice;
    }

    public double getPriceAfterMultiplier(int date) {
        return ePrice * (super.getDatePricePercent(date-1) / 100);
    }
}
