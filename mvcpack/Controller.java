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

        this.view.setOpenListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed open"); //CHECKER
                String[] hotelListNames= model.getHotelListNames();
                view.printHotels(hotelListNames);
            }
        });

        this.view.setSelectListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.getHotelNameTfText();
                String hotelName = view.getHotelNameTfText();
                int hotelIndex = Integer.parseInt(hotelName) - 1;
                hotelName = model.openHotel(hotelIndex);

                if (!hotelName.equals("\0")){
                    view.setFeedbackLblText("Selected Hotel: " + hotelName);
                    view.openHotel(hotelName);

                } else {
                    view.setFeedbackLblText("Hotel does not exist");
                }
            }
        });

        this.view.setInquireListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.inquireHotel();
            }
        });

        this.view.setManageListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.manageHotel();
            }
        });

        this.view.setBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.home();
            }
        });

        // BUTTONS UNDER OPEN HOTEL
       this.view.setInquireHotelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            }
        });
    
        this.view.setInquireRoomListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            }
        });
    
        this.view.setInquireReservationListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            }
        });
    
        this.view.setInquireDateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            }
        });


    }


}
