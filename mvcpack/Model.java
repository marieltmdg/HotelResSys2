package mvcpack;
import basepack.*;
import basepack.roompack.*;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * The Model class is responsible for managing the overall operations involving hotels, rooms, and reservations.
 */
@SuppressWarnings("ALL")
public class Model {
    public Utility utility;
    private ArrayList<Hotel> hotelList;
    private int selectedHotelIndex;
    private String savesDirPath = "saves" + File.separator;
    private ArrayList<Manager> managerList;
    private Map<String, Long> lastModifiedMap;
    private boolean managerPresence;

    /**
     * Constructs a Driver instance and initializes the necessary objects.
     */
    public Model() {
        this.utility = new Utility();
        this.hotelList = new ArrayList<Hotel>();
        this.managerList = new ArrayList<Manager>();
        this.lastModifiedMap = new HashMap<>();
        this.managerPresence = false;
    }


    /**
     * The addHotel() method adds a new hotel to the Model with the specified name and room counts.
     * 
     * @param name The name of the hotel to be added.
     * @param standardRoomCount The number of standard rooms to be added to the hotel.
     * @param deluxeRoomCount The number of deluxe rooms to be added to the hotel.
     * @param execRoomCount The number of executive rooms to be added to the hotel.
     * @return An integer value indicating the status of the addition operation. Returns 1 if the hotel was added successfully, 
     * -1 if the total room count is 0 or less, and -2 if the total room count exceeds 50.
     */
    public int addHotel(String name, int standardRoomCount, int deluxeRoomCount, int execRoomCount) {
        int cont = 1;

        //loop to check hotel name similarity
        for (int i = 0; i < hotelList.size() && cont == 1; i++) {
            if (name.equals(hotelList.get(i).getHotelName())) {
                cont = 0;
            }
        }

        int totalRoomCount = standardRoomCount + deluxeRoomCount + execRoomCount;
        //room number input is less than 0
        if (totalRoomCount <= 0) {
            cont = -1;
        }

         //total room number is greater than 50
        if (standardRoomCount + deluxeRoomCount + execRoomCount > 50) {
            cont = -2;
        }

        //valid case        
        if (cont == 1) {
            Hotel hotel = new Hotel(name);
            hotelList.add(hotel);
            for (int i = 0; i < standardRoomCount; i++) {
                hotel.addStandardRoom();
            }
            for (int i = 0; i < deluxeRoomCount; i++) {
                hotel.addDeluxeRoom();
            }
            for (int i = 0; i < execRoomCount; i++) {
                hotel.addExecRoom();
            }
        }

        return cont;
    }

    
    
    /**
     * Opens a hotel based on the given hotel index.
     * 
     * @param hotelIndex the index of the hotel to open
     * @return an array of strings representing the details of the opened hotel
     */
    public String[] openHotel(int hotelIndex) {
        //checker for invalid hotel indices
        if (hotelIndex >= 0 && hotelIndex < hotelList.size()) {
            this.selectedHotelIndex = hotelIndex;
            return hotelList.get(hotelIndex).getHotelDetails();
        } else {
            return new String[] {"\0"};
        }
    }

    /**
     * Renames the hotel at the specified index.
     *
     * @param newName the new name of the hotel
     * @return true if the name was successfully changed, false if the new name is already in use
     */
    public boolean renameHotel(String newName) {
        for (Hotel h : hotelList) {
            if (h.getHotelName().equals(newName)) {
                return false;
            }
        }

        hotelList.get(selectedHotelIndex).setHotelName(newName);
        return true;
    }

    /**
     * Adds a new room to the hotel at the specified index.
     *
     * @param roomType the type of room to add (1 for standard, 2 for deluxe, 3 for executive)
     * @param hotelIndex the index of the hotel to add the room to
     */
    public void addRoom(int roomType, int hotelIndex) {
        System.out.println("Room addition");

        switch (roomType) {
            case 1://standard
                hotelList.get(hotelIndex).addStandardRoom();
                break;
            case 2://deluxe
                hotelList.get(hotelIndex).addDeluxeRoom();
                break;
            case 3://executive
                hotelList.get(hotelIndex).addExecRoom();
                break;
            default:
        }
    }

    /**
     * Sets the total price of the reservation for the specified room.
     *
     * @param promoValidity the validity of the promotion (0 for no promotion, 1 for valid promotion)
     * @param checkIn the check-in date of the reservation
     * @param checkOut the check-out date of the reservation
     * @param roomIndex the index of the room to set the price for
     */
    public void setResTotalPrice(int promoValidity, int checkIn, int checkOut, int roomIndex) {
        hotelList.get(selectedHotelIndex).getRoom(roomIndex).setResTotalPrice(promoValidity, checkIn, checkOut);
    }

    /**
     * Removes a room from the hotel at the specified index.
     *
     * @param index the index of the room to remove
     * @return a string indicating the result of the removal operation
     */
    public String removeRoom(int index) {
        if (index >= 0 && index < hotelList.get(selectedHotelIndex).getRoomCount())
            return hotelList.get(selectedHotelIndex).removeRoom(index);
        else return "Input out of bounds";
    }

    /**
     * Updates the price of the hotel at the specified index.
     *
     * @param price the new price of the hotel
     * @return a string indicating the result of the update operation
     */
    public String updatePrice(double price) {
        if (price >= 150) {
            return hotelList.get(selectedHotelIndex).updatePrice(price);
        } else return "New price must be equal to or greater than 150";
    }

    /**
     * Updates the price of a specific date for the hotel at the specified index.
     *
     * @param date the date to update the price for
     * @param multiplier the multiplier to apply to the base price
     * @return a string indicating the result of the update operation
     */
    public String updateDatePrice(int date, double multiplier) {
        if (date >= 1 && date <= 30) {
            if (multiplier >= 50 && multiplier <= 150) {
                hotelList.get(selectedHotelIndex).updateDatePrice(date, multiplier);
                return "Date price modifier for Day " + date + " to " + multiplier + "% successful";
            } else return "New % modifier must range 50% to 150%";
        } else return "Please select valid dates to modify price (1-30)";
    }

    /**
     * Removes a reservation from the specified room.
     *
     * @param roomIndex the index of the room to remove the reservation from
     * @param resIndex the index of the reservation to remove
     * @return a string indicating the result of the removal operation
     */
    public String removeReservation(int roomIndex, int resIndex) {
        return hotelList.get(selectedHotelIndex).removeHotelReservation(roomIndex, resIndex);
    }

    /**
     * Removes the hotel at the specified index.
     */
    public void removeHotel() {
        hotelList.remove(hotelList.get(selectedHotelIndex));
    }

    /**
     * Adds a reservation to the hotel at the specified index.
     *
     * @param name the name of the guest
     * @param checkIn the check-in date of the reservation
     * @param checkOut the check-out date of the reservation
     * @param roomIndex the index of the room to reserve
     * @param breakdown the breakdown of the reservation
     * @return a string indicating the result of the addition operation
     */
    public String addReservation(String name, int checkIn, int checkOut, int roomIndex, String[] breakdown){
        return hotelList.get(selectedHotelIndex).addHotelReservation(name, checkIn, checkOut, roomIndex, breakdown);
    }


    public String[] getHotelListNames() {
        String[] names = new String[hotelList.size()];

        for (int i = 0; i < hotelList.size(); i++) {
            names[i] = hotelList.get(i).getHotelName();
        }

        return names;
    }

    /**
     * Retrieves an array of room names for the currently selected hotel.
     *
     * @return an array of room names
     */
    public String[] getRoomListNames() {
        String[] names = new String[hotelList.get(selectedHotelIndex).getRoomList().size()];

        for (int i = 0; i < hotelList.get(selectedHotelIndex).getRoomList().size(); i++) {
            names[i] = hotelList.get(selectedHotelIndex).getRoomName(i);
        }

        return names;
    }

    /**
     * Checks if a reservation is valid for a given room and reservation index.
     *
     * @param  roomIndex  the index of the room to check 
     * @param  resIndex   the index of the reservation to check
     * @return             an integer indicating the validity of the reservation:
     *                     - 0 if the reservation is valid
     *                     - 1 if the reservation index is out of range
     *                     - 2 if the room index is out of range
     */
    public int checkValidReservation(int roomIndex, int resIndex){
        int i = 0;

        if(getRoomCount()-1 < roomIndex || roomIndex < 0)
            return 2;

        if(hotelList.get(selectedHotelIndex).getRoom(roomIndex).getReservationList().size()-1 < resIndex || resIndex < 0)
            return 1;

        return i;
    }

    public String[] getResBreakdown(int roomIndex, int resIndex){
        return hotelList.get(selectedHotelIndex).getRoom(roomIndex).getResBreakdown(resIndex);
    }

    public String[] printReservationInfo(int roomIndex, int resIndex){
        return hotelList.get(selectedHotelIndex).getRoom(roomIndex).getReservationList().get(resIndex).printReservation();
    }

    public int getTotalAvailableRooms(int date){
        return hotelList.get(selectedHotelIndex).checkNumAvailableRooms(date);
    }

    public int getTotalReservedRooms(int date) {
        return hotelList.get(selectedHotelIndex).checkNumBookedRooms(date);
    }


    public String[][] getReservationListDetailed() {
        return hotelList.get(selectedHotelIndex).getReservationListDetailed();
    }

    public int getReservationCount() {
        return hotelList.get(selectedHotelIndex).getReservationCount();
    }

    public String getCurrentHotel() {
        return this.hotelList.get(selectedHotelIndex).getHotelName();
    }

    public int getCurrentHotelIndex() {
        return selectedHotelIndex;
    }

    public String getRoomName(int index) {
        return this.hotelList.get(selectedHotelIndex).getRoomName(index);
    }

    public int getRoomCount() {
        return this.hotelList.get(selectedHotelIndex).getRoomCount();
    }

    public int getStandardRoomCount() {
        return this.hotelList.get(selectedHotelIndex).getRoomTypeCount("Standard");
    }

    public int getDeluxeRoomCount() {
        return this.hotelList.get(selectedHotelIndex).getRoomTypeCount("Deluxe");
    }

    public int getExecRoomCount() {
        return this.hotelList.get(selectedHotelIndex).getRoomTypeCount("Executive");
    }

    public double getBasePrice() {
        return this.hotelList.get(selectedHotelIndex).getBasePrice();
    }

    public double getPricePerType(int index) {
        return this.hotelList.get(selectedHotelIndex).getPricePerType(index);
    }

    public double[] getDatePrice() {
        return this.hotelList.get(selectedHotelIndex).getAllDatePrice();
    }

    public String[] getAvailableDatesForRoom(int index) {
        return this.hotelList.get(selectedHotelIndex).getAvailableDatesForRoom(index);
    }

    public double getEarnings() {
        return this.hotelList.get(selectedHotelIndex).getHotelIncome();
    }

    public String[] getPriceBreakdown(int promoValidity, int checkIn, int checkOut, int roomIndex) {
        return hotelList.get(selectedHotelIndex).getRoom(roomIndex).priceBreakdown(promoValidity, checkIn, checkOut);
    }

    /**
     * Retrieves a list of available room names for a given date.
     *
     * @param  date  the date for which to check room availability
     * @return       an array of room names that are available on the given date
     */
    public String[] getAvailableRoomList(int date) {
        String[] roomList = new String[getTotalAvailableRooms(date)];
        int j = 0;
        for(int i=0; i<hotelList.get(selectedHotelIndex).getRoomList().size(); i++){
            if(hotelList.get(selectedHotelIndex).getRoomList().get(i).isAvailable(date, date+1)){
                roomList[j] = hotelList.get(selectedHotelIndex).getRoomList().get(i).getRoomName();
                j++;
            }
        }
        return roomList;
    }


    /**
     * The method getAvailableRoom() iterates through a list of rooms to find an available room for a
     * given check-in and check-out date range.
     *
     * @param checkIn  The checkIn parameter represents the check-in date for a room reservation.
     * @param checkOut The checkOut parameter represents the
     *                 check-out date for a room reservation.
     * @param type     The type parameter represents the wanted type of room
     * @return The method getAvailableRoom returns the index of the first available room in the
     * roomList that is available for the specified check-in and check-out dates. If no available
     * room is found, it returns -1.
     */
    public String getAvailableRoom(int checkIn, int checkOut, int type) {
        for (int i = 0; i < hotelList.get(selectedHotelIndex).getRoomList().size(); i++) {
            if (hotelList.get(selectedHotelIndex).getRoomList().get(i).isAvailable(checkIn, checkOut)) {
                switch (type) {
                    case 1://Standard
                        if (hotelList.get(selectedHotelIndex).getRoomList().get(i) instanceof Standard)
                            return (i + 1) + "";
                        break;
                    case 2://Deluxe
                        if (hotelList.get(selectedHotelIndex).getRoomList().get(i) instanceof Deluxe)
                            return (i + 1) + "";
                        break;
                    case 3://Executive
                        if (hotelList.get(selectedHotelIndex).getRoomList().get(i) instanceof Executive)
                            return (i + 1) + "";
                        break;
                    default:
                        break;
                }
            }
        }
        return "N/A";
    }

    /**
     * Serializes the hotel list into a file with the given name.
     *
     * @param name the name of the file to save the serialized data
     * @return a string indicating the success or failure of the serialization process
     */
    public String serializeHotelList(String name) {
        String serName = name + ".ser";
        File file = new File(savesDirPath + serName);

        if (file.exists()) {
            long lastModified = file.lastModified();
            Long recordedLastModified = lastModifiedMap.get(savesDirPath+serName);

            if (recordedLastModified != null && lastModified == recordedLastModified) {
            } else return "File already exists. Choose a different name or delete the existing file.";
        }

        try (FileOutputStream fileOut = new FileOutputStream(file);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(hotelList);
            System.out.println("Serialized data is saved in " + serName);
            lastModifiedMap.put(savesDirPath+serName, file.lastModified());
            return "Save successful";
        } catch (IOException i) {
            return "Save unsuccessful";
        }
    }

    /**
     * Deserializes a hotel list from a file with the given name.
     *
     * @param  name  the name of the file to load the serialized data from
     * @return       a string indicating the success or failure of the deserialization process,
     *               with the following possible values:
     *               - "List not found. e1" if the file is not found or cannot be read
     *               - "List not found. e2" if the class of the serialized object cannot be found
     *               - "Successfully loaded hotels" if the deserialization is successful
     */
    public String deserializeHotelList(String name) {
        String serName = name + ".ser";

        try (FileInputStream fileIn = new FileInputStream(savesDirPath+ serName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            hotelList = (ArrayList<Hotel>) in.readObject();
        } catch (IOException i) {
            return "List not found. e1";
        } catch (ClassNotFoundException c) {
            return "List not found. e2";
        }

        return "Successfully loaded hotels";
    }

    /**
     * Saves a new manager to the manager list and serializes the list to a file.
     *
     * @param  userName  the username of the new manager
     * @param  password  the password of the new manager
     * @return           a string indicating the success or failure of the save operation,
     *                   with the following possible values:
     *                   - "Username already exists" if the username already exists in the manager list
     *                   - "Manager added to list" if the manager is successfully added to the list and serialized
     *                   - "Manager addition unsuccessful" if there is an error during the save operation
     */
    public String saveManager(String userName, String password){
        for(Manager m : managerList){
            if (m.getUsername().equals(userName)) {
                return "Username already exists";
            }
        }
        managerList.add(new Manager(userName, password));

        try (FileOutputStream fileOut = new FileOutputStream(savesDirPath + "managers.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(managerList);
            System.out.println("Serialized data is saved in " + savesDirPath + "managers.ser");
            File file = new File(savesDirPath + "managers.ser");
            lastModifiedMap.put(savesDirPath+ "managers.ser", file.lastModified());

            return "Manager added to list";
        } catch (IOException i) {
            return "Manager addition unsuccessful";
        }

    }

    /**
     * Deserializes the manager list from a file and loads it into memory.
     *
     * This method attempts to deserialize the manager list from a file located at
     * `savesDirPath + "managers.ser"`. If the file is not found or there is an
     * error during the deserialization process, appropriate error messages are
     * printed to the console.
     *
     * After successfully deserializing the manager list, it is loaded into memory
     * and printed to the console for debugging purposes.
     *
     * @throws IOException if there is an error reading from the file
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     */
    public void deserializeManagerList(){
        try (FileInputStream fileIn = new FileInputStream(savesDirPath+ "managers.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            managerList = (ArrayList<Manager>) in.readObject();
        } catch (IOException i) {
            System.out.println("List not found. e1");
        } catch (ClassNotFoundException c) {
            System.out.println("List not found. e2");
        }

        System.out.println("Successfully loaded managers");

        for(Manager m : managerList){
            System.out.println(m.getUsername());
        }
    }

    public boolean loadManager(String userName, String password){
        for(Manager m : managerList){
            if (m.getUsername().equals(userName)) {
                if (m.getPassword().equals(password)){
                    managerPresence = true;
                    return true;
                }
            }
        }
        return false;
    }

    public void resetHotelList(){
        this.hotelList = new ArrayList<Hotel>();
    }

    public boolean getManagerPresence(){
        return this.managerPresence;
    }

    public void setManagerPresence(Boolean b){
        this.managerPresence = b;
    }
}

