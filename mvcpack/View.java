package mvcpack;

import mvcpack.custompack.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class View extends JFrame {
    private CLabel createLbl, openLbl, feedbackLbl, titleLbl, centerTitleLbl;
    private JTextField hotelNameTf, numStandardTf, numDeluxeTf, numExecutiveTf,
                        numCheckInTf, numCheckOutTf, generalTf, promoCodeTf, nameTf;
    private CButton createBtn, openBtn, confirmBtn, selectBtn;
    private JPanel northPnl, southPnl, westPnl, eastPnl, centerPnl;

    //open hotel
    private CButton inquireBtn, manageBtn, reserveBtn;

    //inquire
    private CButton iHotelBtn, iRoomBtn, iResBtn, iDateBtn, backBtn;

    //manage
    private CButton renameBtn, addRoomBtn, removeRoomBtn, updatePriceBtn,
        removeReservationBtn, removeHotelBtn, datePriceBtn,
            confirmRenameBtn, confirmAddRmBtn, confirmRemoveRmBtn, confirmUpdatePriceBtn
            ,confirmRemoveResBtn, confirmRemoveHotelBtn, confirmDatePriceBtn;
    private JTextField newNameTf, general2Tf;

    private CButton confirmResBtn, finalizeResButton, cancelResButton;
    
    private final int SMALL_TF_WIDTH = 50;
    private final int TF_WIDTH = 200;
    private final int BTN_WIDTH = 200;
    private final int BTN_HEIGHT = 60;
    private final int SMALL_BTN_HEIGHT = BTN_HEIGHT - 15;
    private final int SMALL_BTN_WIDTH = BTN_WIDTH - 30;
    private final int TF_HEIGHT = 35;

    private final int MAINFRAME_WIDTH = 700;
    private final int MAINFRAME_HEIGHT = 500;
    private final int CENTER_MAIN_WIDTH = MAINFRAME_WIDTH-(BTN_WIDTH+10 + SMALL_BTN_WIDTH+20)-10;
    private final int CENTER_MAIN_HEIGHT = 400;
    private final String DEFAULT_FONT = "Verdana";
    private final int SUBTITLE_HEIGHT = 15;


    public View() {
        super("Hotel Reservation System");
        setLayout(new BorderLayout());
        setSize( MAINFRAME_WIDTH,MAINFRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
        setVisible(true);
        setResizable(false);
    }

    //initial values when opening
    private void init() {

        //BUTTONS
        this.confirmBtn = new CButton("Confirm", BTN_WIDTH, BTN_HEIGHT);
        this.selectBtn = new CButton("Select", BTN_WIDTH, BTN_HEIGHT);
        inquireBtn = new CButton("Inquire", BTN_WIDTH, BTN_HEIGHT);
        manageBtn = new CButton("Manage", BTN_WIDTH, BTN_HEIGHT);
        reserveBtn = new CButton("Reserve", BTN_WIDTH, BTN_HEIGHT);
        backBtn = new CButton("Back", BTN_WIDTH, BTN_HEIGHT);

        //inquire panel
        iDateBtn = new CButton("Dates", SMALL_BTN_WIDTH, SMALL_BTN_HEIGHT, 14);
        iHotelBtn = new CButton("Hotel", SMALL_BTN_WIDTH, SMALL_BTN_HEIGHT, 14);
        iResBtn = new CButton("Reservation", SMALL_BTN_WIDTH, SMALL_BTN_HEIGHT, 14);
        iRoomBtn = new CButton("Room", SMALL_BTN_WIDTH, SMALL_BTN_HEIGHT, 14);

        //manage pnl
        renameBtn = new CButton("Rename Hotel", SMALL_BTN_WIDTH, SMALL_BTN_HEIGHT, 14);
        addRoomBtn = new CButton("Add Room", SMALL_BTN_WIDTH, SMALL_BTN_HEIGHT, 14);
        removeRoomBtn = new CButton("Remove Room", SMALL_BTN_WIDTH, SMALL_BTN_HEIGHT, 14);
        updatePriceBtn = new CButton("Update Price", SMALL_BTN_WIDTH, SMALL_BTN_HEIGHT, 14);
        datePriceBtn = new CButton("Price/Date", SMALL_BTN_WIDTH, SMALL_BTN_HEIGHT, 14);
        removeReservationBtn = new CButton("Remove Booking", SMALL_BTN_WIDTH, SMALL_BTN_HEIGHT, 14);
        removeHotelBtn = new CButton("Remove Hotel", SMALL_BTN_WIDTH, SMALL_BTN_HEIGHT, 14);

        confirmRenameBtn = new CButton("Confirm Rename", BTN_WIDTH, SMALL_BTN_HEIGHT, 14);
        confirmAddRmBtn = new CButton("Confirm Add Rooms", BTN_WIDTH, SMALL_BTN_HEIGHT, 14);
        confirmRemoveRmBtn = new CButton("Confirm Removal", BTN_WIDTH, SMALL_BTN_HEIGHT, 14);
        confirmUpdatePriceBtn = new CButton("Confirm Update", BTN_WIDTH, SMALL_BTN_HEIGHT, 14);
        confirmDatePriceBtn = new CButton("Confirm Update", BTN_WIDTH, SMALL_BTN_HEIGHT, 14);
        confirmRemoveResBtn = new CButton("Confirm Removal", BTN_WIDTH, SMALL_BTN_HEIGHT, 14);
        confirmRemoveHotelBtn = new CButton("Confirm Removal", BTN_WIDTH, SMALL_BTN_HEIGHT, 14);

        //res pnl
        confirmResBtn = new CButton("Add Reservation", BTN_WIDTH, BTN_HEIGHT, 14);
        finalizeResButton = new CButton("Confirm", BTN_WIDTH, BTN_HEIGHT, 14);
        cancelResButton = new CButton("Cancel", BTN_WIDTH, BTN_HEIGHT, 14);

        centerPnl = new JPanel();

        //WEST PANEL
        this.createBtn = new CButton("Create a Hotel", BTN_WIDTH, BTN_HEIGHT);
        this.openBtn = new CButton("Open a Hotel", BTN_WIDTH, BTN_HEIGHT);
        
        westPnl = new JPanel();
        westPnl.setLayout(new FlowLayout());
        westPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 200));
        westPnl.setBackground(Color.decode("#1B384B"));
        westPnl.add(createBtn);
        westPnl.add(openBtn);

        //EAST PANEL
        
        //NORTH PANEL
        this.titleLbl = new CLabel();
        this.titleLbl.setPreferredSize(new Dimension(MAINFRAME_WIDTH, TF_HEIGHT + 5));
        this.titleLbl.setFont(new Font(DEFAULT_FONT, Font.BOLD, 30));
        this.titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        this.titleLbl.setVerticalAlignment(SwingConstants.CENTER);
        this.titleLbl.setBackground(Color.decode("#1B384B"));

        setTitleLblText("Main Menu");

        this.centerTitleLbl = new CLabel();
        this.centerTitleLbl.setPreferredSize(new Dimension(MAINFRAME_WIDTH, TF_HEIGHT-10));
        this.centerTitleLbl.setFont(new Font(DEFAULT_FONT, Font.BOLD, 20));
        this.centerTitleLbl.setBackground(Color.decode("#1B384B"));
        this.centerTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        this.centerTitleLbl.setVerticalAlignment(SwingConstants.CENTER);

        northPnl = new JPanel();
        northPnl.setLayout(new FlowLayout());
        northPnl.setPreferredSize(new Dimension(MAINFRAME_WIDTH,TF_HEIGHT+20));
        northPnl.setBackground(Color.decode("#1B384B"));
        northPnl.add(titleLbl);

        //SOUTH PANEL
        this.feedbackLbl = new CLabel();
        this.feedbackLbl.setPreferredSize(new Dimension(MAINFRAME_WIDTH, TF_HEIGHT + 10));
        this.feedbackLbl.setFont(new Font(DEFAULT_FONT, Font.BOLD, 15));
        this.feedbackLbl.setHorizontalAlignment(SwingConstants.CENTER);
        this.feedbackLbl.setVerticalAlignment(SwingConstants.CENTER);
        
        southPnl = new JPanel();
        southPnl.setLayout(new FlowLayout());
        southPnl.setPreferredSize(new Dimension(400,TF_HEIGHT+10));
        southPnl.add(feedbackLbl);
        
        //ADD EVERYTHING
        this.add(titleLbl, BorderLayout.NORTH);
        this.add(centerPnl, BorderLayout.CENTER);
        this.add(westPnl, BorderLayout.WEST);
        this.add(southPnl, BorderLayout.SOUTH);

        this.revalidate();
        this.repaint();

    }

    public void home(){
        this.remove(centerPnl);
        this.remove(westPnl);

        centerPnl = new JPanel();

        //WEST PANEL
        westPnl = new JPanel();
        westPnl.setLayout(new FlowLayout());
        westPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 200));
        westPnl.setBackground(Color.decode("#1B384B"));
        westPnl.add(createBtn);
        westPnl.add(openBtn);

        setTitleLblText("Main Menu");

        //ADD EVERYTHING
        this.add(centerPnl, BorderLayout.CENTER);
        this.add(westPnl, BorderLayout.WEST);

        this.revalidate();
        this.repaint();
    }

    
  // PRINT HOTEL PANEL
    public JScrollPane printHotels(String[] hotelNames, int width){
        System.out.println("PRINT HOTELS"); // CHECKER
        int num;

        JPanel mainHotelPnl = new JPanel();
        mainHotelPnl.setLayout(new BoxLayout(mainHotelPnl, BoxLayout.Y_AXIS));
        mainHotelPnl.setMaximumSize(new Dimension(width, 300));
        
        for(int i = 0; i < hotelNames.length; i++){
            num = i + 1;
            String lbl = "[" + num + "] " + hotelNames[i];

            CLabel hotel = new CLabel(lbl);
            hotel.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 12));
            hotel.setPreferredSize(new Dimension(width-10, 20));
            hotel.setMaximumSize(new Dimension(width, 20));
            
 
            mainHotelPnl.add(hotel);
         
        }

        JScrollPane printHotelsScrPane = new JScrollPane(mainHotelPnl);
        printHotelsScrPane.setPreferredSize(new Dimension(width, 300));

        return printHotelsScrPane;
    }

    
    // CREATE HOTEL PRESSED
    public void createHotel(String[] hotelNames){
        this.remove(centerPnl);
        
        this.setTitleLblText("Hotel Creation");
        centerPnl = new JPanel();
        centerPnl.setLayout(new BorderLayout());

        JPanel centerRightPnl = new JPanel();
        JPanel centerLeftPnl = new JPanel();
        JPanel headerPnl = new JPanel();
        JPanel addRoomPnl = new JPanel();

        addRoomPnl.setPreferredSize(new Dimension(230, CENTER_MAIN_HEIGHT));
        addRoomPnl.setLayout(new FlowLayout(FlowLayout.CENTER));

        CLabel roomAddLbl = new CLabel("Add Rooms              ", SUBTITLE_HEIGHT, Font.BOLD);
        CLabel standardAddLbl = new CLabel("Standard Room         ", SUBTITLE_HEIGHT, Font.PLAIN);
        CLabel deluxeAddLbl = new CLabel("Deluxe Room            ", SUBTITLE_HEIGHT, Font.PLAIN);
        CLabel executiveAddLbl = new CLabel("Executive Room        ", SUBTITLE_HEIGHT, Font.PLAIN);

        this.hotelNameTf = new JTextField();
        this.hotelNameTf.setPreferredSize(new Dimension(TF_WIDTH, TF_HEIGHT));

        this.numStandardTf = new JTextField();
        this.numDeluxeTf = new JTextField();
        this.numExecutiveTf = new JTextField();

        this.numStandardTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));
        this.numDeluxeTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));
        this.numExecutiveTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));

        centerRightPnl.setLayout(new FlowLayout(FlowLayout.RIGHT));
        centerRightPnl.setPreferredSize(new Dimension(245, 300));
        centerLeftPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerLeftPnl.setPreferredSize(new Dimension(245, 300));

        headerPnl.setPreferredSize(new Dimension(230, TF_HEIGHT));

        addRoomPnl.add(new CLabel(""));
        addRoomPnl.add(roomAddLbl);

        addRoomPnl.add(standardAddLbl);
        addRoomPnl.add(numStandardTf);

        addRoomPnl.add(deluxeAddLbl);
        addRoomPnl.add(numDeluxeTf);

        addRoomPnl.add(executiveAddLbl);
        addRoomPnl.add(numExecutiveTf);

        addRoomPnl.add(confirmBtn);

        this.createLbl = new CLabel("Hotel Name: ",SUBTITLE_HEIGHT, Font.BOLD);
        centerLeftPnl.add(createLbl);
        centerLeftPnl.add(hotelNameTf);
        centerLeftPnl.add(addRoomPnl);

        this.openLbl = new CLabel(TF_WIDTH, TF_HEIGHT-15,"Hotels in the system: ", SUBTITLE_HEIGHT, Font.BOLD);
        headerPnl.add(openLbl);
        centerRightPnl.add(headerPnl);
        centerRightPnl.add(printHotels(hotelNames, 220));

        centerPnl.add(centerLeftPnl, BorderLayout.WEST);
        centerPnl.add(centerRightPnl, BorderLayout.EAST);
        this.add(centerPnl);

        this.revalidate();
        this.repaint();
    }

    public void selectHotel(String[] hotelNames){
        this.remove(centerPnl);
        this.setTitleLblText("Hotel Selection");

        centerPnl = new JPanel();
        centerPnl.setLayout(new BorderLayout());

        JPanel centerLeftPnl = new JPanel();
        JPanel centerRightPnl = new JPanel();
        CLabel selectHotelLbl = new CLabel("Select Hotel [n]: ", SUBTITLE_HEIGHT, Font.BOLD);

        centerRightPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerRightPnl.setPreferredSize(new Dimension(245, 300));
        centerLeftPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerLeftPnl.setPreferredSize(new Dimension(245, 300));

        this.hotelNameTf = new JTextField();
        this.hotelNameTf.setPreferredSize(new Dimension(TF_WIDTH, TF_HEIGHT));
        centerLeftPnl.add(printHotels(hotelNames, 230));

        centerRightPnl.add(selectHotelLbl);
        centerRightPnl.add(hotelNameTf);
        centerRightPnl.add(selectBtn);

        centerPnl.add(centerLeftPnl, BorderLayout.WEST);
        centerPnl.add(centerRightPnl, BorderLayout.EAST);
        this.add(centerPnl);
        centerPnl.revalidate();
        centerPnl.repaint();
    }

    // there is a selected hotel
    public void openHotel(String hotelName){
        setTitleLblText("Current Hotel: " + hotelName);

        this.remove(centerPnl);
        this.westPnl.remove(this.createBtn);
        this.westPnl.remove(this.openBtn);

        westPnl.add(backBtn);
        this.westPnl.add(inquireBtn);
        this.westPnl.add(manageBtn);
        this.westPnl.add(reserveBtn);
        this.revalidate();
        this.repaint();
    }

    public JPanel inquireHotelLeftPanel(){
        JPanel centerLeftPnl = new JPanel();
        //ctrleft panel

        centerLeftPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerLeftPnl.setPreferredSize(new Dimension(SMALL_BTN_WIDTH+10, CENTER_MAIN_HEIGHT));

        centerLeftPnl.add(iHotelBtn);
        centerLeftPnl.add(iRoomBtn);
        centerLeftPnl.add(iResBtn);
        centerLeftPnl.add(iDateBtn);

        return centerLeftPnl;
    }

    public void inquireHotel(){
        this.remove(this.centerPnl);
        centerPnl = new JPanel();
        JPanel centerLeftPnl = new JPanel();
        //ctrleft panel

        centerPnl.setLayout(new BorderLayout());

        setCenterTitleLblText("Inquire Hotel");

        this.centerPnl.add(centerTitleLbl, BorderLayout.NORTH);
        this.centerPnl.add(inquireHotelLeftPanel(), BorderLayout.WEST);

        this.add(westPnl, BorderLayout.WEST);
        this.add(centerPnl, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    public void inquireHotelInfo(String hotelName, int roomCount, int standardRmCt,
                                 int delRmCt, int exRmCt,  double earnings){
        this.remove(centerPnl);
        centerPnl = new JPanel(new BorderLayout());

        JPanel centerRightPnl = new JPanel();
        centerRightPnl.setLayout(new FlowLayout());
        centerRightPnl.setPreferredSize(new Dimension(CENTER_MAIN_WIDTH, CENTER_MAIN_HEIGHT));

        JPanel hotelNamePnl  = new JPanel();
        hotelNamePnl.setPreferredSize(new Dimension(BTN_WIDTH+10, TF_HEIGHT));
        CLabel hotelNameLbl = new CLabel("Hotel Name: " + hotelName, SUBTITLE_HEIGHT, Font.BOLD);
        hotelNamePnl.add(hotelNameLbl);

        JPanel roomCountPnl = new JPanel();
        roomCountPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, TF_HEIGHT*3));
        CLabel roomCountLbl = new CLabel("Total Room Count: " + roomCount, SUBTITLE_HEIGHT, Font.BOLD);
        CLabel standardCountLbl = new CLabel("Standard Rooms: " + standardRmCt, SUBTITLE_HEIGHT, Font.PLAIN);
        CLabel deluxeCountLbl = new CLabel("Deluxe Rooms: " + delRmCt, SUBTITLE_HEIGHT, Font.PLAIN);
        CLabel execCountLbl = new CLabel("Executive Rooms: " + exRmCt, SUBTITLE_HEIGHT, Font.PLAIN);
        roomCountPnl.add(roomCountLbl);
        roomCountPnl.add(standardCountLbl);
        roomCountPnl.add(deluxeCountLbl);
        roomCountPnl.add(execCountLbl);

        JPanel earningsPnl = new JPanel();
        earningsPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, TF_HEIGHT*2));
        CLabel earningsLbl = new CLabel(BTN_WIDTH, SUBTITLE_HEIGHT*2, "<html>"+"Est. earnings for the month:" + "<html>"
                + "<html> " + earnings + "<html>", SUBTITLE_HEIGHT,Font.BOLD);
        earningsPnl.add(earningsLbl);

        centerRightPnl.add(hotelNamePnl);
        centerRightPnl.add(roomCountPnl);
        centerRightPnl.add(earningsPnl);

        setCenterTitleLblText("Hotel Information");

        centerPnl.add(centerTitleLbl, BorderLayout.NORTH);
        centerPnl.add(inquireHotelLeftPanel(), BorderLayout.WEST);
        centerPnl.add(centerRightPnl, BorderLayout.EAST);

        this.add(centerPnl, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    public JPanel manageHotelLeftPanel(){
        JPanel centerLeftPnl = new JPanel();

        centerLeftPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerLeftPnl.setPreferredSize(new Dimension(SMALL_BTN_WIDTH+10, 300));

        centerLeftPnl.add(renameBtn);
        centerLeftPnl.add(addRoomBtn);
        centerLeftPnl.add(removeRoomBtn);
        centerLeftPnl.add(updatePriceBtn);
        centerLeftPnl.add(datePriceBtn);
        centerLeftPnl.add(removeReservationBtn);
        centerLeftPnl.add(removeHotelBtn);

        return centerLeftPnl;
    }

    public void manageHotel(){
        this.remove(this.centerPnl);

        centerPnl = new JPanel();
        JPanel centerLeftPnl = new JPanel();

        centerPnl.setLayout(new BorderLayout());
        centerLeftPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerLeftPnl.setPreferredSize(new Dimension(SMALL_BTN_WIDTH+10, 300));

        setCenterTitleLblText("Manage Hotel");

        centerPnl.add(centerTitleLbl, BorderLayout.NORTH);
        centerPnl.add(manageHotelLeftPanel(), BorderLayout.WEST);
        this.add(centerPnl, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    public void renameHotel(){
        this.remove(centerPnl);

        JPanel centerRightPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerRightPnl.setPreferredSize(new Dimension(CENTER_MAIN_WIDTH, CENTER_MAIN_HEIGHT));
        centerPnl = new JPanel();
        centerPnl.setLayout(new BorderLayout());

        CLabel nameLbl = new CLabel("New Hotel Name:", SUBTITLE_HEIGHT, Font.BOLD);
        this.newNameTf = new JTextField();
        this.newNameTf.setPreferredSize(new Dimension(TF_WIDTH, TF_HEIGHT));

        centerRightPnl.add(nameLbl);
        centerRightPnl.add(newNameTf);
        centerRightPnl.add(confirmRenameBtn);

        setCenterTitleLblText("Rename Hotel");

        centerPnl.add(centerTitleLbl, BorderLayout.NORTH);
        this.centerPnl.add(manageHotelLeftPanel(), BorderLayout.WEST);
        this.centerPnl.add(centerRightPnl, BorderLayout.EAST);

        this.add(centerPnl);
        this.revalidate();
        this.repaint();
    }

    public void addRoom(){
        this.remove(centerPnl);

        JPanel centerRightPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerRightPnl.setPreferredSize(new Dimension(CENTER_MAIN_WIDTH, CENTER_MAIN_HEIGHT));
        centerPnl = new JPanel();
        centerPnl.setLayout(new BorderLayout());

        JPanel addRoomPnl = new JPanel();
        addRoomPnl.setPreferredSize(new Dimension(CENTER_MAIN_WIDTH, CENTER_MAIN_HEIGHT));
        addRoomPnl.setLayout(new FlowLayout(FlowLayout.CENTER));

        CLabel roomAddLbl = new CLabel("Room Amount       ", SUBTITLE_HEIGHT, Font.BOLD);
        CLabel standardAddLbl = new CLabel("Standard Room         ", SUBTITLE_HEIGHT, Font.PLAIN);
        CLabel deluxeAddLbl = new CLabel("Deluxe Room            ", SUBTITLE_HEIGHT, Font.PLAIN);
        CLabel executiveAddLbl = new CLabel("Executive Room        ", SUBTITLE_HEIGHT, Font.PLAIN);

        this.hotelNameTf = new JTextField();
        this.hotelNameTf.setPreferredSize(new Dimension(TF_WIDTH, TF_HEIGHT));

        this.numStandardTf = new JTextField();
        this.numDeluxeTf = new JTextField();
        this.numExecutiveTf = new JTextField();

        this.numStandardTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));
        this.numDeluxeTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));
        this.numExecutiveTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));

        centerRightPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerRightPnl.setPreferredSize(new Dimension(CENTER_MAIN_WIDTH, CENTER_MAIN_HEIGHT));

        addRoomPnl.add(roomAddLbl);

        addRoomPnl.add(standardAddLbl);
        addRoomPnl.add(numStandardTf);

        addRoomPnl.add(deluxeAddLbl);
        addRoomPnl.add(numDeluxeTf);

        addRoomPnl.add(executiveAddLbl);
        addRoomPnl.add(numExecutiveTf);

        addRoomPnl.add(confirmAddRmBtn);

        centerRightPnl.add(addRoomPnl);

        setCenterTitleLblText("Add Room");

        centerPnl.add(centerTitleLbl, BorderLayout.NORTH);
        centerPnl.add(manageHotelLeftPanel(), BorderLayout.WEST);
        centerPnl.add(centerRightPnl, BorderLayout.EAST);

        this.add(centerPnl);
        this.revalidate();
        this.repaint();
    }

    public void removeRoom(String[] roomNames){
        this.remove(centerPnl);

        JPanel centerRightPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerRightPnl.setPreferredSize(new Dimension(CENTER_MAIN_WIDTH, CENTER_MAIN_HEIGHT));
        JPanel selectionPnl = new JPanel(new FlowLayout());
        JPanel roomPnl = new JPanel();
        this.generalTf = new JTextField();
        centerPnl = new JPanel();
        centerPnl.setLayout(new BorderLayout());

        roomPnl.setPreferredSize(new Dimension(CENTER_MAIN_WIDTH, 175));
        roomPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        selectionPnl.setPreferredSize(new Dimension(CENTER_MAIN_WIDTH, 125));
        selectionPnl.setLayout(new FlowLayout(FlowLayout.CENTER));

        generalTf.setPreferredSize(new Dimension(TF_WIDTH, TF_HEIGHT));

        CLabel selectRoomLbl = new CLabel("Select Room [n]: ", SUBTITLE_HEIGHT, Font.BOLD);
        roomPnl.add(printRooms(roomNames, 170));
        selectionPnl.add(selectRoomLbl, new FlowLayout(FlowLayout.CENTER));
        selectionPnl.add(generalTf, new FlowLayout(FlowLayout.CENTER));
        selectionPnl.add(confirmRemoveRmBtn, new FlowLayout(FlowLayout.CENTER));

        centerRightPnl.add(roomPnl, BorderLayout.NORTH);
        centerRightPnl.add(selectionPnl, BorderLayout.SOUTH);

        setCenterTitleLblText("Remove Room");

        centerPnl.add(centerTitleLbl, BorderLayout.NORTH);
        centerPnl.add(manageHotelLeftPanel(), BorderLayout.WEST);
        centerPnl.add(centerRightPnl, BorderLayout.EAST);

        this.add(centerPnl);
        this.revalidate();
        this.repaint();
    }

    public void updatePrice(double basePrice){
        this.remove(centerPnl);

        JPanel centerRightPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerRightPnl.setPreferredSize(new Dimension(CENTER_MAIN_WIDTH, CENTER_MAIN_HEIGHT));
        centerPnl = new JPanel();
        centerPnl.setLayout(new BorderLayout());

        CLabel currentPriceLbl = new CLabel("Current Base Price: " , SUBTITLE_HEIGHT, Font.BOLD);
        CLabel priceLbl = new CLabel( Double.toString(basePrice) , SUBTITLE_HEIGHT, Font.ITALIC);

        this.generalTf = new JTextField();
        generalTf.setPreferredSize(new Dimension(TF_WIDTH, TF_HEIGHT));

        centerRightPnl.add(currentPriceLbl);
        centerRightPnl.add(priceLbl);
        centerRightPnl.add(new CLabel("New price can not be less than 150" , SUBTITLE_HEIGHT-5, Font.ITALIC));
        centerRightPnl.add(generalTf);
        centerRightPnl.add(confirmUpdatePriceBtn);

        setCenterTitleLblText("Update Price");

        centerPnl.add(centerTitleLbl, BorderLayout.NORTH);
        centerPnl.add(manageHotelLeftPanel(), BorderLayout.WEST);
        centerPnl.add(centerRightPnl, BorderLayout.EAST);

        this.add(centerPnl);
        this.revalidate();
        this.repaint();
    }

    public void datePrice(double[] prices){
        this.remove(centerPnl);

        JPanel centerRightPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerRightPnl.setPreferredSize(new Dimension(CENTER_MAIN_WIDTH, CENTER_MAIN_HEIGHT));
        JPanel selectionPnl = new JPanel(new FlowLayout());
        JPanel pricePnl = new JPanel();
        this.generalTf = new JTextField();
        this.general2Tf = new JTextField();
        centerPnl = new JPanel();
        centerPnl.setLayout(new BorderLayout());

        pricePnl.setPreferredSize(new Dimension(CENTER_MAIN_WIDTH, 175));
        pricePnl.setLayout(new FlowLayout(FlowLayout.LEFT));
        selectionPnl.setPreferredSize(new Dimension(CENTER_MAIN_WIDTH, 150));
        selectionPnl.setLayout(new FlowLayout(FlowLayout.CENTER));

        generalTf.setPreferredSize(new Dimension(TF_WIDTH/2-2, TF_HEIGHT));
        general2Tf.setPreferredSize(new Dimension(TF_WIDTH/2-2, TF_HEIGHT));

        CLabel datePriceLbl = new CLabel("      [Date]     [%Modifier]    ", SUBTITLE_HEIGHT, Font.BOLD);
        pricePnl.add(printPricePerDate(prices, CENTER_MAIN_WIDTH -10, 170));
        selectionPnl.add(new CLabel("New % can range from 50% to 150%", SUBTITLE_HEIGHT-5, Font.ITALIC));
        selectionPnl.add(datePriceLbl, new FlowLayout(FlowLayout.CENTER));
        selectionPnl.add(generalTf, new FlowLayout(FlowLayout.CENTER));
        selectionPnl.add(general2Tf, new FlowLayout(FlowLayout.CENTER));
        selectionPnl.add(confirmDatePriceBtn, new FlowLayout(FlowLayout.CENTER));

        centerRightPnl.add(pricePnl, BorderLayout.NORTH);
        centerRightPnl.add(selectionPnl, BorderLayout.SOUTH);

        setCenterTitleLblText("Update Date Price");

        centerPnl.add(centerTitleLbl, BorderLayout.NORTH);
        centerPnl.add(manageHotelLeftPanel(), BorderLayout.WEST);
        centerPnl.add(centerRightPnl, BorderLayout.EAST);

        this.add(centerPnl);
        this.revalidate();
        this.repaint();
    }

    public void removeReservation(int roomCount, String[][] reservationNames){
        this.remove(centerPnl);

        JPanel centerRightPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerRightPnl.setPreferredSize(new Dimension(CENTER_MAIN_WIDTH, CENTER_MAIN_HEIGHT));
        JPanel selectionPnl = new JPanel(new FlowLayout());
        JPanel resPnl = new JPanel();
        this.generalTf = new JTextField();
        this.general2Tf = new JTextField();
        centerPnl = new JPanel();
        centerPnl.setLayout(new BorderLayout());

        resPnl.setPreferredSize(new Dimension(CENTER_MAIN_WIDTH, 175));
        resPnl.setLayout(new FlowLayout(FlowLayout.LEFT));
        selectionPnl.setPreferredSize(new Dimension(CENTER_MAIN_WIDTH, 125));
        selectionPnl.setLayout(new FlowLayout(FlowLayout.CENTER));

        generalTf.setPreferredSize(new Dimension(TF_WIDTH/2-2, TF_HEIGHT));
        general2Tf.setPreferredSize(new Dimension(TF_WIDTH/2-2, TF_HEIGHT));

        CLabel selectRoomLbl = new CLabel("     [Room]  [Reservation]", SUBTITLE_HEIGHT, Font.BOLD);
        resPnl.add(printReservation(roomCount, reservationNames, CENTER_MAIN_WIDTH -10, 170));
        selectionPnl.add(selectRoomLbl, new FlowLayout(FlowLayout.CENTER));
        selectionPnl.add(generalTf, new FlowLayout(FlowLayout.CENTER));
        selectionPnl.add(general2Tf, new FlowLayout(FlowLayout.CENTER));
        selectionPnl.add(confirmRemoveResBtn, new FlowLayout(FlowLayout.CENTER));

        centerRightPnl.add(resPnl, BorderLayout.NORTH);
        centerRightPnl.add(selectionPnl, BorderLayout.SOUTH);

        setCenterTitleLblText("Remove Reservation");

        centerPnl.add(centerTitleLbl, BorderLayout.NORTH);
        centerPnl.add(manageHotelLeftPanel(), BorderLayout.WEST);
        centerPnl.add(centerRightPnl, BorderLayout.EAST);

        this.add(centerPnl);
        this.revalidate();
        this.repaint();
    }

    public void removeHotel(){
        this.remove(centerPnl);

        JPanel centerRightPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerRightPnl.setPreferredSize(new Dimension(CENTER_MAIN_WIDTH, CENTER_MAIN_HEIGHT));
        centerPnl = new JPanel();
        centerPnl.setLayout(new BorderLayout());

        setCenterTitleLblText("Remove Hotel");

        centerRightPnl.add(new CLabel("Are you sure?", 14, Font.PLAIN));
        centerRightPnl.add(new CLabel("This action cannot be undone", 14, Font.BOLD));
        centerRightPnl.add(confirmRemoveHotelBtn);

        centerPnl.add(centerTitleLbl, BorderLayout.NORTH);
        centerPnl.add(manageHotelLeftPanel(), BorderLayout.WEST);
        centerPnl.add(centerRightPnl, BorderLayout.EAST);

        this.add(centerPnl);
        this.revalidate();
        this.repaint();
    }

    public JScrollPane printPricePerDate(double[] prices, int width, int height){
        int num;

        JPanel mainPricePnl = new JPanel();
        mainPricePnl.setLayout(new BoxLayout(mainPricePnl, BoxLayout.Y_AXIS));
        mainPricePnl.setMaximumSize(new Dimension(width, height));

        for(int i = 0; i < prices.length; i++){
            num = i + 1;
            String lbl = "Day " + num + ": " + prices[i] + "%";
            CLabel room = new CLabel(lbl);
            room.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 12));
            room.setPreferredSize(new Dimension(BTN_WIDTH-10, 20));
            room.setMaximumSize(new Dimension(width, 20));

            mainPricePnl.add(room);

        }

        JScrollPane printRoomsScrPane = new JScrollPane(mainPricePnl);
        printRoomsScrPane.setPreferredSize(new Dimension(width, height));

        return printRoomsScrPane;
    }

    public JScrollPane printRooms(String[] roomNames, int height){
        System.out.println("PRINT RoomS"); // CHECKER
        int num;
        
        JPanel mainRoomPnl = new JPanel();
        mainRoomPnl.setLayout(new BoxLayout(mainRoomPnl, BoxLayout.Y_AXIS));
        mainRoomPnl.setMaximumSize(new Dimension(Short.MAX_VALUE, height));

        for(int i = 0; i < roomNames.length; i++){
            num = i + 1;
            String lbl = "[" + num + "] " + roomNames[i];
            CLabel room = new CLabel(lbl);
            room.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 12));
            room.setPreferredSize(new Dimension(BTN_WIDTH-10, 20));
            room.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));
            
            mainRoomPnl.add(room);
         
        }

        JScrollPane printRoomsScrPane = new JScrollPane(mainRoomPnl);
        printRoomsScrPane.setPreferredSize(new Dimension(BTN_WIDTH+5, height));

        return printRoomsScrPane;
    }

    public JScrollPane printReservation(int roomCount, String[][] reservationNames, int width, int height){
        System.out.println("PRINT Reservations"); // CHECKER
        int num = 0;
        int roomNum = 0;

        JPanel mainRoomPnl = new JPanel();
        mainRoomPnl.setLayout(new BoxLayout(mainRoomPnl, BoxLayout.Y_AXIS));
        mainRoomPnl.setMaximumSize(new Dimension(width, height));

        for(int j = 0; j < roomCount; j++) {
            roomNum = j + 1;
            String roomName = "Room " + "[" + roomNum + "]";
            CLabel room = new CLabel(roomName);
            room.setFont(new Font(DEFAULT_FONT, Font.BOLD, 12));
            room.setPreferredSize(new Dimension(BTN_WIDTH - 10, 20));
            room.setMaximumSize(new Dimension(width, 20));

            mainRoomPnl.add(room);

            for (int i = 0; i < reservationNames[0].length; i++) {
                if(reservationNames[j][i] != null) {

                    num = i + 1;
                    String lbl = "[" + num + "] " + reservationNames[j][i];
                    CLabel res = new CLabel(lbl);
                    res.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 12));
                    res.setPreferredSize(new Dimension(BTN_WIDTH - 10, 20));
                    res.setMaximumSize(new Dimension(width, 20));

                    mainRoomPnl.add(res);
                } else if (i == 0) {
                    CLabel res = new CLabel("No reservations");
                    res.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 12));
                    res.setPreferredSize(new Dimension(BTN_WIDTH - 10, 20));
                    res.setMaximumSize(new Dimension(width, 20));

                    mainRoomPnl.add(res);
                }
            }
        }

        JScrollPane printRoomsScrPane = new JScrollPane(mainRoomPnl);
        printRoomsScrPane.setPreferredSize(new Dimension(width, height));

        return printRoomsScrPane;
    }

    public JScrollPane printReserveConfirmation(String[] breakdown, int height){
        JPanel mainBreakdownPnl = new JPanel();
        mainBreakdownPnl.setLayout(new BoxLayout(mainBreakdownPnl, BoxLayout.Y_AXIS));
        mainBreakdownPnl.setMaximumSize(new Dimension(Short.MAX_VALUE, height));

        for (String line : breakdown) {
            CLabel dayPrompt = new CLabel(line);
            mainBreakdownPnl.add(dayPrompt);
        }

        JScrollPane printBreakdownScrPane = new JScrollPane(mainBreakdownPnl);
        printBreakdownScrPane.setPreferredSize(new Dimension(BTN_WIDTH+5, height));

        return printBreakdownScrPane;
    }

    public void reserveHotel(String[] roomNames, String[] priceBreakdown, int state){
        this.remove(centerPnl);
        this.setTitleLblText("Hotel Reservation");

        this.centerPnl = new JPanel();
        JPanel centerRightPnl = new JPanel();
        JPanel centerLeftPnl = new JPanel();
        JPanel addResPnl = new JPanel();
        JPanel headerPnl = new JPanel();

        centerPnl.setLayout(new BorderLayout());
        centerRightPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerRightPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 300));
        centerLeftPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerLeftPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 300));
        addResPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 300));
        addResPnl.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        headerPnl.setPreferredSize(new Dimension(BTN_WIDTH-50, 20));

        this.nameTf = new JTextField();
        this.generalTf = new JTextField();
        this.numCheckInTf = new JTextField();
        this.numCheckOutTf = new JTextField();
        this.promoCodeTf = new JTextField();
        
        this.nameTf.setPreferredSize(new Dimension(TF_WIDTH, TF_HEIGHT));
        this.generalTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));
        this.numCheckInTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));
        this.numCheckOutTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));
        this.promoCodeTf.setPreferredSize(new Dimension(TF_WIDTH, TF_HEIGHT));


        CLabel nameLbl = new CLabel("Guest Name");
        CLabel roomReserveLbl = new CLabel("List of Rooms");
        CLabel checkInLbl = new CLabel("Check In Date "); 
        CLabel checkOutLbl = new CLabel("Check Out Date");
        CLabel promoCodeLbl = new CLabel("Promo Code");
        CLabel roomIndexLbl = new CLabel("Room [Number] to Reserve");  
        CLabel breakdownLbl = new CLabel("Price Breakdown");


        centerLeftPnl.add(nameLbl);
        centerLeftPnl.add(nameTf);

        centerLeftPnl.add(roomIndexLbl);
        centerLeftPnl.add(generalTf);

        centerLeftPnl.add(addResPnl);
        
        addResPnl.add(checkInLbl);
        addResPnl.add(this.numCheckInTf);

        addResPnl.add(checkOutLbl);
        addResPnl.add(numCheckOutTf);

        addResPnl.add(promoCodeLbl);
        addResPnl.add(promoCodeTf);

        addResPnl.add(this.confirmResBtn);
        
       

        if(state == 1){
            headerPnl.add(roomReserveLbl);
            centerRightPnl.add(headerPnl);
            centerRightPnl.add(printRooms(roomNames, 350));
        }
        
        if(state == 2){
            headerPnl.add(breakdownLbl);
            centerRightPnl.add(headerPnl);
            centerRightPnl.add(printReserveConfirmation(priceBreakdown, 350));
        }
     
        centerPnl.add(centerLeftPnl, BorderLayout.WEST);
        centerPnl.add(centerRightPnl, BorderLayout.EAST);
        this.add(centerPnl);


        this.revalidate();
        this.repaint();
    }

    public void setCreateListener(ActionListener actionListener){
        this.createBtn.addActionListener(actionListener);
    }

    public void setOpenListener(ActionListener actionListener){
        this.openBtn.addActionListener(actionListener);
    }

    public void setConfirmListener(ActionListener actionListener){
        this.confirmBtn.addActionListener(actionListener);
    }

    public void setSelectListener(ActionListener actionListener){
        this.selectBtn.addActionListener(actionListener);
    }

    public void setBackListener(ActionListener actionListener){
        this.backBtn.addActionListener(actionListener);
    }

    public void setInquireListener(ActionListener actionListener){
        this.inquireBtn.addActionListener(actionListener);
    }

    public void setManageListener(ActionListener actionListener){
        this.manageBtn.addActionListener(actionListener);
    }

    public void setReserveListener(ActionListener actionListener){
        this.reserveBtn.addActionListener(actionListener);
    }

    public void setConfirmResListener(ActionListener actionListener){
        this.confirmResBtn.addActionListener(actionListener);
    }

    public void setFinalizeResListener(ActionListener actionListener){
        this.finalizeResButton.addActionListener(actionListener);
    }

    public void setCancelResListener(ActionListener actionListener){
        this.cancelResButton.addActionListener(actionListener);
    }

    public void setInquireHotelListener(ActionListener actionListener){
        this.iHotelBtn.addActionListener(actionListener);
    }

    public void setInquireRoomListener(ActionListener actionListener){
        this.iRoomBtn.addActionListener(actionListener);
    }

    public void setInquireReservationListener(ActionListener actionListener){
        this.iResBtn.addActionListener(actionListener);
    }

    public void setInquireDateListener(ActionListener actionListener){
        this.iDateBtn.addActionListener(actionListener);
    }

    public void setRenameHotelListener(ActionListener actionListener){
        this.renameBtn.addActionListener(actionListener);
    }

    public void setAddRoomListener(ActionListener actionListener){
        this.addRoomBtn.addActionListener(actionListener);
    }

    public void setRemoveRoomListener(ActionListener actionListener){
        this.removeRoomBtn.addActionListener(actionListener);
    }

    public void setUpdatePriceListener(ActionListener actionListener){
        this.updatePriceBtn.addActionListener(actionListener);
    }

    public void setDatePriceListener(ActionListener actionListener){
        this.datePriceBtn.addActionListener(actionListener);
    }

    public void setRemoveResListener(ActionListener actionListener){
        this.removeReservationBtn.addActionListener(actionListener);
    }

    public void setRemoveHotelListener(ActionListener actionListener){
        this.removeHotelBtn.addActionListener(actionListener);
    }

    public void setConfirmRenameListener(ActionListener actionListener){
        this.confirmRenameBtn.addActionListener(actionListener);
    }

    public void setConfirmAddRmBtnListener(ActionListener actionListener){
        this.confirmAddRmBtn.addActionListener(actionListener);
    }

    public void setConfirmRemoveRmListener(ActionListener actionListener){
        this.confirmRemoveRmBtn.addActionListener(actionListener);
    }

    public void setConfirmUpdatePriceListener(ActionListener actionListener){
        this.confirmUpdatePriceBtn.addActionListener(actionListener);
    }

    public void setConfirmDatePriceListener(ActionListener actionListener){
        this.confirmDatePriceBtn.addActionListener(actionListener);
    }

    public void setConfirmRemoveResListener(ActionListener actionListener){
        this.confirmRemoveResBtn.addActionListener(actionListener);
    }

    public void setConfirmRemoveHotelListener(ActionListener actionListener){
        this.confirmRemoveHotelBtn.addActionListener(actionListener);
    }

    //return feedback
    public void setFeedbackLblText(String text) {
        this.feedbackLbl.setText(text);
    }

    public void setTitleLblText(String text) {
        this.titleLbl.setText(text);
    }

    public void setCenterTitleLblText(String text) {
        this.centerTitleLbl.setText(text);
    }

    // GET TF HOTELNAME
    public String getHotelNameTfText() {
        return this.hotelNameTf.getText();
    }

    public String getNewNameTfText() {
        return this.newNameTf.getText();
    }

    public String getGeneral2TfText() {
        return this.general2Tf.getText();
    }

    public String getNumStandardTf(){
        return this.numStandardTf.getText();
    }

    public String getNumDeluxeTf(){
        return this.numDeluxeTf.getText();
    }

    public String getNumExecutiveTf(){
        return this.numExecutiveTf.getText();
    }

    public String getNumCheckInTf(){
        return this.numCheckInTf.getText();
    }

    public String getNumCheckOutTf(){
        return this.numCheckOutTf.getText();
    }

    public String getGeneralTf(){
        return this.generalTf.getText();
    }

    public String getPromoCodeTf(){
        return this.promoCodeTf.getText();
    }

    public String getNameTf(){
        return this.nameTf.getText();
    }
}