package mvcpack;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;

        mainMenuListeners();
        openHotelListeners();
        inquireHotelListeners();
        manageHotelListeners();
        reserveHotelListeners();

        confirmCreateListener();
        confirmManageListeners();
    }

    public void mainMenuListeners(){
        this.view.setCreateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed create"); // CHECKER
                String[] hotelListNames= model.getHotelListNames();
                view.createHotel(hotelListNames);
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

                view.reserveHotel(model.getRoomListNames());
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

    public void confirmManageListeners(){
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

        this.view.setConfirmRemoveRmListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = (model.getPosNumValue(view.getRoomIndexTf()));
                String result = "Please input a positive number";

                if(index != -1)
                    result = model.removeRoom(index-1);

                view.setFeedbackLblText(result);
                view.removeRoom(model.getRoomListNames());
            }
        });

        this.view.setConfirmUpdatePriceListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double price = model.getPosDoubleValue(view.getRoomIndexTf());
                String result = "Please input a positive number";

                if (price != -1)
                    result = model.updatePrice(price);

                view.setFeedbackLblText(result);
                view.updatePrice(model.getBasePrice());
            }
        });

        this.view.setConfirmRemoveResBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int roomIndex = model.getPosNumValue(view.getRoomIndexTf());
                int resIndex = model.getPosNumValue(view.getResIndexTfText());
                String result = "Please input a positive number";

                if (roomIndex != -1 && resIndex != -1)
                    result = model.removeReservation(roomIndex-1, resIndex-1);

                view.setFeedbackLblText(result);
                view.removeReservation(model.getRoomCount(), model.getReservationListDetailed());
            }
        });

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
            }
        });

        this.view.setAddRoomListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.addRoom();
            }
        });

        this.view.setRemoveRoomListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.removeRoom(model.getRoomListNames());
            }
        });

        this.view.setUpdatePriceListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.updatePrice(model.getBasePrice());
            }
        });

        this.view.setRemoveResListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.removeReservation(model.getRoomCount(), model.getReservationListDetailed());
            }
        });

        this.view.setRemoveHotelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.removeHotel();
            }
        });
    }

    public void reserveHotelListeners() {
            this.view.setConfirmResListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            this.view.setInquireReservationListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });

            this.view.setInquireDateListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });
        }

}

