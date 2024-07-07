package mvcpack;
import java.util.ArrayList;

import basepack.*;

/**
 * The Driver class is responsible for managing the overall operations involving hotels, rooms, and reservations.
 */
public class Model {
    private Utility utility;
    private Manager manager;
    private ArrayList<Hotel> hotelList;
    /**
     * Constructs a Driver instance and initializes the necessary objects.
     */
    public Model() {
        this.utility = new Utility();
        this.manager = new Manager();
        this.hotelList = new ArrayList<Hotel>();
    }

    /**
     * The addHotel() method prompts the user to input a hotel name, checks if the hotel already
     * exists in the list, and adds the hotel to the list if it doesn't already exist.
     * 
     * @return True if the hotel is added, false otherwise.
     */
    public boolean addHotel(String name) {
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
        }

        return cont;
    }

    /**
     * The renameHotel() method allows the user to input a new name for a selected hotel, checks if
     * the name is already taken, and updates the hotel's name if the user confirms the modification.
     * 
     * @param hotelIndex The hotelIndex parameter is an index within the hotelList.
     */
    private void renameHotel(int hotelIndex) {
        boolean valid = true;
        
        utility.printBorder();
        System.out.print("New hotel name: ");
        String newName = utility.getStringInput();

        //loop to check hotel name similarity
        for(Hotel h : hotelList){
            if(h.getHotelName().equals(newName)){
                System.out.println("Hotel name already taken");
                valid = false;
            }
        }

        //valid case
        if (valid){
            System.out.println("Hotel name " + newName + " available");
            if (utility.confirmModification()) {
                hotelList.get(hotelIndex).setHotelName(newName);
            }
        }
    }

   /**
    * The method addRoom() prompts the user to confirm room addition for a selected hotel and then
    * adds a room to that hotel if confirmed.
    * 
    * @param hotelIndex The hotelIndex parameter is an index within the hotelList.
    */
    private void addRoom(int hotelIndex) {
        utility.printBorder();
        System.out.println("Room addition");
        
        if (utility.confirmModification()) {
            hotelList.get(hotelIndex).addRoom();
        }
    }

    /**
     * The removeRoom() method removes a room from the selected hotel after user confirmation.
     *
     * @param hotelIndex The hotelIndex parameter is an index within the hotelList.
     */
    private void removeRoom(int hotelIndex) {
        int selectedRoomIndex = utility.roomIndexSelection(hotelList.get(hotelIndex).getRoomList());
        int print = selectedRoomIndex+1;

        if (selectedRoomIndex != -1){
            utility.printBorder();
            System.out.println("Room to be removed: " + print);
            if (utility.confirmModification()) {
                hotelList.get(hotelIndex).removeRoom(selectedRoomIndex);
            }
        } else System.out.println("Selection not on list");
    }

    /**
     * The updateRoomPrice() method prompts the user for a new room price, validates it, and
     * updates the price for a selected hotel if certain conditions are met.
     * 
     * @param hotelIndex The hotelIndex parameter is an index within the hotelList.
     */
    private void updateRoomPrice(int hotelIndex) {
        
        utility.printBorder();
        System.out.print("New room price (Min. 100): ");
        double newPrice = utility.getDoubleInput();
        
        if (newPrice >= 100){ //checker for price minimum as per specs
            if (hotelList.get(hotelIndex).getReservationCount() == 0){ //checker for reservations within hotel
                if (utility.confirmModification()) { //confirmation checker
                    hotelList.get(hotelIndex).updatePrice(newPrice);
                }
            } else System.out.println("Update base price can not be done\nThere are active reservations");
        } else System.out.println("Update base price can not be done\nThe minimum price is 100");
    }

    /**
     * The method removeHotel() removes a selected hotel from a list after confirming the
     * modification.
     * 
     * @param hotelIndex The hotelIndex parameter is an index within the hotelList.
     * @return true if the hotel removal was successful and false if it was not.
     */
    private boolean removeHotel(int hotelIndex) {
        utility.printBorder();
        System.out.println("Remove hotel: " + hotelList.get(hotelIndex).getHotelName());

        boolean confirm = utility.confirmModification();

        //checker for any reservations inside the rooms inside the hotel 
        if (confirm && !hotelList.get(hotelIndex).checkForReservations()) {
            this.hotelList.remove(hotelList.get(hotelIndex));
            System.out.println("Hotel removal successful");
            return true;
        }else if(confirm && hotelList.get(hotelIndex).checkForReservations()){
            System.out.println("Hotel removal unsuccessful\nThere are reservations");
        } 
        return false;
    }

    /**
     * The openHotel() method checks if there are hotels in the system, allows the user to select a
     * hotel, and then provides options to view, manage, or add reservations for the selected hotel.
     */
    private void openHotel() {
        if (hotelList.isEmpty()) {
            utility.printBorder();
            System.out.println("There are no hotels in the system");
            return;
        }

        boolean quit = false;

        int hotelIndex = utility.hotelIndexSelection(hotelList);

        //checker for invalid hotel indices
        if (hotelIndex != -1) {
            //loop to stay in Open Hotel menu unless the user wants to quit
            while(!quit){
                System.out.print("\n=================OPEN HOTEL==================");
                System.out.println("\nHOTEL: " + hotelList.get(hotelIndex).getHotelName());
                utility.printOpenHotel();
                switch (utility.getIntInput()) {
                    case 1:
                        viewHotel(hotelIndex);
                        break;
                    case 2:
                        quit = manageHotel(hotelIndex);
                        break;
                    case 3:
                        manager.inputAddReservation(hotelList.get(hotelIndex));
                        break;
                    case 0:
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            }
        } else {
            System.out.println("Hotel does not exist");
        }
    }

    /**
     * The viewHotel() method allows the user to interact with a selected hotel by viewing its
     * information, selecting a room, and managing reservations.
     * 
     * @param hotelIndex The hotelIndex parameter is an index within the hotelList.
     */
    private void viewHotel(int hotelIndex) {
        boolean quit = false;
        int roomIndex = 0;
        //loop to stay in View Hotel menu unless the user quits
        while(!quit){
            utility.printViewHotel();
            switch (utility.getIntInput()) {
                case 1://hotel information
                    utility.printBorder();
                    hotelList.get(hotelIndex).printHotelInformation();
                    break;
                case 2://room information
                    roomIndex = utility.roomIndexSelection(hotelList.get(hotelIndex).getRoomList());
                    if(roomIndex != -1)
                        hotelList.get(hotelIndex).getRoom(roomIndex).printRoomInfo();
                    else 
                        System.out.println("Selection not on list");
                    break;
                case 3://reservation information
                    roomIndex = utility.roomIndexSelection(hotelList.get(hotelIndex).getRoomList());
                
                    if(roomIndex != -1){
                        if(!(hotelList.get(hotelIndex).getRoom(roomIndex).getReservationList().isEmpty())){
                            int resIndex = utility.resIndexSelection(hotelList.get(hotelIndex).getRoom(roomIndex).getReservationList());

                            if (resIndex != -1) {
                                utility.printBorder();
                                hotelList.get(hotelIndex).printReservation(roomIndex, resIndex);
                            } else {
                                System.out.println("Reservation does not exist");
                            }
                        } else System.out.println("There are no reservations for this room");
                    } else System.out.println("Selection not on list");
                    break;
                case 4: //availability information
                    int date = utility.dateSelection();
                    utility.printBorder();
                    if(date > 30 || date < 1){
                        System.out.println("Invalid Date");
                    }
                    else{
                        hotelList.get(hotelIndex).printRoomStates(date);
                    }
                    break;
                case 0:
                    quit = true;
                    break;
                default:
                    utility.printBorder();
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    /**
     * The method manageHotel() allows the user to interact with a selected hotel by performing
     * various actions such as renaming the hotel, adding or deleting rooms, updating room prices,
     * removing reservations, and potentially removing the entire hotel.
     * 
     * @param hotelIndex The hotelIndex parameter is an index within the hotelList.
     * @return True if the hotel was removed, false otherwise.
     */
    private boolean manageHotel(int hotelIndex) {
        boolean quit = false;
        boolean ret = false;
        //loop to stay in Manage Hotel menu unless the user quits
        while(!quit){
            utility.printHotelManager();
            switch (utility.getIntInput()) {
                case 1:
                    renameHotel(hotelIndex);
                    break;
                case 2:
                    addRoom(hotelIndex);
                    break;
                case 3:
                    removeRoom(hotelIndex);
                    break;
                case 4:
                    updateRoomPrice(hotelIndex);
                    break;
                case 5:
                    manager.inputRemoveReservation(hotelList.get(hotelIndex));
                    break;
                case 6:
                    quit = removeHotel(hotelIndex);
                    ret = quit;
                    break;
                case 0:
                    quit = true;
                    break;
                default:
                    utility.printBorder();
                    System.out.println("Invalid input");
                    break;
            }
        }
        return ret;
    }

    public String[] getHotelListNames(){
        String[] names = new String[hotelList.size()];

        for(int i = 0; i <hotelList.size();i++){
            names[i] = hotelList.get(i).getHotelName();
        }

        return names;
    }
}

