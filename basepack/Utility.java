package basepack;

import java.util.ArrayList;

/**
 * The Utility class provides various helper methods for user input and validation.
 */
public class Utility {

    /**
     * The checkDateValidity() method checks the validity of the check-in and check-out dates.
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
     * The getPosNumValue() method gets the positive integer value of a string.
     *
     * @param x The string to evaluate.
     * @return The integer, -1 if invalid input.
     */
    public int getPosNumValue(String x){
        int temp = -1;
        if(x.equalsIgnoreCase("N/A")){
            return -2;
        }
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

    /**
     * The getPosDoubleValue() method gets the positive double value of a string.
     *
     * @param x The string to evaluate.
     * @return The double, -1 if invalid input.
     */
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

    /**
     * The isEmpty() method checks if a given string is empty.
     *
     * @param x The string to evaluate.
     * @return The boolean value. True if null, false otherwise.
     */
    public boolean isEmpty(String x){
        if (x == null){
            return true;
        } else if (x.isEmpty()) {
            return true;
        }else return false;
    }

    /**
     * The isPromoValid() method checks if a given promo code is applicable,
     * given the conditions of the stay.
     *
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     * @param promoCode The promo code to evaluate.
     * @return The integer corresponding to the type of promo applied, 0 if there is no applicable promo.
     */
    public int isPromoValid(int checkIn, int checkOut, String promoCode){
        switch(promoCode) {
            case "I_WORK_HERE":
                return 1;
            case "STAY4_GET1":
                if (checkOut - checkIn + 1 >= 5)
                    return 2;
                else return 0;
            case "PAYDAY":
                if ((checkIn <= 15 && checkOut > 15) || (checkIn <= 30 && checkOut > 30))
                    return 3;
                else return 0;
            default:
                return 0;
        }
    }
}