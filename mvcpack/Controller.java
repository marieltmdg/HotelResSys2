package mvcpack;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;

        this.view.setCreateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed create"); // CHECKER
                view.createHotel();
            }
        });

        this.view.setOpenListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed open"); //CHECKER
                String[] hotelListNames= model.getHotelListNames();
                view.printHotels(hotelListNames);
            }
        });

        this.view.setConfirmListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.getHotelNameTfText();
                String hotelName = view.getHotelNameTfText();
            
                boolean result = model.addHotel(hotelName);

                if (result) {
                    view.setFeedbackLblText("Hotel \"" + hotelName + "\" added successfully");
                } else view.setFeedbackLblText("Hotel NOT added");
            }
        });
    }


}
