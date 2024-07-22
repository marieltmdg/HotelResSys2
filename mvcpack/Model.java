package mvcpack;
import basepack.*;
import java.util.ArrayList;

/**
 * The Model class is responsible for managing the overall operations involving hotels, rooms, and reservations.
 */
public class Model {
    public Utility utility;
    private ArrayList<Hotel> hotelList;
    private int selectedHotelIndex;

    /**
     * Constructs a Driver instance and initializes the necessary objects.
     */
    public Model() {
        this.utility = new Utility();
        this.hotelList = new ArrayList<Hotel>();
    }

    public int addHotel(String name, int standardRoomCount, int deluxeRoomCount, int execRoomCount) {
        int cont = 1;

        //loop to check hotel name similarity
        for(int i = 0; i < hotelList.size() && cont == 1; i++){
            if (name.equals(hotelList.get(i).getHotelName())){
                cont = 0;
            } 
        }

        int totalRoomCount = standardRoomCount + deluxeRoomCount + execRoomCount;
        if(totalRoomCount <= 0){
            cont = -1;
        }
            

        if(standardRoomCount + deluxeRoomCount + execRoomCount > 50){
            cont = -2;
        }

        //valid case        
        if (cont == 1){
            Hotel hotel = new Hotel(name);
            hotelList.add(hotel);
            for(int i = 0; i < standardRoomCount; i++){
                hotel.addStandardRoom();
            } 
            for(int i = 0; i < deluxeRoomCount; i++){
                hotel.addDeluxeRoom();
            }
            for(int i = 0; i < execRoomCount; i++){
                hotel.addExecRoom();
            }
        }
        
        return cont;
    }


    public void addRoom(int roomType, int hotelIndex) {
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

    public String removeRoom(int index){
        if (index >= 0 && index < hotelList.get(selectedHotelIndex).getRoomCount())
            return hotelList.get(selectedHotelIndex).removeRoom(index);
        else return "Input out of bounds";
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

    public boolean renameHotel(String newName){
        for (Hotel h : hotelList){
            if (h.getHotelName().equals(newName)){
                return false;
            }
        }

        hotelList.get(selectedHotelIndex).setHotelName(newName);
        return true;
    }

    public String updatePrice(double price){
        if(price >= 150){
            return hotelList.get(selectedHotelIndex).updatePrice(price);
        } else return "New price must be equal to or greater than 150";
    }

    public String updateDatePrice(int index, double multiplier){
        if (index >= 0 && index < 30){
            if(multiplier >= 50 && multiplier <= 150) {
                hotelList.get(selectedHotelIndex).updateDatePrice(index, multiplier);
                return "Date price modifier for Day " + (index + 1) + " to " + multiplier + "% successful";
            } else return "New % modifier must range 50% to 150%";
        } else return "Please select valid dates to modify price (1-30)";
    }

    public String removeReservation(int roomIndex, int resIndex){
        return hotelList.get(selectedHotelIndex).removeHotelReservation(roomIndex, resIndex);
    }

    public void removeHotel(){
        hotelList.remove(hotelList.get(selectedHotelIndex));
    }

    public void addReservation(String name, int checkIn, int checkOut, int roomIndex){
        hotelList.get(selectedHotelIndex).addHotelReservation(name, checkIn, checkOut, roomIndex);
    }

    public int getPosNumValue(String x){
        int temp = -1;
        try {
            temp = Integer.parseInt(x);
            if (temp >= 0){
            }
        } catch (Exception e) {
            return -1;
        }
        return temp;
    }

    public double getPosDoubleValue(String x){
        double temp = -1;
        try {
            temp = Double.parseDouble(x);
        } catch (Exception e) {
            return -1;
        }
        return temp;
    }

    public String[] getHotelListNames(){
        String[] names = new String[hotelList.size()];

        for(int i = 0; i <hotelList.size();i++){
            names[i] = hotelList.get(i).getHotelName();
        }

        return names;
    }

    public String[] getRoomListNames(){
        String[] names = new String[hotelList.get(selectedHotelIndex).getRoomList().size()];

        for(int i = 0; i <hotelList.get(selectedHotelIndex).getRoomList().size();i++){
            names[i] = hotelList.get(selectedHotelIndex).getRoomName(i);
        }

        return names;
    }

    public String[][] getReservationListDetailed(){
        return hotelList.get(selectedHotelIndex).getReservationListDetailed();
    }

    public int getReservationCount(){
        return hotelList.get(selectedHotelIndex).getReservationCount();
    }

    public String getCurrentHotel(){
        return this.hotelList.get(selectedHotelIndex).getHotelName();
    }

    public int getCurrentHotelIndex(){
        return selectedHotelIndex;
    }

    public int getRoomCount(){
        return this.hotelList.get(selectedHotelIndex).getRoomCount();
    }

    public int getStandardRoomCount(){
        return this.hotelList.get(selectedHotelIndex).getRoomTypeCount("Standard");
    }

    public int getDeluxeRoomCount(){
        return this.hotelList.get(selectedHotelIndex).getRoomTypeCount("Deluxe");
    }

    public int getExecRoomCount(){
        return this.hotelList.get(selectedHotelIndex).getRoomTypeCount("Executive");
    }

    public double getBasePrice(){
        return this.hotelList.get(selectedHotelIndex).getBasePrice();
    }

    public double[] getDatePrice(){
        return this.hotelList.get(selectedHotelIndex).getAllDatePrice();
    }

    public double getEarnings(){
        return this.hotelList.get(selectedHotelIndex).getHotelIncome();
    }

    public String[] getPriceBreakdown(int promoValidity, int checkIn, int checkOut, int roomIndex){
        return hotelList.get(selectedHotelIndex).getRoom(roomIndex).priceBreakdown(promoValidity, checkIn, checkOut);
    }


   
}

