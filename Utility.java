import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Utility class provides various helper methods for user input and validation.
 */
public class Utility {
    private Scanner sc;

    /**
     * Constructs a utility instance and scanner instance.
     */
    public Utility() {
        this.sc= new Scanner(System.in);
    }

    /**
     * The method getIntInput() reads an integer input from the user, handling exceptions for
     * non-integer inputs.
     * 
     * @return The method getIntInput() is returning an integer value that is obtained by parsing the
     * user input as an integer.
     */
    public int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }
    }

    /**
     * The method getDoubleInput() reads a double input from the user, handling NumberFormatException
     * by prompting the user to enter a number until a valid input is provided.
     * 
     * @return The method getDoubleInput() is returning a double value that is parsed from the user
     * input.
     */
    public double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }
    }

    /**
     * The getStringInput() method in Java reads a line of input as a string.
     * 
     * @return The getStringInput method returns a String input.
     */
    public String getStringInput() {
        return sc.nextLine();
    }

    /**
     * The method printBorder() prints a border line consisting of equal signs.
     */
    public void printBorder(){
        System.out.println("\n=============================================");
    }

    /**
     * The method printSelection() prints the main menu.
     */
    public void printSelection() {
        System.out.println("\n==================MAIN MENU==================");
        System.out.println("[1] CREATE Hotel");
        System.out.println("[2] OPEN Hotel");
        System.out.println("[0] QUIT");
        System.out.print("\nSelection: ");
    }

    /**
     * The method printOpenHotel() prints the open hotel menu
     */
    public void printOpenHotel(){
        System.out.println("[1] VIEW Hotel");
        System.out.println("[2] MANAGE Hotel");
        System.out.println("[3] RESERVE Room");
        System.out.println("[0] BACK to menu");
        System.out.print("\nSelection: ");
    }

    /**
     * The method printViewHotel() prints the main menu for viewing hotel information.
     */
    public void printViewHotel() {
        System.out.println("\n=================VIEW HOTEL==================");
        System.out.println("[1] Inquire Hotel Information");
        System.out.println("[2] Inquire Room");
        System.out.println("[3] Inquire Reservation");
        System.out.println("[4] Inquire Hotel Availability");
        System.out.println("[0] BACK to open hotel");
        System.out.print("\nSelection: ");
    }

    /**
     * The method printInquireRoomsonDate() prints the prompt for inquiring rooms on a specific date.
     */
    public void printInquireRoomsOnDate() {
        printBorder();
        System.out.println("Enter the date you want to check");
        System.out.print("\nSelection: ");
    }

    /**
     * The method printHotelManager() prints the hotel management menu.
     */
    public void printHotelManager() {
        System.out.println("\n=================MANAGE HOTEL================");
        System.out.println("[1] RENAME Hotel");
        System.out.println("[2] ADD Room");
        System.out.println("[3] REMOVE Room");
        System.out.println("[4] UPDATE Price");
        System.out.println("[5] REMOVE Reservation");
        System.out.println("[6] REMOVE Hotel");
        System.out.println("[0] BACK to open hotel");
        System.out.print("\nSelection: ");
    }

    /**
     * Closes the Scanner object.
     */
    public void closeScanner() {
        sc.close();
    }

    /**
     * Checks the validity of the check-in and check-out dates.
     * 
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     * @return True if the dates are valid, false otherwise.
     */
    public boolean checkDateValidity(int checkIn, int checkOut) {
        if (checkOut <= checkIn || checkIn > 31 || checkOut > 31 || checkIn < 1 || checkOut < 1) {
            return false;
        }
        return true;
    }

    /**
     * Prompts the user to confirm or cancel a modification.
     * 
     * @return True if the modification is confirmed, false otherwise.
     */
    public boolean confirmModification() {
        System.out.println("Confirm modification: \n[1] Confirm\n[2] Cancel");

        //keep iterating until the user inputs a valid selection
        while (true) {
            System.out.print("\nSelection: ");
            switch (getIntInput()) {
                case 1: 
                    printBorder();
                    System.out.println("Action confirmed");
                    return true;
                case 2: 
                    printBorder();
                    System.out.println("Action cancelled");    
                    return false;
                default: 
                    printBorder();
                    System.out.println("Invalid input");
            }
        }
    }

    /**
     * Prompts the user to select a hotel from a list of hotels.
     * 
     * @param hotelList The list of hotels.
     * @return The selected hotel index within the hotelList, or -1 if the selection is invalid.
     */
    public int hotelIndexSelection(ArrayList<Hotel> hotelList) {
        int selection = 0;

        //prints the hotel list
        printBorder();
        System.out.println("Select Hotel: ");
        for (int i = 0; i < hotelList.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + hotelList.get(i).getHotelName());
        }

        //prompt user to select
        System.out.print("\nSelection: ");
        selection = getIntInput() - 1;

        if (selection >= 0 && selection < hotelList.size()) {
            return selection;
        } else {
            return -1;
        }
    }

    /**
     * Prompts the user to select the index of a room from a list of rooms.
     * 
     * @param roomList The list of rooms.
     * @return The selected room index, or -1 if the selection is invalid.
     */
    public int roomIndexSelection(ArrayList<Room> roomList) {
        int selection = 0;

        //prints the room list
        printBorder();
        System.out.println("Select Room: ");
        for (int i = 0; i < roomList.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + roomList.get(i).getRoomName());
        }

        //prompts the user to select
        System.out.print("\nSelection: ");
        selection = getIntInput() - 1;

        if (selection >= 0 && selection < roomList.size()) {
            return selection;
        } else {
            return -1;
        }
    }

    /**
     * Prompts the user to select the index of a reservation from a list of reservations.
     * 
     * @param reservationList The list of reservations.
     * @return The selected reservation index, or -1 if the selection is invalid.
     */
    public int resIndexSelection(ArrayList<Reservation> reservationList) {
        int selection = 0;

        //prints the reservation list
        printBorder();
        System.out.println("Select Reservation: ");
        for (int i = 0; i < reservationList.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + reservationList.get(i).getGuestName());
        }

        //prompts the user to select
        System.out.print("\nSelection: ");
        selection = getIntInput() - 1;

        if (selection >= 0 && selection < reservationList.size()) {
            return selection;
        } else {
            return -1;
        }
    }

    /**
     * Prompts the user to input a date.
     * 
     * @return The selected date.
     */
    public int dateSelection() {
        printBorder();
        System.out.println("Select Date: ");
        int i = getIntInput();

        return i;
    }

}