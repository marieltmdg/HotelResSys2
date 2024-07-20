package mvcpack;

import mvcpack.custompack.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class View extends JFrame {
    private CLabel createLbl, openLbl, feedbackLbl, titleLbl, centerTitleLbl;
    private JTextField hotelNameTf, numStandardTf, numDeluxeTf, numExecutiveTf,
                        numCheckInTf, numCheckOutTf, roomIndexTf, promoCodeTf;
    private CButton createBtn, openBtn, confirmBtn, selectBtn;
    private JPanel northPnl, southPnl, westPnl, eastPnl, centerPnl;

    //open hotel
    //inquire
    private CButton inquireBtn, manageBtn, reserveBtn;
    
    private CButton iHotelBtn, iRoomBtn, iResBtn, iDateBtn, backBtn;
    private CButton renameBtn, addRoomBtn, removeRoomBtn;

    private CButton confirmResBtn;
    
    private final int SMALL_TF_WIDTH = 50;
    private final int TF_WIDTH = 200;
    private final int BTN_WIDTH = 210;
    private final int BTN_HEIGHT = 60;
    private final int TF_HEIGHT = 35;
    private final int MAINFRAME_WIDTH = 700;
    private final int MAINFRAME_HEIGHT = 500;
    private final String DEFAULT_FONT = "Verdana";
    private final int SUBTITLE_HEIGHT = 15;


    public View() {
        super("Hotel Reservation System");
        setLayout(new BorderLayout());
        setSize( MAINFRAME_WIDTH,MAINFRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
        setVisible(true);
    }

    //initial values when opening
    private void init() {

        //BUTTONS
        this.confirmBtn = new CButton("Confirm");
        this.selectBtn = new CButton("Select");
        inquireBtn = new CButton("Inquire");
        manageBtn = new CButton("Manage");
        reserveBtn = new CButton("Reserve");
        iDateBtn = new CButton("Dates");
        iHotelBtn = new CButton("Hotel");
        iResBtn = new CButton("Reservation");
        iRoomBtn = new CButton("Room");
        //westpnl
        backBtn = new CButton("Back");

        //ctrright pnl
        renameBtn = new CButton("Rename Hotel");
        addRoomBtn = new CButton("Add room");
        removeRoomBtn = new CButton("Remove room");

        //res pnl
        confirmResBtn = new CButton("Confirm");
        
        this.inquireBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        this.manageBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        this.reserveBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));

        this.confirmBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        this.selectBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        this.iDateBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        this.iHotelBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        this.iResBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        this.iRoomBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        this.backBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        this.renameBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        this.addRoomBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        this.removeRoomBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        this.confirmResBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));

        centerPnl = new JPanel();

        //WEST PANEL
        this.createBtn = new CButton("Create a Hotel");
        this.openBtn = new CButton("Open a Hotel");
        this.createBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        this.openBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
        
        westPnl = new JPanel();
        westPnl.setLayout(new FlowLayout());
        westPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 200));
        westPnl.setBackground(Color.decode("#1B384B"));
        westPnl.add(createBtn);
        westPnl.add(openBtn);

        //EAST PANEL
        
        //NORTH PANEL
        this.titleLbl = new CLabel();
        this.titleLbl.setPreferredSize(new Dimension(MAINFRAME_WIDTH, TF_HEIGHT + 10));
        this.titleLbl.setFont(new Font(DEFAULT_FONT, Font.BOLD, 30));
        this.titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        this.titleLbl.setVerticalAlignment(SwingConstants.CENTER);
        this.titleLbl.setBackground(Color.decode("#1B384B"));

        setTitleLblText("Main Menu");

        this.centerTitleLbl = new CLabel();
        this.centerTitleLbl.setPreferredSize(new Dimension(MAINFRAME_WIDTH, TF_HEIGHT + 10));
        this.centerTitleLbl.setFont(new Font(DEFAULT_FONT, Font.BOLD, 25));
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

    // TODO BACK BUTTON
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
    public JScrollPane printHotels(String[] hotelNames){
        System.out.println("PRINT HOTELS"); // CHECKER
        int num;

        JPanel mainHotelPnl = new JPanel();
        mainHotelPnl.setLayout(new BoxLayout(mainHotelPnl, BoxLayout.Y_AXIS));
        mainHotelPnl.setMaximumSize(new Dimension(Short.MAX_VALUE, 350));
        
        for(int i = 0; i < hotelNames.length; i++){
            num = i + 1;
            String lbl = "[" + num + "] " + hotelNames[i];

            CLabel hotel = new CLabel(lbl);
            hotel.setPreferredSize(new Dimension(BTN_WIDTH-10, 20));
            hotel.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));
            
 
            mainHotelPnl.add(hotel);
         
        }

        JScrollPane printHotelsScrPane = new JScrollPane(mainHotelPnl);
        printHotelsScrPane.setPreferredSize(new Dimension(BTN_WIDTH+5, 320));

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
        JPanel addRoomPnl = new JPanel();
        JPanel headerPnl = new JPanel();
       
        CLabel roomAddLbl = new CLabel("<html>Add Rooms</html>", SUBTITLE_HEIGHT, Font.BOLD);
        CLabel standardAddLbl = new CLabel("Standard Room", SUBTITLE_HEIGHT, Font.BOLD);
        CLabel deluxeAddLbl = new CLabel("Deluxe Room", SUBTITLE_HEIGHT, Font.BOLD);
        CLabel executiveAddLbl = new CLabel("Executive Room", SUBTITLE_HEIGHT, Font.BOLD);

        centerRightPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerRightPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 300));
        centerLeftPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerLeftPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 300));
        addRoomPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 300));
        addRoomPnl.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        headerPnl.setPreferredSize(new Dimension(BTN_WIDTH, TF_HEIGHT));

        // add text fields
        
        this.hotelNameTf = new JTextField();
        this.numStandardTf = new JTextField();
        this.numDeluxeTf = new JTextField();
        this.numExecutiveTf = new JTextField();

        this.hotelNameTf.setPreferredSize(new Dimension(TF_WIDTH, TF_HEIGHT));
        this.numStandardTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));
        this.numDeluxeTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));
        this.numExecutiveTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));

        this.createLbl = new CLabel("Hotel Name: ",SUBTITLE_HEIGHT, Font.BOLD);
        centerLeftPnl.add(createLbl);
        centerLeftPnl.add(hotelNameTf);

        centerLeftPnl.add(roomAddLbl);

        centerLeftPnl.add(addRoomPnl);

        addRoomPnl.add(standardAddLbl);
        addRoomPnl.add(numStandardTf);

        addRoomPnl.add(deluxeAddLbl);
        addRoomPnl.add(numDeluxeTf);

        addRoomPnl.add(executiveAddLbl);
        addRoomPnl.add(numExecutiveTf);

        addRoomPnl.add(confirmBtn);

        this.openLbl = new CLabel("Hotels in the system: ", SUBTITLE_HEIGHT, Font.BOLD);
        headerPnl.add(openLbl);
        centerRightPnl.add(headerPnl);
        centerRightPnl.add(printHotels(hotelNames));

        centerPnl.add(centerLeftPnl, BorderLayout.WEST);
        centerPnl.add(centerRightPnl, BorderLayout.EAST);
        this.add(centerPnl);

        centerPnl.revalidate();
        centerPnl.repaint();
        addRoomPnl.revalidate();
        addRoomPnl.repaint();
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
        centerRightPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 300));
        centerLeftPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerLeftPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 300));

        this.hotelNameTf = new JTextField();
        this.hotelNameTf.setPreferredSize(new Dimension(TF_WIDTH, TF_HEIGHT));
        centerLeftPnl.add(printHotels(hotelNames));

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
        centerLeftPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 300));

        centerLeftPnl.add(iHotelBtn);
        centerLeftPnl.add(iRoomBtn);
        centerLeftPnl.add(iResBtn);
        centerLeftPnl.add(iDateBtn);

        return centerLeftPnl;
    }

    public void inquireHotel(){
        this.remove(this.centerPnl);
        this.remove(this.southPnl);
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
        centerRightPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 300));

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

        centerPnl.add(inquireHotelLeftPanel(), BorderLayout.WEST);
        centerPnl.add(centerRightPnl, BorderLayout.EAST);

        this.add(centerPnl, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    public void manageHotel(){
        System.out.println("MANAGE HOTEL");//checker
        
        this.remove(this.centerPnl);
        this.remove(this.southPnl);
        centerPnl = new JPanel();
        JPanel centerLeftPnl = new JPanel();

        //ctrleft panel

        centerPnl.setLayout(new BorderLayout());
        centerLeftPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerLeftPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 300));

        centerLeftPnl.add(renameBtn);
        centerLeftPnl.add(addRoomBtn);
        centerLeftPnl.add(removeRoomBtn);

        this.centerPnl.add(centerLeftPnl, BorderLayout.WEST);
        this.add(centerPnl);

        this.revalidate();
        this.repaint();
    }

    public JScrollPane printRooms(String[] roomNames){
        System.out.println("PRINT RoomS"); // CHECKER
        int num;
        
        JPanel mainRoomPnl = new JPanel();
        mainRoomPnl.setLayout(new BoxLayout(mainRoomPnl, BoxLayout.Y_AXIS));
        mainRoomPnl.setMaximumSize(new Dimension(Short.MAX_VALUE, 350));

        for(int i = 0; i < roomNames.length; i++){
            num = i + 1;
            String lbl = "[" + num + "] " + roomNames[i];
            CLabel room = new CLabel(lbl);
            room.setPreferredSize(new Dimension(BTN_WIDTH-10, 20));
            room.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));
            
            mainRoomPnl.add(room);
         
        }

        JScrollPane printRoomsScrPane = new JScrollPane(mainRoomPnl);
        printRoomsScrPane.setPreferredSize(new Dimension(BTN_WIDTH+5, 320));

        return printRoomsScrPane;
    }

    public void reserveHotel(String[] roomNames){
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

        this.roomIndexTf = new JTextField();
        this.numCheckInTf = new JTextField();
        this.numCheckOutTf = new JTextField();
        this.promoCodeTf = new JTextField();
        
        this.roomIndexTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));
        this.numCheckInTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));
        this.numCheckOutTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));
        this.promoCodeTf.setPreferredSize(new Dimension(TF_WIDTH, TF_HEIGHT));

        CLabel roomReserveLbl = new CLabel("List of Rooms");
        CLabel checkInLbl = new CLabel("Check In Date"); 
        CLabel checkOutLbl = new CLabel("Check Out Date");
        CLabel promoCodeLbl = new CLabel("Promo Code");
        CLabel roomIndexLbl = new CLabel("Room Index to Reserve");  

        centerLeftPnl.add(roomIndexLbl);
        centerLeftPnl.add(roomIndexTf);

        centerLeftPnl.add(addResPnl);
        
        addResPnl.add(checkInLbl);
        addResPnl.add(this.numCheckInTf);

        addResPnl.add(checkOutLbl);
        addResPnl.add(numCheckOutTf);

        addResPnl.add(promoCodeLbl);
        addResPnl.add(promoCodeTf);

        addResPnl.add(confirmResBtn);
        
        headerPnl.add(roomReserveLbl);
        centerRightPnl.add(headerPnl);
        centerRightPnl.add(printRooms(roomNames));

        centerPnl.add(centerLeftPnl, BorderLayout.WEST);
        centerPnl.add(centerRightPnl, BorderLayout.EAST);
        this.add(centerPnl);


        this.revalidate();
        this.repaint();
    }

    // LISTENER FOR CREATE
    public void setCreateListener(ActionListener actionListener){
        this.createBtn.addActionListener(actionListener);
    }

    // LISTENER FOR OPEN
    public void setOpenListener(ActionListener actionListener){
        this.openBtn.addActionListener(actionListener);
    }

    //LISTENER FOR CONFIRM
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

    public String getNumStandardTf(){
        return this.numStandardTf.getText();
    }

    public String getNumDeluxeTf(){
        return this.numDeluxeTf.getText();
    }

    public String getNumExecutiveTf(){
        return this.numExecutiveTf.getText();
    }

    public int getNumCheckInTf(){
        return Integer.parseInt(this.numCheckInTf.getText());
    }

    public int genNumCheckOutTf(){
        return Integer.parseInt(this.numCheckOutTf.getText());
    }

    public int getRoomIndexTf(){
        return Integer.parseInt(this.roomIndexTf.getText());
    }

    public String getPromoCodeTf(){
        return this.promoCodeTf.getText();
    }
    
    //CLEAR ALL FIELDS
    public void clearFields() {
        this.hotelNameTf.setText("");
        setFeedbackLblText("");
    }

}