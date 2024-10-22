package basepack;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The Room class represents a room in a hotel with a name, base price, and a list of reservations.
 */
public abstract class Room implements Serializable {
    private String roomName;
    private double basePrice;
    private ArrayList<Reservation> reservationList;
    private double[] datePricePercentMultiplier;

    /**
     * Constructs a Room instance with the specified room name.
     * Initializes the base price to 1299 and sets up an empty list of reservations.
     *
     * @param roomName The name of the room.
     */
    public Room(String roomName) {
        this.roomName = roomName;
        this.basePrice = 1299;
        this.reservationList = new ArrayList<Reservation>();
        this.datePricePercentMultiplier = new double[30];

        for(int i = 0; i < 30; i++){
            datePricePercentMultiplier[i] = 100;
        }
    }

     /**
     * The getReservationList() methodReturns the list of reservations for this room.
     *
     * @return The list of reservations.
     */
    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    /**
     * The method getBasePrice() returns the base price as a double value.
     * 
     * @return The method getBasePrice() is returning the value of the variable basePrice.
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * The getDatePricePercent() method returns the price percent multiplier for a given date index.
     *
     * @param index The index of the date.
     * @return The price percent multiplier for the specified date index.
     */
    public double getDatePricePercent(int index){
        return datePricePercentMultiplier[index];
    }

   /**
    * The getRoomName() method in Java returns the roomName attribute.
    * 
    * @return The method getRoomName() is returning the value of the variable roomName.
    */
    public String getRoomName() {
        return roomName;
    }

    /**
     * The getReservationListCount() method returns the number of reservations in the reservation list.
     *
     * @return The number of reservations.
     */
    public int getReservationListCount() {
        return reservationList.size();
    }

    /**
     * The getReservationName() method returns the name of the guest for a reservation at the specified index.
     *
     * @param index The index of the reservation.
     * @return The name of the guest.
     */
    public String getReservationName(int index){
        return reservationList.get(index).getGuestName();
    }

    /**
     * The getResDates() method returns the check-in and check-out dates for a reservation at the specified index.
     *
     * @param index The index of the reservation.
     * @return A string representing the check-in and check-out dates.
     */
    public String getResDates(int index){
        int checkIn = reservationList.get(index).getCheckIn();
        int checkOut = reservationList.get(index).getCheckOut();

        return "(" +checkIn+"-"+checkOut +")";
    }

    /**
     * The getResBreakdown() method returns the breakdown of the reservation at the specified index.
     *
     * @param index The index of the reservation.
     * @return An array of strings representing the breakdown of the reservation.
     */
    public String[] getResBreakdown(int index){
        return reservationList.get(index).getReservationBreakdown();
    }

    /**
     * The method setBasePrice() updates the base price with a new value.
     * 
     * @param newPrice The newPrice parameter is a double type variable that represents the new base
     * price value that will be set for an object or instance.
     */
    public void setBasePrice(double newPrice) {
        this.basePrice = newPrice;
    }

    /**
     * The setDatePrice() method sets the price percent multiplier for a specific date.
     *
     * @param date The date for which the price percent multiplier is to be set.
     * @param percent The price percent multiplier to be set.
     */
    public void setDatePrice(int date, double percent){
        datePricePercentMultiplier[date-1] = percent;
    }

    /**
     * The addReservation() method creates a new Reservation object with the given name, check-in
     * date, check-out date, and adds it to the reservation list.
     * 
     * @param name The name parameter represents the name of the person making the reservation.
     * @param checkIn The check-in date for the reservation. 
     * @param checkOut The check-out represents the date when the reservation ends.
     * @param breakdown The breakdown of the reservation.
     */
    public void addReservation(String name, int checkIn, int checkOut, String[] breakdown){
        reservationList.add(new Reservation(name, checkIn, checkOut, this, breakdown));
    }

    /**
     * The removeReservation() method removes a reservation from a list based on the provided index.
     * 
     * @param index The index parameter specifies the position of the reservation in the
     * reservationList.
     * @return String message if successful or not.
     */
    public String removeReservation(int index){
        if(reservationList.isEmpty()){
            return "There are no reservations for the selected room";
        }

        if (index >= 0 && index < getReservationListCount()) {
            reservationList.remove(reservationList.get(index));
            return "Reservation removal successful";
        }

        return "Reservation input out of bounds";
    }

    /**
     * The method isAvailable() checks if a given time range is available for reservation based on
     * existing reservations.
     * 
     * @param checkIn The check-in date of a new reservation that needs to be checked for availability.
     * @param checkOut The check-out date of a reservation. 
     * @return true if the property is available for the given check-in and check-out dates, and false otherwise.
     */
    public boolean isAvailable(int checkIn, int checkOut){
        for(Reservation reservation: reservationList){
            if(!(reservation.getCheckOut() <= checkIn || reservation.getCheckIn() >= checkOut)){
                return false;         
            }
        }
        return true;       
    }

    /**
     * The method getTotalReservationPrice() calculates the total price of all reservations in a list.
     * 
     * @return The method getTotalReservationPrice is returning the total price of all reservations
     * in the reservationList.
     */
    public double getTotalReservationPrice(){
        double sum = 0;

        //iterate reservations and sum their total price
        for(Reservation r: reservationList){
            sum += r.getTotalPrice();
        }
        return sum;
    }

    /**
     * The method printAvailability() generates a formatted string representing the availability of
     * items for each day within a specified range.
     * 
     * @return A formatted string representing the availability status for a range of days. 
     */
    public String[] printAvailability() {
        String s[] = new String[]{"", "", "", "", ""};

        for (int i = 1; i <= 30; i++) {
            if (isAvailable(i, i + 1)) {
                String dayStr = (i < 10 ? "[0" + i + "]" : "[" + i + "]");
                int weekIndex = (i - 1) / 7;  // determine which week the day belongs to
                s[weekIndex] += dayStr;
            }
        }
        return s;
    }

     /**
     * The printReservation() method prints out details of a guest's reservation including guest name,
     * stay length, room details, and total price.
     * 
     * @param resIndex The index of the reservation.
      * @return The String[] that lists down the details of the reservation.
     */
    public String[] printReservation(int resIndex){
        return reservationList.get(resIndex).printReservation();
    }

    /**
     * The getPriceAfterMultiplier() method gets the price per night, given the date price modifier.
     *
     * @param date The date for which the price is to be calculated.
     * @return The price after multiplier for the specified date.
     */
    public abstract double getPriceAfterMultiplier(int date);

    /**
     * The getTotalPriceAfterDiscount() method calculates the total price of a stay after applying any discounts.
     *
     * @param promoValidity The validity period of the promotion.
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     * @return The total price of the stay after applying any discounts.
     */
    public double getTotalPriceAfterDiscount(int promoValidity, int checkIn, int checkOut){
        double price = 0;

        for(int i = checkIn; i < checkOut; i++){
            price += getPriceAfterMultiplier(i); 
        }

        switch(promoValidity){
            case 1:
                price *= 0.9;
                break;
            case 2:
                price -= getPriceAfterMultiplier(checkIn);
                break;
            case 3:
                price *= 0.93;
                break;
            default: price *= 1;
                    break;

        }

        DecimalFormat df = new DecimalFormat("#0.00");      
        price = Double.valueOf(df.format(price));
        return price;
    }

    /**
     * The getPriceAfterDiscountBreakdown() method gets the price per night, given the discount,
     * and the date price modifier.
     *
     * @param promoValidity The promo validity.
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     * @return The double[] that lists down the price after discount per night.
     */
    public double[] getPriceAfterDiscountBreakdown(int promoValidity, int checkIn, int checkOut) {
        int numDays = checkOut - checkIn;
        double[] price = new double[numDays];

        // Initialize price per day
        for (int i = 0; i < numDays; i++) {
            price[i] = getPriceAfterMultiplier(checkIn + i);
        }

        // Apply discounts based on promoValidity
        switch (promoValidity) {
            case 1: // 10% discount
                for (int i = 0; i < numDays; i++) {
                    price[i] *= 0.9;
                }
                break;
            case 2: // Free first day
                if (numDays > 0) {
                    price[0] = 0.0;
                }
                break;
            case 3: // 7% discount
                for (int i = 0; i < numDays; i++) {
                    price[i] *= 0.93;
                }
                break;
        }

        for (int i = 0; i < numDays; i++) {
            DecimalFormat df = new DecimalFormat("#0.00");      
            price[i] = Double.valueOf(df.format(price[i]));
        }
        return price;
    }

    /**
     * the priceBreakdown() method constructs the String[] that will be printed,
     * corresponding to the price breakdown of the entire stay, including the total price.
     *
     * @param promoValidity The promo validity.
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     * @return The String[] that lists down the price breakdown per night and its total.
     */
    public String[] priceBreakdown(int promoValidity, int checkIn, int checkOut) {
        int numDays = checkOut - checkIn;
        String[] breakdown = new String[numDays + 1];
        double[] price = getPriceAfterDiscountBreakdown(promoValidity, checkIn, checkOut);
        
        for (int i = 0; i < numDays; i++) {
            DecimalFormat df = new DecimalFormat("#,##0.00");    
            String priceString = df.format(price[i]);  
            int currentDay = checkIn + i;
            switch (promoValidity) {
                case 1: // 10% discount
                case 3: // 7% discount
                    breakdown[i] = "Day " + currentDay + " - " + (currentDay+1) + " : P" + priceString;
                    break;
                case 2: // Free first day
                    if (i == 0) {
                        breakdown[i] = "Promo Redeemed. Free";
                    } else {
                        breakdown[i] = "Day " + currentDay + " - " + (currentDay+1) + " : P" + priceString;
                    }
                    break;
                default: // No discount
                    breakdown[i] = "Day " + currentDay + " - " + (currentDay+1) + " : P" + priceString;
                    break;
            }
        }

        double totalPrice = getTotalPriceAfterDiscount(promoValidity, checkIn, checkOut);
        DecimalFormat df = new DecimalFormat("#,##0.00");    
        String totalPriceString = df.format(totalPrice);  
        breakdown[breakdown.length-1] = "The Total Price is P" + totalPriceString;

        return breakdown;
    }

    /**
     * The setResTotalPrice() method sets the total price of the most recent reservation after applying any discounts.
     *
     * @param promoValidity The validity period of the promotion.
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     */
    public void setResTotalPrice(int promoValidity, int checkIn, int checkOut){
        reservationList.get(reservationList.size()-1).setTotalPrice(promoValidity, checkIn, checkOut);
    }
}