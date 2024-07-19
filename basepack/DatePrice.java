public class DatePrice {
    private int date;
    private double percent;

    public DatePrice(int date){
        this.date = date;
        this.percent = 1;
    }

    public boolean setPercent(double percent){
        if(percent >= 0.5 && percent <= 1.5){
            this.percent = percent;
            return true;
        } else return false;
    }

    public double getPercent(){
        return percent;
    }
}
