public class Executive extends Room {
    private double ePrice;
    
    public Executive(String roomName){
        super(roomName);
        ePrice = super.basePrice * 1.35;
    }
}
