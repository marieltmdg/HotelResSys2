package basepack.roompack;

import basepack.Room;

public class Deluxe extends Room {
    private double dPrice;

    public Deluxe(String roomName) {
        super(roomName);
        dPrice = super.getBasePrice() * 1.2;
    }
    
    public double getDPrice(){
        return dPrice;
    }

    public double getPriceAfterMultiplier(int date) {
        return dPrice * (super.getDatePricePercent(date-1) / 100);
    }

    public double getTotalPriceAfterDiscount(int promoValidity, int checkIn, int checkOut) {
        double price = 0;

        for (int i = checkIn; i < checkOut; i++) {
            price += getPriceAfterMultiplier(i);
        }

        switch (promoValidity) {
            case 1:
                price *= 0.9;
                break;
            case 2:
                price -= getPriceAfterMultiplier(checkIn);
                break;
            case 3:
                price *= 0.93;
                break;
            default:
                price *= 1;

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
        String[] breakdown = new String[numDays + 1];
        double[] price = getPriceAfterDiscountBreakdown(promoValidity, checkIn, checkOut);

        for (int i = 0; i < numDays; i++) {
            int currentDay = checkIn + i;
            switch (promoValidity) {
                case 1: // 10% discount
                case 3: // 10% discount
                    breakdown[i] = "Day " + currentDay + " - " + (currentDay + 1) + " : P" + price[i];
                    break;
                case 2: // Free first day
                    if (i == 0) {
                        breakdown[i] = "Promo Redeemed. Free";
                    } else {
                        breakdown[i] = "Day " + currentDay + " - " + (currentDay + 1) + " : P" + price[i];
                    }
                    break;
                default: // No discount
                    breakdown[i] = "Day " + currentDay + " - " + (currentDay + 1) + " : P" + price[i];
                    break;
            }
        }

        breakdown[breakdown.length - 1] = "The Total Price is P" + getTotalPriceAfterDiscount(promoValidity, checkIn, checkOut);

        return breakdown;
    }
}
