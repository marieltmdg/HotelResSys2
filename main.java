import mvcpack.*;

public class main {
    public static void main(String[] args) {
        Model driverModel = new Model();
        View driverView = new View();
        Controller driverController = new Controller(driverModel, driverView);
    }
}
