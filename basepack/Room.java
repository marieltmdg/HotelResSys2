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
        this.datePricePercentMultiplier = new double[31];

        for(int i = 0; i < 31; i++){
            datePricePercentMultiplier[i] = 1;
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

    public void setDatePrice(int date, double percent){
        datePricePercentMultiplier[date-1] = percent;
    }

    public double getDatePricePercent(int date){
        return datePricePercentMultiplier[date-1];
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
     */
    public void removeReservation(int index){
        reservationList.remove(reservationList.get(index));
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
}
