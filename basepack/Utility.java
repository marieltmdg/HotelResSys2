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

    public int getPosNumValue(String x){
        int temp = -1;
        try {
            temp = Integer.parseInt(x);
            if (temp < 0){
                return -1;
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
            if (temp < 0){
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
        return temp;
    }

    public boolean isEmpty(String x){
        if (x == null){
            return true;
        } else return false;
    }

    public int isPromoValid(int checkIn, int checkOut, String promoCode){
        switch(promoCode){
            case "I_WORK_HERE":
                return 1; 
            case "STAY4_GET1":
                if(checkOut - checkIn + 1 >= 5)
                    return 2;
                else return 0;
            case "PAYDAY":
                if((checkIn <= 15 && checkOut > 15) || (checkIn <= 30 && checkOut > 30))
                    return 3;
                else return 0;
            default: return 0;
        }
    }
}