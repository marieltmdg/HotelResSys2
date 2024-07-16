package mvcpack;
import basepack.*;
import java.util.ArrayList;

/**
 * The Driver class is responsible for managing the overall operations involving hotels, rooms, and reservations.
 */
public class Model {
    private Utility utility;
    private ArrayList<Hotel> hotelList;
    private int selectedHotelIndex;

    /**
     * Constructs a Driver instance and initializes the necessary objects.
     */
    public Model() {
        this.utility = new Utility();
        this.hotelList = new ArrayList<Hotel>();
    }

    public boolean addHotel(String name, int standardRoomCount, int deluxeRoomCount, int ExecRoomCount) {
        boolean cont = true;

        //loop to check hotel name similarity
        for(int i = 0; i < hotelList.size() && cont; i++){
            if (name.equals(hotelList.get(i).getHotelName())){
                cont = false;
            } 
        }

        //valid case        
        if (cont){
            Hotel hotel = new Hotel(name);
            hotelList.add(hotel);
            for(int i = 0; i < standardRoomCount; i++){
                hotel.addStandardRoom();
            } 
            for(int i = 0; i < deluxeRoomCount; i++){
                hotel.addDeluxeRoom();
            }
            for(int i = 0; i < standardRoomCount; i++){
                hotel.addExecRoom();
            }
        }

        return cont;
    }


    private void addRoom(int roomType, int hotelIndex) {
        System.out.println("Room addition");
         
        switch(roomType){
            case 1: 
                hotelList.get(hotelIndex).addStandardRoom();
                break;
            case 2: 
                hotelList.get(hotelIndex).addDeluxeRoom();
                break;
            case 3: 
                hotelList.get(hotelIndex).addExecRoom();
                break;
            default:
        }
    }

    public String openHotel(int hotelIndex) {
        //checker for invalid hotel indices
        if (hotelIndex >= 0 && hotelIndex < hotelList.size()) {
            //TESTING
            this.selectedHotelIndex = hotelIndex;
            return hotelList.get(hotelIndex).getHotelName();
        } else {
            return "\0";
        }
    }


    public String[] getHotelListNames(){
        String[] names = new String[hotelList.size()];

        for(int i = 0; i <hotelList.size();i++){
            names[i] = hotelList.get(i).getHotelName();
        }

        return names;
    }
}

