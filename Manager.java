/**
 * The Manager class handles the addition and removal of reservations for a hotel.
 */
public class Manager {
    private Utility utility;

    /**
     * Constructs a Manager instance and initializes the Utility object.
     */
    public Manager() {
        utility = new Utility();
    }

    /**
     * Checks the date and prints if the date is invalid.
     * 
     * @param checkIn Integer that represents checkIn date.
     * @param checkOut  Integer that represents checkOut date.
     * @return True if the date is valid, false otherwise.
     */
    private boolean inputAddReservationPrompts(int checkIn, int checkOut){
        if (!(utility.checkDateValidity(checkIn, checkOut))) {
            System.out.println("Invalid booking dates\n");

            //specific date validity checker
            if (checkOut < checkIn) {
                System.out.println("Check-out can not be earlier than check-in");
            }else if(checkOut == checkIn) {
                System.out.println("Check-out can not be the same day as check-in");
            } else if (checkIn > 31 || checkOut > 31 || checkIn < 1 || checkOut < 1) {
                System.out.println("Please limit values to 1-31");
            }
            return true;
        }
        return false;
    }

    /**
     * Prompts the user to input details for adding a reservation to the specified hotel.
     * 
     * @param hotel The hotel to which the reservation will be added.
     */
    public void inputAddReservation(Hotel hotel) {
       System.out.println("\n================RESERVE ROOM=================");
        
        //input
        System.out.print("Enter guest name: ");
        String guestName = utility.getStringInput();
        System.out.print("Enter check-in day (1-30): ");
        int checkIn = utility.getIntInput();
        System.out.print("Enter check-out day (2-31): ");
        int checkOut = utility.getIntInput();

        utility.printBorder();
        if (!inputAddReservationPrompts(checkIn, checkOut)) {
           hotel.addHotelReservation(guestName, checkIn, checkOut);
        }
    }

    /**
     * Prompts the user to input details for removing a reservation from the specified hotel.
     * 
     * @param hotel The hotel from which the reservation will be removed.
     */
    public void inputRemoveReservation(Hotel hotel) {
        int roomIndex = utility.roomIndexSelection(hotel.getRoomList());
        int reservationIndex;
        String guestName;
        //checker for valid roomIndex
        if(hotel.getRoomList().size() > roomIndex && roomIndex >= 0){
            //checker if there is any reservation in the room
            if (!(hotel.getRoom(roomIndex).getReservationList().isEmpty())) {
                reservationIndex = utility.resIndexSelection(hotel.getRoomList().get(roomIndex).getReservationList());
                //checker if the reservation index is valid
                if(hotel.getRoom(roomIndex).getReservationList().size() > reservationIndex && reservationIndex >= 0){       
                    utility.printBorder();
                    System.out.println("Reservation exists");
                    if (utility.confirmModification()) {
                        //stores guestName of the reservation to be removed for a prompt
                        guestName = hotel.getRoom(roomIndex).getReservation(reservationIndex).getGuestName();
                        hotel.removeHotelReservation(roomIndex, reservationIndex);
                        System.out.println("Reservation successfully removed for " + guestName);
                        return;
                    } else {
                        System.out.println("Action cancelled");
                        return;
                    }
                } else System.out.println("Selection not on list");
            } else System.out.println("There are no reservations for this room");

        } else System.out.println("Selection not on list");
    }
}
