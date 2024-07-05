import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DriverController implements ActionListener {
    private DriverModel driverModel;
    private DriverView driverView;

    public DriverController(DriverModel driverModel, DriverView driverView){
        this.driverModel = driverModel;
        this.driverView = driverView;

        updateView();

        this.driverView.setActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driverView.getHotelNameTfText();
                String hotelName = driverView.getHotelNameTfText();
            
                boolean result = driverModel.addHotel(hotelName);

                if (result) {
                    driverView.setFeedbackLblText("Hotel added successfully");
                } else driverView.setFeedbackLblText("Hotel NOT added");

            }
        });

    }

    public void updateView() {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
