package basepack;

import basepack.roompack.*;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The Hotel class represents a hotel with a name, a room count, and a list of rooms.
 */
public class Hotel implements Serializable {
    private String hotelName;
    private int roomCount;
    private ArrayList<Room> roomList;

    /**
     * Constructs a Hotel instance with the specified hotel name.
     * Initializes the room count to 0 and adds an initial room to the room list.
     *
     * @param hotelName The name of the hotel.
     */
    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        this.roomCount = 0;
        this.roomList = new ArrayList<Room>();
    }
    
    /**
     * The method getHotelName() returns the name of a hotel.
     * 
     * @return The method getHotelName() is returning the value of the variable hotelName
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * The method getRoomCount() returns the number of rooms.
     * 
     * @return The method getRoomCount is returning the value of the variable roomCount
     */
    public int getRoomCount() {
        return roomCount;
    }

   /**
    * The method getRoomList() returns the list of the Room objects in the hotel.
    * 
    * @return The list of the Room objects of the hotel
    */
    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    /**
    * The method getRoom() returns an instance of Room object in the roomList.
    * 
    * @param index is the index of the Room in the list
    * @return The specified Room object of the roomList
    */
    public Room getRoom(int index) {
        return roomList.get(index);
    }

    /**
     * Returns the count of rooms of a given type in the hotel.
     *
     * @param  type  the type of room to count (Standard, Deluxe, Executive)
     * @return       the number of rooms of the given type
     */
    public int getRoomTypeCount(String type){
        int total = 0;

        switch(type){
            case "Standard":
                for(Room r : roomList){
                    if (r instanceof Standard){
                        total++;
                    }
                }
                break;
            case "Deluxe":
                for(Room r : roomList){
                    if (r instanceof Deluxe){
                        total++;
                    }
                }
                break;
            case "Executive":
                for(Room r : roomList){
                    if (r instanceof Executive){
                        total++;
                    }
                }
                break;
        }

        return total;
    }

    /**
     * The generateRoomName() method generates a room name based on the room count, with a leading number calculated
     * from the room count divided by 10 plus 1, followed by a hyphen and the room count modulo 10.
     *
     * @param type is the room type of the room.
     * @return The generateRoomName method returns a String with a unique room name.
     */
    private String generateRoomName(String type){
        int leadingNum = (roomCount / 10) + 1;

        switch(type){
            case "Standard":
                return "S" + leadingNum + "-" + roomCount % 10;
            case "Deluxe":
                return "D" + leadingNum + "-" + roomCount % 10;
            case "Executive":
                return "E" + leadingNum + "-" + roomCount % 10;
        }
        return "\0";
    }

    /**
     * The getReservationCount() method calculates the total number of reservations across all rooms in a roomList.
     * 
     * @return The getReservationCount method returns the total number of reservations across all
     * rooms in the roomList.
     */
    public int getReservationCount() {
        int resCount = 0;
        //iterate through each room and count the reservations
        for(int i = 0; i < roomList.size();i++){
            if(!(roomList.get(i).getReservationList().isEmpty())){
                resCount = resCount + roomList.get(i).getReservationList().size();
                } 
            }  
        return resCount;
    }

    /**
     * The method setHotelName() sets a new name for a hotel.
     * 
     * @param newName The parameter newName represents the new name that will be assigned to the hotelName.
     */
    public void setHotelName(String newName) {
        this.hotelName = newName;
    }

    /**
     * The method addStandardRoom() adds a new standard room object, provided the room count is less than 50,
     * and gives it a unique name.
     */
    public void addStandardRoom(){
        String roomName = generateRoomName("Standard");
        Standard newRoom = new Standard(roomName);

        //check if the room size has not yet reached capacity
        if(roomList.size() < 50){
            roomList.add(newRoom);
            roomCount++;
        }
    }

    /**
     * The method addDeluxeRoom() adds a new deluxe room object, provided the room count is less than 50,
     * and gives it a unique name.
     */
    public void addDeluxeRoom(){
        String roomName = generateRoomName("Deluxe");
        Deluxe newRoom = new Deluxe(roomName);

        //check if the room size has not yet reached capacity
        if(roomList.size() < 50){
            roomList.add(newRoom);
            roomCount++;
        }
    }

    /**
     * The method addExecRoom() adds a new executive room object, provided the room count is less than 50,
     * and gives it a unique name.
     */
    public void addExecRoom(){
        String roomName = generateRoomName("Executive");
        Executive newRoom = new Executive(roomName);

        //check if the room size has not yet reached capacity
        if(roomList.size() < 50){
            roomList.add(newRoom);
            roomCount++;
        }
    }

   /**
    * The removeRoom() method removes a room from a list if it exists has no reservations, and the room count
    * is greater than 1.
    * 
    * @param index The index of the room in the roomList that will be removed.
    * @return string result of the removal
    */
    public String removeRoom(int index){
        //check if the room exists
        if (roomList.contains(roomList.get(index))){
            if(roomList.get(index).getReservationList().isEmpty() && roomList.size() > 1){
                roomList.remove(roomList.get(index));
                roomCount--;
                return "Room Removal Successful";
            } else if (roomList.get(index).getReservationList().isEmpty() && roomList.size() == 1) {
                //check if there is only one room left, and do not continue removal
               return "Room " + roomList.get(index).getRoomName() + " not deleted. Hotels must have atleast one room";
            }else 
                //check if there is a reservation, and do not continue removal
                return "Room " + roomList.get(index).getRoomName() + " not deleted. Has a reservation";
        } else return "Room " + roomList.get(index).getRoomName() + " does not exist";
    }

    /**
     * The updatePrice() method sets the base price for all rooms in a list to a specified value and
     * prints a confirmation message.
     * 
     * @param price The price represents the new base price that will be set for all rooms in the roomList.
     * @return String feedback message.
     */
    public String updatePrice(double price){
        //set the base price for all rooms to the new price
        for(int i=0; i<roomList.size(); i++)
            roomList.get(i).setBasePrice(price);

        return "Room price set to "+ price;
    }

    /**
     * The updateDatePrice() method sets the date price modifier for a specific date.
     *
     * @param date is the date the price would be set.
     * @param percent is the percent modifier that will be set.
     */
    public void updateDatePrice(int date, double percent){
        //set the base price for all rooms to the new price
        for(int i=0; i<roomList.size(); i++)
            roomList.get(i).setDatePrice(date, percent);

    }

    /**
     * The addHotelReservation() method adds a reservation for a guest with specified check-in and
     * check-out dates to an available room in a hotel.
     * 
     * @param name The name of the person making the hotel reservation.
     * @param checkIn The checkIn parameter represents the check-in date for the hotel reservation.
     * @param checkOut The checkOut parameter represents the check-out date for a hotel reservation.
     * @param roomIndex The roomIndex parameter represents the index of the room in the roomList.
     * @param breakdown The breakdown parameter represents the list of items that the guest will be charged for.
     * @return String display feedback message.
     */
    public String addHotelReservation(String name, int checkIn, int checkOut, int roomIndex, String[] breakdown){
            //add reservation if there are available rooms
            if(roomList.get(roomIndex).isAvailable(checkIn, checkOut)){
                roomList.get(roomIndex).addReservation(name, checkIn, checkOut, breakdown);
                return "Reservation successful for " + name;
            } else {
                return "Room is not available for selected dates";
            }
    }

    /**
     * The removeHotelReservation() method removes a reservation from a specific room in a hotel.
     * 
     * @param roomIndex The roomIndex parameter represents the index of the room in the roomList.
     * @param resIndex The resIndex parameter represents the index of the reservation to be removed.
     * @return String message if successful or not.
     */
    public String removeHotelReservation(int roomIndex, int resIndex){
        if (roomIndex >= 0 && roomIndex < roomCount)
            return roomList.get(roomIndex).removeReservation(resIndex);
        else return "Room input out of bounds";
    }

    /**
     * The getHotelIncome() method calculates the total income generated from all room reservations
     * in a hotel.
     * 
     * @return String display feedback message.
     */
    public double getHotelIncome(){
        double sum = 0;
        
        //sum all reservation total prices
        for(Room r : roomList){
            sum += r.getTotalReservationPrice();
            
        }

        DecimalFormat df = new DecimalFormat("#0.00");      
        sum = Double.valueOf(df.format(sum));
        
        return sum;
    }

    /**
     * Retrieves the details of the hotel, including the hotel name, room count, and reservation count.
     *
     * @return A string array containing the hotel details:
     *         details[0] - Hotel name
     *         details[1] - Room count
     *         details[2] - Reservation count
     */
    public String[] getHotelDetails() {
        String[] details = new String[3];
        details[0] = this.getHotelName(); // Assuming getHotelName() method exists
        details[1] = String.valueOf(this.getRoomCount()); // Assuming getRoomCount() method exists
        details[2] = String.valueOf(this.getReservationCount()); // Assuming getReservationCount() method exists
        return details;
    }

    /**
     * The method getBasePrice() gets the base price of the hotel.
     *
     * @return The base price of the hotel.
     */
    public double getBasePrice(){
        return roomList.get(0).getBasePrice();
    }

    /**
     * Retrieves the price per type of a room based on its index in the roomList.
     *
     * @param  index  the index of the room in the roomList
     * @return        the price per type of the room, or the base price of the first room if the index is out of bounds or the room is of an unknown type
     */
    public double getPricePerType(int index) {
        if (roomList.get(index) instanceof Standard) {
            return ((Standard) roomList.get(index)).getBasePrice();
        } else if (roomList.get(index) instanceof Deluxe) {
            return ((Deluxe) roomList.get(index)).getDPrice();
        } else if (roomList.get(index) instanceof Executive) {
            return ((Executive) roomList.get(index)).getEPrice();
        }
        return roomList.get(0).getBasePrice();
    }

    /**
     * The method getAllDatePrice() gets the complete date price multiplier per date.
     *
     * @return The list of the price multiplier per date.
     */
    public double[] getAllDatePrice(){
        double ret[] = new double[30];

        for(int i = 0 ; i < 30; i++){
            ret[i] = roomList.get(0).getDatePricePercent(i);
        }

        return ret;
    }

    /**
     * The method getReservationListDetailed() gets reservation details for all rooms.
     *
     * @return The detailed list of the reservation details per room.
     */
    public String[][] getReservationListDetailed(){

        String[][] ret = new String[getRoomCount()][30];

        for(int i = 0 ; i < roomList.size() ; i++)
            for(int j = 0; j < roomList.get(i).getReservationListCount(); j++)
                ret[i][j] = roomList.get(i).getRoomName() +
                        " " + roomList.get(i).getReservationName(j) + " Date:" + roomList.get(i).getResDates(j);

        return ret;
    }

    /**
     * The method getAvailableDatesForRoom() gets the available dates for a room.
     *
     * @param index The index of the room.
     * @return The available dates for the room.
     */
    public String[] getAvailableDatesForRoom(int index){
        return roomList.get(index).printAvailability();
    }

    /**
     * The method checkNumAvailableRooms() iterates through a list of rooms and counts the number of
     * rooms available for a specific date.
     * 
     * @param date The date for which one wants to check the availability of rooms.
     * @return The number of available rooms for a given date.
     */
    public int checkNumAvailableRooms(int date){
        int ctr = 0;

        for(Room r: roomList){
            if(r.isAvailable(date, date+1))
                ctr++;
        }
        return ctr;
    }

    /**
     * The method checkNumBookedRooms() counts the number of rooms that are not available for booking
     * on a specific date.
     * 
     * @param date The date for which you want to check the availability of rooms. 
     * @return The method is returning the number of booked rooms for a specific date.
     */
    public int checkNumBookedRooms(int date){
         int ctr = 0;

        for(Room r: roomList){
            if(!(r.isAvailable(date, date+1)))
                ctr++;
        }
        return ctr;
    }

    /**
     * The method printReservation() prints the guest name, room name, price per night, and stay length
     * 
     * @param roomIndex The index of the room.
     * @param resIndex The index of the reservation.
     * @return The method is returning the guest name, room name, price per night, and stay length
     */
    public String[] printReservation(int roomIndex, int resIndex){
        return roomList.get(roomIndex).printReservation(resIndex);
    }

    /**
     * The method getRoomName() gets the room name, given an index.
     *
     * @param roomIndex The index from which the room name will be retrieved.
     * @return The room name.
     */
    public String getRoomName(int roomIndex){
        return roomList.get(roomIndex).getRoomName();
    }

}


