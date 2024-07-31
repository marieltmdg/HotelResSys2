import mvcpack.*;

/**
 * The Main class to run the program.
 */
public class Main {
    public static void main(String[] args) {
        Model driverModel = new Model();
        View driverView = new View();
        Controller driverController = new Controller(driverModel, driverView);
    }
}
