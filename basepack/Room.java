package basepack;

import java.util.ArrayList;

/**
 * The Room class represents a room in a hotel with a name, base price, and a list of reservations.
 */
public abstract class Room {
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

    public void setDatePrice(int index, double percent){
        datePricePercentMultiplier[index] = percent;
    }

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
    * The getReservation() method in Java returns a Reservation attribute.
    * 
    * @param resIndex index of a reservation in reservationList.
    * @return The method getReservation() is returning the matching instance of Reservation in the 
    * reservationList.
    */
    public Reservation getReservation(int resIndex) {
        return reservationList.get(resIndex);
    }

    public int getReservationListCount() {
        return reservationList.size();
    }

    public String getReservationName(int index){
        return reservationList.get(index).getGuestName();
    }

    public String getResDates(int index){
        int checkIn = reservationList.get(index).getCheckIn();
        int checkOut = reservationList.get(index).getCheckOut();

        return "(" +checkIn+"-"+checkOut +")";
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
     * The addReservation() method creates a new Reservation object with the given name, check-in
     * date, check-out date, and adds it to the reservation list.
     * 
     * @param name The name parameter represents the name of the person making the reservation.
     * @param checkIn The check-in date for the reservation. 
     * @param checkOut The check-out represents the date when the reservation ends. 
     */
    public void addReservation(String name, int checkIn, int checkOut){
        reservationList.add(new Reservation(name, checkIn, checkOut, this));
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

        if (!(index >= 0 && index < getReservationListCount())) {
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
    public String printAvailability(){
        String s = "";
        int ctr = 1;

        //concatenate string to show available dates
        for(int i=1; i<=30; i++){
            if(isAvailable(i, i+1) && ctr % 7 != 0 && i<10){
                 s = s.concat("[0" + i + "]");
                 ctr++;
            }
            else if(isAvailable(i, i+1) && ctr % 7 != 0){
                 s = s.concat("[" + i + "]");
                 ctr++;
            }
            else if(isAvailable(i, i+1) && ctr % 7 == 0 && i < 10){
                s = s.concat("[0" + i + "]\n"); 
                ctr++;
            }   
            else if(isAvailable(i, i+1) && ctr % 7 == 0){
                s = s.concat("[" + i + "]\n"); 
                ctr++; 
            }       
        }
        return s;
    }
    
    /**
     * The method printRoomInfo() prints the room name, price per night, and available check-in dates
     * for a room.
     */
    public void printRoomInfo(){
        System.out.println("Room Name: " + roomName);
        System.out.println("Price per Night: " + basePrice);
        System.out.println("Available Check In Dates:");
        System.out.println(printAvailability());
    }

     /**
     * The printReservation() method prints out details of a guest's reservation including guest name,
     * stay length, room details, and total price.
     * 
     * @param resIndex The index of the reservation.
     */
    public void printReservation(int resIndex){
        reservationList.get(resIndex).printReservation();
    }

    public double getPriceAfterMultiplier(int day){
        return this.basePrice * this.datePricePercentMultiplier[day];
    }

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
            
        }
        return price; 
    }

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
    
        return price;
    }

    public String[] priceBreakdown(int promoValidity, int checkIn, int checkOut) {
        int numDays = checkOut - checkIn;
        String[] breakdown = new String[numDays+1];
        double[] price = getPriceAfterDiscountBreakdown(promoValidity, checkIn, checkOut);
    
        for (int i = 0; i < numDays; i++) {
            int currentDay = checkIn + i;
            switch (promoValidity) {
                case 1: // 10% discount
                case 3: // 10% discount
                    breakdown[i] = "Day " + currentDay + " - " + (currentDay+1) + " : P" + price[i];
                    break;
                case 2: // Free first day
                    if (i == 0) {
                        breakdown[i] = "Promo Redeemed. Free";
                    } else {
                        breakdown[i] = "Day " + currentDay + " - " + (currentDay+1) + " : P" + price[i];
                    }
                    break;
                default: // No discount
                    breakdown[i] = "Day " + currentDay + " - " + (currentDay+1) + " : P" + price[i];
                    break;
            }
        }

        breakdown[breakdown.length-1] = "The Total Price is P" + getTotalPriceAfterDiscount(promoValidity, checkIn, checkOut);
    
        return breakdown;
    }
}
