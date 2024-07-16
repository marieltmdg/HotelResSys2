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
                String[] hotelListNames= model.getHotelListNames();
                view.createHotel(hotelListNames);
                confirmCreateListener();
                
            }
        });

        this.view.setOpenListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed open"); //CHECKER
                String[] hotelListNames= model.getHotelListNames();
                view.selectHotel(hotelListNames);
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
                    openHotelListeners();
                } else {
                    view.setFeedbackLblText("Hotel does not exist");
                }
            }
        });

        this.view.setBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.home();
            }
        });

    }

    public void confirmCreateListener() {
        this.view.setConfirmListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.getHotelNameTfText();
                String hotelName = view.getHotelNameTfText();
                String stringStandard = view.getNumStandardTf();
                String stringDeluxe = view.getNumDeluxeTf();
                String stringExecutive = view.getNumExecutiveTf();
                
                int numStandard = Integer.parseInt(stringStandard);
                int numDeluxe = Integer.parseInt(stringDeluxe);
                int numExecutive = Integer.parseInt(stringExecutive);

                int result = model.addHotel(hotelName, numStandard, numDeluxe, numExecutive);
                String[] hotelListNames= model.getHotelListNames();

                switch(result){
                    case -1://error no room
                        view.setFeedbackLblText("Hotel NOT added. Hotels must have atleast one room");
                        break;
                    case 0://error same name
                        view.setFeedbackLblText("Hotel NOT added. Hotel with the same name exists");
                        break;
                    case 1://valid case
                        view.createHotel(hotelListNames);
                        view.setFeedbackLblText("Hotel \"" + hotelName + "\" added successfully");
                        break;
                }
            }
        });
    }

    //TODO : ADD RESERVE LISTENER
    public void openHotelListeners(){
        this.view.setInquireListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.inquireHotel();
                inquireHotelListeners();
            }
        });

        this.view.setManageListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.manageHotel();
            }
        });

        this.view.setReserveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("PRESSED RESERVE");
                // TODO add reserve stuff
            }
        });
    }

    public void inquireHotelListeners(){
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

