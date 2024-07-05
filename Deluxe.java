public class Deluxe extends Room {
    private double dPrice;

    public Deluxe(String roomName){
        super(roomName);
        dPrice = super.basePrice * 1.2;
    }
}