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

/**
 * The Model class is responsible for managing the overall operations involving hotels, rooms, and reservations.
 */
@SuppressWarnings("ALL")
public class Model {
    public Utility utility;
    private ArrayList<Hotel> hotelList;
    private int selectedHotelIndex;
    private String savesDirPath = "saves" + File.separator;
    private String hotelSavesDirPath = "saves" + File.separator + "hotels" + File.separator;
    private ArrayList<Manager> managerList;
    private Manager currentManager;

    /**
     * Constructs a Driver instance and initializes the necessary objects.
     */
    public Model() {
        this.utility = new Utility();
        this.hotelList = new ArrayList<Hotel>();
        this.managerList = new ArrayList<Manager>();
        this.currentManager = null;
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
     * The openHotel() method opens the hotel at the specified index and returns the hotel details.
     *
     * @param hotelIndex The index of the hotel to open.
     * @return An array of strings containing the hotel details.
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
     * The renameHotel() method renames the hotel at the specified index.
     *
     * @param newName The new name of the hotel.
     * @return A boolean value indicating the status of the renaming operation. Returns true if the hotel was renamed successfully,
     * and false if the hotel name already exists.
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
     * The addRoom() method adds a room to the hotel at the specified index.
     *
     * @param roomType The type of room to add.
     * @param hotelIndex The index of the hotel to add the room to.
     */
    public void addRoom(int roomType, int hotelIndex) {
        System.out.println("Room addition");

        switch (roomType) {
            case 1: //standard
                hotelList.get(hotelIndex).addStandardRoom();
                break;
            case 2: //deluxe
                hotelList.get(hotelIndex).addDeluxeRoom();
                break;
            case 3: //executive
                hotelList.get(hotelIndex).addExecRoom();
                break;
            default:
        }
    }

    /**
     * The setResTotalPrice() method sets the total price of a reservation based on the check-in and check-out dates.
     *
     * @param promoValidity The validity period of the promotion.
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     * @param roomIndex The index of the room to set the total price for.
     */
    public void setResTotalPrice(int promoValidity, int checkIn, int checkOut, int roomIndex) {
        hotelList.get(selectedHotelIndex).getRoom(roomIndex).setResTotalPrice(promoValidity, checkIn, checkOut);
    }

    /**
     * The removeRoom() method removes a room from the hotel at the specified index.
     *
     * @param index The index of the room to remove.
     * @return A string indicating the status of the removal operation.
     */
    public String removeRoom(int index) {
        if (index >= 0 && index < hotelList.get(selectedHotelIndex).getRoomCount())
            return hotelList.get(selectedHotelIndex).removeRoom(index);
        else return "Input out of bounds";
    }

    /**
     * The updatePrice() method updates the base price of the hotel.
     *
     * @param price The new price to set.
     * @return A string indicating the status of the update operation.
     */
    public String updatePrice(double price) {
        if (price >= 150) {
            return hotelList.get(selectedHotelIndex).updatePrice(price);
        } else return "New price must be equal to or greater than 150";
    }

    /**
     * The updateDatePrice() method updates the price modifier for a specific date.
     *
     * @param date The date to update the price for.
     * @param multiplier The new price modifier.
     * @return A string indicating the status of the update operation.
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
     * The removeReservation() method removes a reservation from the hotel at the specified index.
     *
     * @param roomIndex The index of the room to remove the reservation from.
     * @param resIndex The index of the reservation to remove.
     * @return A string indicating the status of the removal operation.
     */
    public String removeReservation(int roomIndex, int resIndex) {
        return hotelList.get(selectedHotelIndex).removeHotelReservation(roomIndex, resIndex);
    }

    /**
     * The removeHotel() method removes the hotel at the specified index.
     */
    public void removeHotel() {
        hotelList.remove(hotelList.get(selectedHotelIndex));
    }

    /**
     * The addReservation() method adds a reservation to the hotel at the specified index.
     *
     * @param name The name of the guest making the reservation.
     * @param checkIn The check-in date of the reservation.
     * @param checkOut The check-out date of the reservation.
     * @param roomIndex The index of the room to reserve.
     * @param breakdown The breakdown of the reservation.
     * @return A string indicating the status of the addition operation.
     */
    public String addReservation(String name, int checkIn, int checkOut, int roomIndex, String[] breakdown){
        return hotelList.get(selectedHotelIndex).addHotelReservation(name, checkIn, checkOut, roomIndex, breakdown);
    }

    /**
     * The getHotelListNames() method returns the names of all hotels in the hotelList.
     *
     * @return An array of strings containing the names of all hotels in the hotelList.
     */
    public String[] getHotelListNames() {
        String[] names = new String[hotelList.size()];

        for (int i = 0; i < hotelList.size(); i++) {
            names[i] = hotelList.get(i).getHotelName();
        }

        return names;
    }

    /**
     * The getRoomListNames() method returns the names of all rooms in the hotel at the selected index.
     *
     * @return An array of strings containing the names of all rooms in the hotel at the selected index.
     */
    public String[] getRoomListNames() {
        String[] names = new String[hotelList.get(selectedHotelIndex).getRoomList().size()];

        for (int i = 0; i < hotelList.get(selectedHotelIndex).getRoomList().size(); i++) {
            names[i] = hotelList.get(selectedHotelIndex).getRoomName(i);
        }

        return names;
    }

    /**
     * The getAvailableRoomNames() method returns the names of all available rooms in the hotel at the selected index
     * for the specified check-in and check-out dates.
     *
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     * @return An array of strings containing the names of all available rooms in the hotel at the selected index.
     */
    public String[] getAvailableRoomNames(int checkIn, int checkOut) {
        String[] names = new String[hotelList.get(selectedHotelIndex).getRoomList().size()];
        int j = 0;
        for (int i = 0; i < hotelList.get(selectedHotelIndex).getRoomList().size(); i++) {
            if (hotelList.get(selectedHotelIndex).getRoomList().get(i).isAvailable(checkIn, checkOut)) {
                names[j] = hotelList.get(selectedHotelIndex).getRoomName(i);
                j++;
            }
        }

        return names;
    }

    /**
     * The checkValidReservation() method checks the validity of the room and reservation indices.
     *
     * @param roomIndex The index of the room to check.
     * @param resIndex The index of the reservation to check.
     * @return An integer value indicating the status of the check. Returns 0 if the room and reservation indices are valid.
     *        1 if the reservation index is invalid. 2 if the room index is invalid.
     */
    public int checkValidReservation(int roomIndex, int resIndex){
        int i = 0;

        if(getRoomCount()-1 < roomIndex || roomIndex < 0)
            return 2;

        if(hotelList.get(selectedHotelIndex).getRoom(roomIndex).getReservationList().size()-1 < resIndex || resIndex < 0)
            return 1;

       

        return i;
    }

    /**
     * The getResBreakdown() method returns the breakdown of the reservation at the specified room and reservation indices.
     *
     * @param roomIndex The index of the room to get the reservation breakdown from.
     * @param resIndex The index of the reservation to get the breakdown from.
     * @return An array of strings containing the breakdown of the reservation.
     */
    public String[] getResBreakdown(int roomIndex, int resIndex){
        return hotelList.get(selectedHotelIndex).getRoom(roomIndex).getResBreakdown(resIndex);
    }

    /**
     * The printReservationInfo() method returns the information of the reservation at the specified room and reservation indices.
     *
     * @param roomIndex The index of the room to get the reservation information from.
     * @param resIndex The index of the reservation to get the information from.
     * @return An array of strings containing the information of the reservation.
     */
    public String[] printReservationInfo(int roomIndex, int resIndex){
        return hotelList.get(selectedHotelIndex).getRoom(roomIndex).getReservationList().get(resIndex).printReservation();
    }

    /**
     * The getTotalAvailableRooms() method returns the total number of available rooms in the hotel at the selected index for the specified date.
     *
     * @param date The date to check the availability for.
     * @return An integer value indicating the total number of available rooms.
     */
    public int getTotalAvailableRooms(int date){
        return hotelList.get(selectedHotelIndex).checkNumAvailableRooms(date);
    }

    /**
     * The getTotalReservedRooms() method returns the total number of reserved rooms in the hotel at the selected index for the specified date.
     *
     * @param date The date to check the reservations for.
     * @return An integer value indicating the total number of reserved rooms.
     */
    public int getTotalReservedRooms(int date) {
        return hotelList.get(selectedHotelIndex).checkNumBookedRooms(date);
    }

    /**
     * The getReservationListDetailed() method returns the detailed list of reservations in the hotel at the selected index.
     *
     * @return A 2D array of strings containing the detailed list of reservations.
     */
    public String[][] getReservationListDetailed() {
        return hotelList.get(selectedHotelIndex).getReservationListDetailed();
    }

    /**
     * The getReservationCount() method returns the total number of reservations in the hotel at the selected index.
     *
     * @return An integer value indicating the total number of reservations.
     */
    public int getReservationCount() {
        return hotelList.get(selectedHotelIndex).getReservationCount();
    }

    /**
     * The getCurrentHotel() method returns the current hotel name.
     *
     * @return The current hotel name.
     */
    public String getCurrentHotel() {
        return this.hotelList.get(selectedHotelIndex).getHotelName();
    }

    /**
     * The getCurrentHotelIndex() method returns the current hotel index.
     *
     * @return The current hotel index.
     */
    public int getCurrentHotelIndex() {
        return selectedHotelIndex;
    }

    /**
     * The getRoomName() method returns the name of the room at the specified index.
     *
     * @param index The index of the room to get the name from.
     * @return The name of the room.
     */
    public String getRoomName(int index) {
        return this.hotelList.get(selectedHotelIndex).getRoomName(index);
    }

    /**
     * The getRoomCount() method returns the total number of rooms in the hotel at the selected index.
     *
     * @return An integer value indicating the total number of rooms.
     */
    public int getRoomCount() {
        return this.hotelList.get(selectedHotelIndex).getRoomCount();
    }

    /**
     * The getStandardRoomCount() method returns the total number of standard rooms in the hotel at the selected index.
     *
     * @return An integer value indicating the total number of standard rooms.
     */
    public int getStandardRoomCount() {
        return this.hotelList.get(selectedHotelIndex).getRoomTypeCount("Standard");
    }

    /**
     * The getDeluxeRoomCount() method returns the total number of deluxe rooms in the hotel at the selected index.
     *
     * @return An integer value indicating the total number of deluxe rooms.
     */
    public int getDeluxeRoomCount() {
        return this.hotelList.get(selectedHotelIndex).getRoomTypeCount("Deluxe");
    }

    /**
     * The getExecRoomCount() method returns the total number of executive rooms in the hotel at the selected index.
     *
     * @return An integer value indicating the total number of executive rooms.
     */
    public int getExecRoomCount() {
        return this.hotelList.get(selectedHotelIndex).getRoomTypeCount("Executive");
    }

    /**
     * The getBasePrice() method returns the base price of the hotel at the selected index.
     *
     * @return The base price of the hotel.
     */
    public double getBasePrice() {
        return this.hotelList.get(selectedHotelIndex).getBasePrice();
    }

    /**
     * The getPricePerType() method returns the price of a room type in the hotel at the selected index.
     *
     * @param index The index of the room type to get the price from.
     * @return The price of the room type.
     */
    public double getPricePerType(int index) {
        return this.hotelList.get(selectedHotelIndex).getPricePerType(index);
    }

    /**
     * The getDatePrice() method returns the date price of the hotel at the selected index.
     *
     * @return An array of doubles containing the date prices.
     */
    public double[] getDatePrice() {
        return this.hotelList.get(selectedHotelIndex).getAllDatePrice();
    }

    /**
     * The getAvailableDatesForRoom() method returns the available dates for the room at the specified index.
     *
     * @param index The index of the room to get the available dates from.
     * @return An array of strings containing the available dates.
     */
    public String[] getAvailableDatesForRoom(int index) {
        return this.hotelList.get(selectedHotelIndex).getAvailableDatesForRoom(index);
    }

    /**
     * The getEarnings() method returns the total earnings of the hotel at the selected index.
     *
     * @return The total earnings of the hotel.
     */
    public double getEarnings() {
        return this.hotelList.get(selectedHotelIndex).getHotelIncome();
    }

    /**
     * The getPriceBreakdown() method returns the price breakdown of the room at the specified index.
     *
     * @param promoValidity The validity period of the promotion.
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     * @param roomIndex The index of the room to get the price breakdown from.
     * @return An array of strings containing the price breakdown.
     */
    public String[] getPriceBreakdown(int promoValidity, int checkIn, int checkOut, int roomIndex) {
        return hotelList.get(selectedHotelIndex).getRoom(roomIndex).priceBreakdown(promoValidity, checkIn, checkOut);
    }

    /**
     * The getAvailableRoomList() method returns the list of available rooms for the specified date.
     *
     * @param date The date to check the availability for.
     * @return An array of strings containing the available rooms.
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
     * The serializeHotelList() method serializes the hotelList and saves it to a file.
     *
     * @param name The name of the file to save the serialized data to.
     * @return A string indicating the status of the serialization operation.
     */
    public String serializeHotelList(String name) {
        String serName = name + ".ser";
        File file = new File(hotelSavesDirPath + serName);

        try (FileOutputStream fileOut = new FileOutputStream(file);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(hotelList);
            System.out.println("Serialized data is saved in " + serName);
            return "Save successful";
        } catch (IOException i) {
            return "Save unsuccessful";
        }
    }

    /**
     * The deserializeHotelList() method deserializes the hotelList from a file.
     *
     * @param name The name of the file to load the serialized data from.
     * @return A string indicating the status of the deserialization operation.
     */
    public String deserializeHotelList(String name) {
        String serName = name + ".ser";

        try (FileInputStream fileIn = new FileInputStream(hotelSavesDirPath+ serName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            hotelList = (ArrayList<Hotel>) in.readObject();
        } catch (IOException i) {
            return " List not found";
        } catch (ClassNotFoundException c) {
            return " List not found";
        }

        return " Successfully loaded hotels";
    }

    /**
     * The saveManager() method saves a new manager to the managerList.
     *
     * @param userName The username of the manager to save.
     * @param password The password of the manager to save.
     * @return A string indicating the status of the save operation.
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

            return "Manager added to list";
        } catch (IOException i) {
            return "Manager addition unsuccessful";
        }

    }

    /**
     * The deserializeManagerList() method deserializes the managerList from a file.
     */
    public void deserializeManagerList(){
        try (FileInputStream fileIn = new FileInputStream(savesDirPath+ "managers.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            managerList = (ArrayList<Manager>) in.readObject();
            System.out.println("Successfully loaded managers");
        } catch (IOException i) {
            System.out.println("Manager list not found. e1");
        } catch (ClassNotFoundException c) {
            System.out.println("Manager list not found. e2");
        }

    }

    /**
     * The loadManager() method loads a manager from the managerList.
     *
     * @param userName The username of the manager to load.
     * @param password The password of the manager to load.
     * @return A boolean value indicating the status of the load operation. Returns true if the manager was loaded successfully,
     * and false if the manager was not found.
     */
    public boolean loadManager(String userName, String password){
        for(Manager m : managerList){
            if (m.getUsername().equals(userName)) {
                if (m.getPassword().equals(password)){
                    this.currentManager = m;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * The deleteManager() method deletes the current manager from the managerList.
     *
     * @return A boolean value indicating the status of the deletion operation. Returns true if the manager was deleted successfully,
     * and false otherwise.
     */
    public boolean deleteManager(){
        try (FileOutputStream fileOut = new FileOutputStream(savesDirPath + "managers.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            managerList.remove(currentManager);
            out.writeObject(managerList);
            System.out.println("Serialized data is saved in " + savesDirPath + "managers.ser");
            return true;
        } catch(IOException i) {
            return false;
        }
    }

    /**
     * The deleteHotelList() method deletes a hotel list from the saves directory.
     *
     * @param listName The name of the hotel list to delete.
     * @return A string indicating the status of the deletion operation.
     */
    public String deleteHotelList(String listName){
        String serName = listName + ".ser";
        File file = new File(hotelSavesDirPath + serName);

        if (file.exists()){
            file.delete();
            return "Hotel list deleted successfully";
        } else {
            return "Hotel list not found";
        }
    }

    /**
     * The resetHotelList() method resets the hotelList.
     */
    public void resetHotelList(){
        this.hotelList = new ArrayList<Hotel>();
    }

    /**
     * The getManagerPresence() method checks if a manager is currently logged in.
     *
     * @return A boolean value indicating the presence of a manager. Returns true if a manager is logged in, and false otherwise.
     */
    public boolean getManagerPresence(){
         if (currentManager != null){
             return true;
         } else return false;
    }

    /**
     * The logoutManager() method logs out the current manager.
     */
    public void logoutManager(){
        this.currentManager = null;
    }
}

