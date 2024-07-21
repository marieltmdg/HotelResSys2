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
                String[] hotelListNames= model.getHotelListNames();
                view.selectHotel(hotelListNames);
            }
        });

        this.view.setSelectListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int index = (model.getPosNumValue(view.getHotelNameTfText()));
                String result = "Please input a positive number";

                if(index != -1) {
                    result = model.openHotel(index-1);
                    if(!(result.equals("\0"))) {
                        view.openHotel(result);
                        view.setFeedbackLblText("");
                        openHotelListeners();
                    } else view.setFeedbackLblText("Input out of bounds");
                } else view.setFeedbackLblText(result);
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

                String stringStandard = (view.getNumStandardTf().isEmpty()) ? "0" : view.getNumStandardTf();
                String stringDeluxe = (view.getNumDeluxeTf().isEmpty()) ? "0" :  view.getNumDeluxeTf();
                String stringExecutive = (view.getNumExecutiveTf().isEmpty()) ? "0" : view.getNumExecutiveTf();

                int numStandard = model.getPosNumValue(stringStandard);
                int numDeluxe = model.getPosNumValue(stringDeluxe);
                int numExecutive = model.getPosNumValue(stringExecutive);

                if(!(numStandard == -1 || numDeluxe == -1 || numExecutive == -1)){
                    int result = model.addHotel(hotelName, numStandard, numDeluxe, numExecutive);
                    String[] hotelListNames= model.getHotelListNames();
                
                    switch(result){
                        case -2://error exceeding room limit
                            view.setFeedbackLblText("Hotel NOT added. Hotels must only contain a total of 50 rooms");
                            break;
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
                } else view.setFeedbackLblText("Please input a positive number");
            }
        });
    }

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
                manageHotelListeners();
            }
        });

        this.view.setReserveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("PRESSED RESERVE");
                // TODO add reserve stuff
                view.reserveHotel(model.getRoomListNames(), null, 1);
                reserveHotelListeners();

            }
        });
    }

    public void inquireHotelListeners(){
        this.view.setInquireHotelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.inquireHotelInfo(model.getCurrentHotel(), model.getRoomCount(), model.getStandardRoomCount(),
                        model.getDeluxeRoomCount(), model.getExecRoomCount(), model.getEarnings());
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

    public void confirmRenameListener() {
        this.view.setConfirmRenameListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = view.getNewNameTfText();
                boolean result = model.renameHotel(newName);

                if (result) {
                    view.setFeedbackLblText("Hotel Rename Successful");
                    view.setTitleLblText("Current Hotel: " + newName);
                } else view.setFeedbackLblText("Hotel Name Already Taken");
            }
        });
    }

    public void confirmAddRmListener(){
        this.view.setConfirmAddRmBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String stringStandard = (view.getNumStandardTf().isEmpty()) ? "0" : view.getNumStandardTf();
                String stringDeluxe = (view.getNumDeluxeTf().isEmpty()) ? "0" : view.getNumDeluxeTf();
                String stringExecutive = (view.getNumExecutiveTf().isEmpty()) ? "0" : view.getNumExecutiveTf();

                int numStandard = model.getPosNumValue(stringStandard);
                int numDeluxe = model.getPosNumValue(stringDeluxe);
                int numExecutive = model.getPosNumValue(stringExecutive);

                if(!(numStandard == -1 || numDeluxe == -1 || numExecutive == -1)){
                    if(model.getRoomCount() + numStandard + numDeluxe + numExecutive >= 51){
                        System.out.println(model.getRoomCount() + numStandard + numDeluxe + numExecutive);
                        view.setFeedbackLblText("Hotel Room Count Cannot Exceed 50");
                    } else {
                        for (int i = 0; i < numStandard; i++) {
                            model.addRoom(1, model.getCurrentHotelIndex());
                        }
                        for (int i = 0; i < numDeluxe; i++) {
                            model.addRoom(2, model.getCurrentHotelIndex());
                        }
                        for (int i = 0; i < numExecutive; i++) {
                            model.addRoom(3, model.getCurrentHotelIndex());
                        }
                        view.setFeedbackLblText("Room Addition Successful");
                    }
                } else view.setFeedbackLblText("Please input a positive number");
            }
        });
    }

    public void confirmRemoveRoomListener(){

        this.view.setConfirmRemoveRmListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = (model.getPosNumValue(view.getNumRoomIndexTf()));
                String result = "Please input a positive number";

                if(index != -1)
                    result = model.removeRoom(index-1);

                view.setFeedbackLblText(result);
                view.removeRoom(model.getRoomListNames());
            }
        });
    }

    public void confirmUpdatePriceListener(){

        this.view.setConfirmUpdatePriceListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double price = model.getPosDoubleValue(view.getNumRoomIndexTf());
                String result = "Please input a positive number";

                if (price != -1)
                    result = model.updatePrice(price);

                view.setFeedbackLblText(result);
                view.updatePrice(model.getBasePrice());
            }
        });
    }

    public void confirmRemoveReservationListener(){

        this.view.setConfirmRemoveResBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int roomIndex = model.getPosNumValue(view.getNumRoomIndexTf());
                int resIndex = model.getPosNumValue(view.getResIndexTfText());
                String result = "Please input a positive number";

                if (roomIndex != -1 && resIndex != -1)
                    result = model.removeReservation(roomIndex-1, resIndex-1);

                view.setFeedbackLblText(result);
                view.removeReservation(model.getRoomCount(), model.getReservationListDetailed());
            }
        });
    }

    public void confirmRemoveHotelListener(){
        this.view.setConfirmRemoveHotelBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = model.getReservationCount();

                if(count == 0){
                    model.removeHotel();
                    view.setFeedbackLblText("Remove hotel successful");
                    view.home();
                } else {
                    view.setFeedbackLblText("Remove hotel unsuccessful. There are current reservations");
                }
            }
        });
    }

    public void manageHotelListeners(){

        this.view.setRenameHotelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.renameHotel();
                confirmRenameListener();
            }
        });

        this.view.setAddRoomListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.addRoom();
                confirmAddRmListener();
            }
        });

        this.view.setRemoveRoomListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.removeRoom(model.getRoomListNames());
                confirmRemoveRoomListener();
            }
        });

        this.view.setUpdatePriceListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.updatePrice(model.getBasePrice());
                confirmUpdatePriceListener();
            }
        });

        this.view.setRemoveResListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.removeReservation(model.getRoomCount(), model.getReservationListDetailed());
                confirmRemoveReservationListener();
            }
        });

        this.view.setRemoveHotelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.removeHotel();
                confirmRemoveHotelListener();
            }
        });
    }

    public void reserveHotelListeners() {
        this.view.setConfirmResListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("confirm");
                String stringRoomIndex = (view.getNumRoomIndexTf().isEmpty()) ? "0" : view.getNumRoomIndexTf();
                String stringCheckIn = (view.getNumCheckInTf().isEmpty()) ? "0" : view.getNumCheckInTf();
                String stringCheckOut = (view.getNumCheckOutTf().isEmpty()) ? "0" :  view.getNumCheckOutTf();
                String stringPromoCode = (view.getPromoCodeTf().isEmpty()) ? "0" : view.getPromoCodeTf();
                String stringName = (view.getNameTf().isEmpty()) ? " " : view.getNameTf();

                int numRoomIndex = model.getPosNumValue(stringRoomIndex) - 1;
                int numCheckIn = model.getPosNumValue(stringCheckIn);
                int numCheckOut = model.getPosNumValue(stringCheckOut);
                int promoValidity = model.utility.isPromoValid(numCheckIn, numCheckOut, stringPromoCode);
                int days = numCheckOut - numCheckIn;

                String[] breakdown = model.getPriceBreakdown(promoValidity, numCheckIn, numCheckOut, numRoomIndex);
                

                if(model.utility.checkDateValidity(numCheckIn, numCheckOut)){
                    model.addReservation(stringName, numCheckIn, numCheckOut, numRoomIndex);

                    view.reserveHotel(model.getRoomListNames(), breakdown, 2);
                }
                else view.setFeedbackLblText("Invalid check-in and check-out days");

            }
        });
    }
    

}

