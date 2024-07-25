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
                int index = (model.utility.getPosNumValue(view.getHotelNameTfText()));
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

                int numStandard = model.utility.getPosNumValue(stringStandard);
                int numDeluxe = model.utility.getPosNumValue(stringDeluxe);
                int numExecutive = model.utility.getPosNumValue(stringExecutive);

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
                String[] breakdown = new String[1];
                breakdown[0] = "*breakdown*";
                view.reserveHotel(breakdown, 0);
                view.setGeneralTfEditable(false);
                view.setStandardRoomBtnClickable(true);
                view.setDeluxeRoomBtnClickable(true);
                view.setExecutiveRoomBtnClickable(true);
                view.setConfirmResClickable(true);
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

                int numStandard = model.utility.getPosNumValue(stringStandard);
                int numDeluxe = model.utility.getPosNumValue(stringDeluxe);
                int numExecutive = model.utility.getPosNumValue(stringExecutive);

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
                int index = (model.utility.getPosNumValue(view.getGeneralTf()));
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
                double price = model.utility.getPosDoubleValue(view.getGeneralTf());
                String result = "Please input a positive number";

                if (price != -1)
                    result = model.updatePrice(price);

                view.setFeedbackLblText(result);
                view.updatePrice(model.getBasePrice());
            }
        });

        this.view.setConfirmDatePriceListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int date = model.utility.getPosNumValue(view.getGeneralTf());
                double price = model.utility.getPosDoubleValue(view.getGeneral2TfText());
                String result = "Please input a positive number";

                if (date != -1 && price != -1)
                    result = model.updateDatePrice(date, price);

                view.setFeedbackLblText(result);
                view.datePrice(model.getDatePrice());
            }
        });

        this.view.setConfirmRemoveResListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int roomIndex = model.utility.getPosNumValue(view.getGeneralTf());
                int resIndex = model.utility.getPosNumValue(view.getGeneral2TfText());
                String result = "Please input a positive number";

                if (roomIndex != -1 && resIndex != -1)
                    result = model.removeReservation(roomIndex-1, resIndex-1);

                view.setFeedbackLblText(result);
                view.removeReservation(model.getRoomCount(), model.getReservationListDetailed());
            }
        });

        this.view.setConfirmRemoveHotelListener(new ActionListener() {
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

        this.view.setDatePriceListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.datePrice(model.getDatePrice());
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
                    System.out.println("Confirm");
                    String stringRoomIndex = (view.getGeneralTf());
                    String stringCheckIn = (view.getNumCheckInTf());
                    String stringCheckOut = (view.getNumCheckOutTf());
                    String stringPromoCode = (view.getPromoCodeTf());
                    String stringName = (view.getNameTf());
                    String result = "Please fill out name, check in, check out, and select room type";

                    int numRoomIndex = model.utility.getPosNumValue(stringRoomIndex);
                    int numCheckIn = model.utility.getPosNumValue(stringCheckIn);
                    int numCheckOut = model.utility.getPosNumValue(stringCheckOut);
                    int promoValidity = model.utility.isPromoValid(numCheckIn, numCheckOut, stringPromoCode);

                    boolean click = true;

                    if (numRoomIndex == -2){
                        result = "There is no available room for that type";
                    }else if (numRoomIndex != -1 && numCheckIn != -1 && numCheckOut != -1) {
                        if(!(model.utility.isEmpty(stringPromoCode))) {
                            switch (promoValidity) {
                                case 0:
                                    result = "Code not Redeemed. Invalid";
                                    break;
                                case 1:
                                case 2:
                                case 3:
                                    result = "Code Redeemed";
                                    break;
                                case 4:
                                    result = "";
                                    break;
                                default:
                                    result = "Code not Redeemed. Invalid";
                                    break;
                            }
                        }

                        String[] breakdown = model.getPriceBreakdown(promoValidity, numCheckIn, numCheckOut, numRoomIndex-1);
                        if (model.utility.checkDateValidity(numCheckIn, numCheckOut)) {
                            view.reserveHotel(breakdown, 1);
                            result = "";
                            click = false;
                        } else result = "Invalid check-in and check-out days";
                    } else {
                        //not valid
                        String[] breakdown = new String[1];
                        breakdown[0] = "*breakdown*";
                        view.reserveHotel(breakdown, 0);
                    }

                    view.setFeedbackLblText(result);
                    view.setConfirmResClickable(click);
                    view.setGeneralTfEditable(false);

                }
            });

            this.view.setStandardRoomListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    String stringCheckIn = (view.getNumCheckInTf().isEmpty()) ? "0" : view.getNumCheckInTf();
                    String stringCheckOut = (view.getNumCheckOutTf().isEmpty()) ? "0" :  view.getNumCheckOutTf();
                    
                    int numCheckIn = model.utility.getPosNumValue(stringCheckIn);
                    int numCheckOut = model.utility.getPosNumValue(stringCheckOut);

                    String temp =  model.getAvailableRoom(numCheckIn, numCheckOut, 1);

                    view.setGeneralTf(temp);

                    view.setStandardRoomBtnClickable(false);
                    view.setDeluxeRoomBtnClickable(true);
                    view.setExecutiveRoomBtnClickable(true);
                }
            });

            this.view.setDeluxeRoomListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    String stringCheckIn = (view.getNumCheckInTf().isEmpty()) ? "0" : view.getNumCheckInTf();
                    String stringCheckOut = (view.getNumCheckOutTf().isEmpty()) ? "0" :  view.getNumCheckOutTf();
                    
                    int numCheckIn = model.utility.getPosNumValue(stringCheckIn);
                    int numCheckOut = model.utility.getPosNumValue(stringCheckOut);

                    String temp =  model.getAvailableRoom(numCheckIn, numCheckOut, 2);

                    view.setGeneralTf(temp);
                    view.setStandardRoomBtnClickable(true);
                    view.setDeluxeRoomBtnClickable(false);
                    view.setExecutiveRoomBtnClickable(true);
                }
            });

            this.view.setExecutiveRoomListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    String stringCheckIn = (view.getNumCheckInTf().isEmpty()) ? "0" : view.getNumCheckInTf();
                    String stringCheckOut = (view.getNumCheckOutTf().isEmpty()) ? "0" :  view.getNumCheckOutTf();
                    
                    int numCheckIn = model.utility.getPosNumValue(stringCheckIn);
                    int numCheckOut = model.utility.getPosNumValue(stringCheckOut);

                    String temp =  model.getAvailableRoom(numCheckIn, numCheckOut, 3);

                    view.setGeneralTf(temp);

                    view.setStandardRoomBtnClickable(true);
                    view.setDeluxeRoomBtnClickable(true);
                    view.setExecutiveRoomBtnClickable(false);
                    
                }
            });

            this.view.setFinalizeResListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    String stringRoomIndex = (view.getGeneralTf());
                    String stringCheckIn = (view.getNumCheckInTf());
                    String stringCheckOut = (view.getNumCheckOutTf());
                    String stringPromoCode = (view.getPromoCodeTf());
                    String stringName = (view.getNameTf());
    
                    int numRoomIndex = model.utility.getPosNumValue(stringRoomIndex);
                    int numCheckIn = model.utility.getPosNumValue(stringCheckIn);
                    int numCheckOut = model.utility.getPosNumValue(stringCheckOut);
                    int promoValidity = model.utility.isPromoValid(numCheckIn, numCheckOut, stringPromoCode);
                    int days = numCheckOut - numCheckIn;

                    String result = "";
                    String[] breakdown  = new String[1];
                    breakdown[0] = "*breakdown*";

                    //BUG : all returning as -1
                    System.out.println(numRoomIndex);
                    System.out.println(numCheckIn);
                    System.out.println(numCheckOut);

                    if (numRoomIndex != -1 && numCheckIn != -1 && numCheckOut != -1) {
                        if (numRoomIndex == -2){
                            result = "There is no available room for that type";
                        }
                         else if (model.utility.checkDateValidity(numCheckIn, numCheckOut)) {
                            result = model.addReservation(stringName, numCheckIn, numCheckOut, numRoomIndex-1);
                            model.setResTotalPrice(promoValidity, numCheckIn, numCheckOut, numRoomIndex);
                        } else result = "Invalid check-in and check-out days";
                    } else result = "Please fill out name, check in, check out, and select room type";

                    view.setStandardRoomBtnClickable(true);
                    view.setDeluxeRoomBtnClickable(true);
                    view.setExecutiveRoomBtnClickable(true);
                    view.setConfirmResClickable(true);

                    view.setFeedbackLblText(result);
                    view.reserveHotel(breakdown, 0);
                    view.setGeneralTfEditable(false);
                }
            });

            this.view.setCancelResListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    String[] breakdown = new String[1];
                    breakdown[0] = "*breakdown*";
                    view.reserveHotel(breakdown, 0);

                    view.setFeedbackLblText("Reservation Cancelled");
                    view.setGeneralTfEditable(false);
                    view.setStandardRoomBtnClickable(true);
                    view.setDeluxeRoomBtnClickable(true);
                    view.setExecutiveRoomBtnClickable(true);
                    view.setConfirmResClickable(true);
                }
            });
        }

}

