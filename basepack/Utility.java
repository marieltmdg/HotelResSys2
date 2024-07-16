package basepack;

import java.util.ArrayList;

/**
 * The Utility class provides various helper methods for user input and validation.
 */
public class Utility {

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
    
    public int hotelIndexLocator(ArrayList<Hotel> hotelList, String hotelName) {
        int selection = -1;

        //prompt user to select
        for (int i = 0 ; i < hotelList.size(); i++){
            if(hotelList.get(i).getHotelName().equals(hotelName)){
                selection = i;
            }
        }

        return selection;
    }
}