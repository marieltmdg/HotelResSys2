public class main {
    public static void main(String[] args) {
        DriverModel driverModel = new DriverModel();
        DriverView driverView = new DriverView();

        DriverController driverController = new DriverController(driverModel, driverView);
    }
}
