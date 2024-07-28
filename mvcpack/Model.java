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
public class Model {
    public Utility utility;
    private ArrayList<Hotel> hotelList;
    private int selectedHotelIndex;
    private String hotelDirPath = "hotels" + File.separator;
    private Map<String, Long> lastModifiedMap = new HashMap<>();

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
        for (int i = 0; i < hotelList.size() && cont == 1; i++) {
            if (name.equals(hotelList.get(i).getHotelName())) {
                cont = 0;
            }
        }

        int totalRoomCount = standardRoomCount + deluxeRoomCount + execRoomCount;
        if (totalRoomCount <= 0) {
            cont = -1;
        }


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

    public boolean renameHotel(String newName) {
        for (Hotel h : hotelList) {
            if (h.getHotelName().equals(newName)) {
                return false;
            }
        }

        hotelList.get(selectedHotelIndex).setHotelName(newName);
        return true;
    }

    public void addRoom(int roomType, int hotelIndex) {
        System.out.println("Room addition");

        switch (roomType) {
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

    public void setResTotalPrice(int promoValidity, int checkIn, int checkOut, int roomIndex) {
        hotelList.get(selectedHotelIndex).getRoom(roomIndex).setResTotalPrice(promoValidity, checkIn, checkOut);
    }

    public String removeRoom(int index) {
        if (index >= 0 && index < hotelList.get(selectedHotelIndex).getRoomCount())
            return hotelList.get(selectedHotelIndex).removeRoom(index);
        else return "Input out of bounds";
    }

    public String updatePrice(double price) {
        if (price >= 150) {
            return hotelList.get(selectedHotelIndex).updatePrice(price);
        } else return "New price must be equal to or greater than 150";
    }

    public String updateDatePrice(int date, double multiplier) {
        if (date >= 1 && date <= 30) {
            if (multiplier >= 50 && multiplier <= 150) {
                hotelList.get(selectedHotelIndex).updateDatePrice(date, multiplier);
                return "Date price modifier for Day " + date + " to " + multiplier + "% successful";
            } else return "New % modifier must range 50% to 150%";
        } else return "Please select valid dates to modify price (1-30)";
    }

    public String removeReservation(int roomIndex, int resIndex) {
        return hotelList.get(selectedHotelIndex).removeHotelReservation(roomIndex, resIndex);
    }

    public String removeHotel() {
        String serName = hotelList.get(selectedHotelIndex).getHotelName() + ".ser";
        hotelList.remove(hotelList.get(selectedHotelIndex));

        File file = new File(hotelDirPath + serName);
        if (file.exists()) {
            long lastModified = file.lastModified();
            Long recordedLastModified = lastModifiedMap.get(hotelDirPath + serName);

            //if it is the file that is being deleted
            if (recordedLastModified != null && lastModified == recordedLastModified) {
                if (file.delete()) {
                    return ". File deleted successfully";
                } else {
                    return ". Failed to delete the file";
                }
            }
        }
        return "";
    }

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

    public String[] getRoomListNames() {
        String[] names = new String[hotelList.get(selectedHotelIndex).getRoomList().size()];

        for (int i = 0; i < hotelList.get(selectedHotelIndex).getRoomList().size(); i++) {
            names[i] = hotelList.get(selectedHotelIndex).getRoomName(i);
        }

        return names;
    }

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

    public int checkValidReservation(int roomIndex, int resIndex){
        int i = 0;
        if(hotelList.get(selectedHotelIndex).getRoom(roomIndex).getReservationList().size() < resIndex)
            i = 1;

        if(getRoomCount() < roomIndex)
            i = 2;

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

    public String serializeHotelList(String name) {
        String serName = name + ".ser";

        try (FileOutputStream fileOut = new FileOutputStream(hotelDirPath + serName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(hotelList);
            System.out.println("Serialized data is saved in ");
            File file = new File(hotelDirPath + "model.ser");
            lastModifiedMap.put(hotelDirPath+ "model.ser", file.lastModified());
            return "Save successful";
        } catch (IOException i) {
            return "Save unsuccessful";
        }
    }

    public String deserializeHotelList(String name) {
        String serName = name + ".ser";

        try (FileInputStream fileIn = new FileInputStream(hotelDirPath+ serName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            hotelList = (ArrayList<Hotel>) in.readObject();
        } catch (IOException i) {
            return "List not found. e1";
        } catch (ClassNotFoundException c) {
            return "List not found. e2";
        }

        return "Successfully loaded hotels";
    }
}

