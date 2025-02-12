package mvcpack;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Controller class that acts as the middleman between the Model and View classes.
 */
public class Controller {
    private Model model;
    private View view;

    /**
     * Constructs a Controller instance with the specified Model and View instances.
     *
     * @param model The Model instance.
     * @param view The View instance.
     */
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;

        model.deserializeManagerList();
        loginListeners();
        mainMenuListeners();
        openHotelListeners();
        inquireHotelListeners();
        manageHotelListeners();
        reserveHotelListeners();
        confirmCreateListener();
        confirmManageListeners();
    }

    public void loginListeners(){
        this.view.setLoginListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String hotels = view.getHotelNameTfText();

                String username = view.getGeneralTf();
                String password = view.getLoginPwTfText();

                if (model.loadManager(username, password)) {
                    if(model.utility.isEmpty(hotels)){
                        view.home(true);
                        view.setFeedbackLblText("Login successful");
                    } else {
                        String add = model.deserializeHotelList(hotels);
                        view.home(true);
                        view.setFeedbackLblText("Login successful." + add);
                    }
                } else {
                    view.setFeedbackLblText("Wrong username or password");
                }
            }
        });

        this.view.setRegisterBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.setFeedbackLblText("");
                view.registerPage();
            }
        });

        this.view.setCancelRegisterBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.setFeedbackLblText("");
                view.loginPage();
            }
        });

        this.view.setCreateManagerListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = view.getCreateManagerNameTfText();
                String password = view.getCreateManagerPwTfText();

                if (username.isEmpty() || password.isEmpty()) {
                    view.setFeedbackLblText("Please complete all fields");
                } else {
                    String result = model.saveManager(username, password);
                    if (result.equals("\0")) {
                        view.setFeedbackLblText("Username already exists");
                    } else {
                        view.loginPage();
                        view.setFeedbackLblText(result);
                    }

                }
                
            }
        });

        this.view.setGuestListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                view.setFeedbackLblText("");
                model.logoutManager();
                view.home(false);
            }
        });
    }

    public void mainMenuListeners(){
        this.view.setCreateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] hotelListNames= model.getHotelListNames();
                view.createHotel(hotelListNames);
                view.setFeedbackLblText("");
            }
        });

        this.view.setOpenListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] hotelListNames= model.getHotelListNames();
                view.selectHotel(hotelListNames);
                view.setFeedbackLblText("");
            }
        });

        this.view.setSelectListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int index = (model.utility.getPosNumValue(view.getHotelNameTfText()));
                String[] result;

                if(index != -1) {
                    result = model.openHotel(index-1);
                    if(!(result[0].equals("\0"))) {
                        view.openHotel(result);
                        view.setFeedbackLblText("");
                    } else view.setFeedbackLblText("Input out of bounds");
                } else view.setFeedbackLblText("Please input a positive number");
            }
        });

        this.view.setLoadListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.setFeedbackLblText("");
                view.loadHotel();
            }
        });

        this.view.setConfirmLoadListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String name = view.getHotelNameTfText();
                String result = model.deserializeHotelList(name);

                view.setFeedbackLblText(result);
            }
        });

        this.view.setSaveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.saveHotels();
            }
        });

        this.view.setConfirmSaveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = view.getHotelNameTfText();
                String result = "Please input a name";

                if(!name.isEmpty()) {
                    result = model.serializeHotelList(name);
                }
                view.setFeedbackLblText(result);
            }
        });

        this.view.setBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.home(model.getManagerPresence());
                view.setFeedbackLblText("");
            }
        });

        this.view.setLogoutListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                model.resetHotelList();
                model.logoutManager();

                view.loginPage();
                view.setFeedbackLblText("Logout successful");
            }
        });

        this.view.setDeleteManagerListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.deleteManager();
            }
        });

        this.view.setConfirmDeleteManagerListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                boolean result = model.deleteManager();

                if (result) {
                    model.resetHotelList();
                    view.loginPage();
                    view.setFeedbackLblText("Manager account deletion successful");
                } else view.setFeedbackLblText("Manager account deletion unsuccessful");
            }
        });

        this.view.setDeleteHotelListListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.deleteHotelList();
            }
        });

        this.view.setConfirmDeleteHotelListListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String listName = view.getHotelNameTfText();

                String result = model.deleteHotelList(listName);

                view.setFeedbackLblText(result);
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
                view.setFeedbackLblText("");
            }
        });

        this.view.setManageListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.manageHotel();
                view.setFeedbackLblText("");
            }
        });

        this.view.setReserveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.setFeedbackLblText("");
                String[] breakdown = new String[1];
                breakdown[0] = "*breakdown*";
                view.reserveHotel(breakdown);
                view.setGeneralTfEditable(false);

                int std = model.getStandardRoomCount();
                int del = model.getDeluxeRoomCount();
                int exe = model.getExecRoomCount();

                if(std > 0) {
                    view.setStandardRoomBtnClickable(true);
                } else view.setStandardRoomBtnClickable(false);
                if(del > 0) {
                    view.setDeluxeRoomBtnClickable(true);
                } else view.setDeluxeRoomBtnClickable(false);
                if(exe > 0) {
                    view.setExecutiveRoomBtnClickable(true);
                } else view.setExecutiveRoomBtnClickable(false);
            }
        });


    }

    public void inquireHotelListeners(){
        this.view.setInquireHotelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.inquireHotelInfo(model.getCurrentHotel(), model.getRoomCount(), model.getStandardRoomCount(),
                        model.getDeluxeRoomCount(), model.getExecRoomCount(), model.getEarnings());
                view.setFeedbackLblText("");
            }
        });
    
        this.view.setInquireRoomListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.inquireRoomInfo(model.getRoomListNames());
            }
        });

        this.view.setInquireSelectRoomListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String sRoomIndex = view.getGeneralTf();
                int roomIndex = model.utility.getPosNumValue(sRoomIndex);
                String result = "Please input a positive number";

                if(roomIndex > model.getRoomCount()|| roomIndex == 0){
                    result = "No room corresponds with the input";
                }else if (roomIndex != -1 && roomIndex != 0) {
                    String roomName = model.getRoomName(roomIndex-1);
                    double price = model.getPricePerType(roomIndex-1);
                    String[] availableDates = model.getAvailableDatesForRoom(roomIndex-1);

                    view.inquireSelectedRoom(roomName, price, availableDates);
                    result = "Showing results for " + roomName;
                }

                view.setFeedbackLblText(result);
            }
        });
    
        this.view.setInquireReservationListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.setFeedbackLblText("");
                view.inquireReservation(model.getRoomCount(), model.getReservationListDetailed());
            }
        });
    
        this.view.setInquireDateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.setFeedbackLblText("");
                view.inquireDatesPanel();
                
            }
        });

        this.view.setDateButtonsListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
               
               
                int date = view.getDateButtonsClickedIndex(e.getSource())+1;
                String[] roomName = new String[model.getTotalAvailableRooms(date)];

                view.displayDateInfo(date, model.getTotalReservedRooms(date), 
                                    model.getTotalAvailableRooms(date), model.getAvailableRoomList(date));
                view.setFeedbackLblText("");
                    
            }
        });

        this.view.setDateBackButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                view.setFeedbackLblText("");
                view.inquireDatesPanel();
                
            }
        });

        this.view.setInquireResButtonLstener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int roomIndex = model.utility.getPosNumValue(view.getGeneralTf());
                int resIndex = model.utility.getPosNumValue(view.getGeneral2TfText());
                int validity = model.checkValidReservation(roomIndex-1, resIndex-1);
                String result = "Input a positive number";

                switch(validity){
                    case 0:
                        String resInfo[] = model.printReservationInfo(roomIndex-1, resIndex-1);
                        String breakdownInfo[] =  model.getResBreakdown(roomIndex-1, resIndex-1);
                        view.displaySelectedReservation(resInfo, breakdownInfo);
                        result = "";
                        break;
                    case 1:
                        result = "No existing reservation with the given index";
                        break;
                    case 2:
                        result = "No existing room with the given index";
                        break;
                }
                    
                view.setFeedbackLblText(result);
                
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
                    view.setFeedbackLblText("Hotel rename successful");
                } else view.setFeedbackLblText("Hotel name already taken");
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
                        view.setFeedbackLblText("Hotel room count cannot exceed 50");
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
                        view.setFeedbackLblText("Room addition successful");
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
                String result;

                if(count == 0){
                    model.removeHotel();
                    result = "Remove hotel successful";
                    view.setFeedbackLblText(result);
                    view.home(model.getManagerPresence());
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
                    String stringGuestName = view.getNameTf();
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
                        result = "There is no available room for that type and date";
                    } else if (numRoomIndex != -1 && numCheckIn != -1 && numCheckOut != -1 && !stringGuestName.isEmpty()) {
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
                        } else result = ""; //no promo inputted, but valid indices

                        if (model.utility.checkDateValidity(numCheckIn, numCheckOut)) {

                            // if everything is valid
                            String[] breakdown = model.getPriceBreakdown(promoValidity, numCheckIn, numCheckOut, numRoomIndex-1);
                            
                            view.setReserveDetailsEditable(false);
                            view.printReserveBreakdown(breakdown, 300);
                            click = false;
                        } else result = "Invalid check-in and check-out days";
                    }

                    // if invalid input
                    if (click) {
                        int std = model.getStandardRoomCount();
                        int del = model.getDeluxeRoomCount();
                        int exe = model.getExecRoomCount();

                        if (std > 0) {
                            view.setStandardRoomBtnClickable(true);
                        } else view.setStandardRoomBtnClickable(false);
                        if (del > 0) {
                            view.setDeluxeRoomBtnClickable(true);
                        } else view.setDeluxeRoomBtnClickable(false);
                        if (exe > 0) {
                            view.setExecutiveRoomBtnClickable(true);
                        } else view.setExecutiveRoomBtnClickable(false);
                    }
                    view.setFeedbackLblText(result);
                    view.setConfirmResClickable(click); //lock details
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

                    int del = model.getDeluxeRoomCount();
                    int exe = model.getExecRoomCount();

                    view.setStandardRoomBtnClickable(false);

                    if(del > 0) {
                        view.setDeluxeRoomBtnClickable(true);
                    } else view.setDeluxeRoomBtnClickable(false);
                    if(exe > 0) {
                        view.setExecutiveRoomBtnClickable(true);
                    } else view.setExecutiveRoomBtnClickable(false);
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
                    int std = model.getStandardRoomCount();
                    int exe = model.getExecRoomCount();

                    if(std > 0) {
                        view.setStandardRoomBtnClickable(true);
                    } else view.setStandardRoomBtnClickable(false);

                    view.setDeluxeRoomBtnClickable(false);

                    if(exe > 0) {
                        view.setExecutiveRoomBtnClickable(true);
                    } else view.setExecutiveRoomBtnClickable(false);
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

                    int std = model.getStandardRoomCount();
                    int del = model.getDeluxeRoomCount();
                    if(std > 0) {
                        view.setStandardRoomBtnClickable(true);
                    } else view.setStandardRoomBtnClickable(false);
                    if(del > 0) {
                        view.setDeluxeRoomBtnClickable(true);
                    } else view.setDeluxeRoomBtnClickable(false);

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

                    if (numRoomIndex != -1 && numCheckIn != -1 && numCheckOut != -1) {
                        if (numRoomIndex == -2){
                            result = "There is no available room of that type for those dates";
                        }
                         else if (model.utility.checkDateValidity(numCheckIn, numCheckOut)) {
                            String[] b = model.getPriceBreakdown(promoValidity, numCheckIn, numCheckOut, numRoomIndex-1);
                            result = model.addReservation(stringName, numCheckIn, numCheckOut, numRoomIndex-1, b);
                            model.setResTotalPrice(promoValidity, numCheckIn, numCheckOut, numRoomIndex-1);                   
                        } else result = "Invalid check-in and check-out days";
                    } else result = "Please fill out name, check in, check out, and select room type";

                    view.reserveHotel(breakdown);

                    int std = model.getStandardRoomCount();
                    int del = model.getDeluxeRoomCount();
                    int exe = model.getExecRoomCount();

                    if(std > 0) {
                        view.setStandardRoomBtnClickable(true);
                    } else view.setStandardRoomBtnClickable(false);
                    if(del > 0) {
                        view.setDeluxeRoomBtnClickable(true);
                    } else view.setDeluxeRoomBtnClickable(false);
                    if(exe > 0) {
                        view.setExecutiveRoomBtnClickable(true);
                    } else view.setExecutiveRoomBtnClickable(false);

                    view.setConfirmResClickable(true);

                    view.setFeedbackLblText(result);
                }
            });

            this.view.setCancelResListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    String[] breakdown = new String[1];
                    breakdown[0] = "*breakdown*";
                    view.reserveHotel(breakdown);
                    view.setGeneralTfEditable(false);

                    int std = model.getStandardRoomCount();
                    int del = model.getDeluxeRoomCount();
                    int exe = model.getExecRoomCount();

                    if(std > 0) {
                        view.setStandardRoomBtnClickable(true);
                    } else view.setStandardRoomBtnClickable(false);
                    if(del > 0) {
                        view.setDeluxeRoomBtnClickable(true);
                    } else view.setDeluxeRoomBtnClickable(false);
                    if(exe > 0) {
                        view.setExecutiveRoomBtnClickable(true);
                    } else view.setExecutiveRoomBtnClickable(false);
                }
            });
        }

}

