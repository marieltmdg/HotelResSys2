import mvcpack.*;

public class Main {
    public static void main(String[] args) {
        Model driverModel = new Model();
        View driverView = new View();
        Controller driverController = new Controller(driverModel, driverView);
    }
}
