package mvcpack;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class View extends JFrame {
    private JLabel createLbl, openLbl, feedbackLbl, titleLbl;
    private JTextField hotelNameTf, numStandardTf, numDeluxeTf, numExecutiveTf;
    private JButton createBtn, openBtn, confirmBtn, selectBtn;
    private JPanel northPnl, southPnl, westPnl, eastPnl, centerPnl;

    //open hotel
    //inquire
    private JButton inquireBtn, manageBtn, reserveBtn;
    
    private JButton iHotelBtn, iRoomBtn, iResBtn, iDateBtn, backBtn;
    private JButton renameBtn, addRoomBtn, removeRoomBtn;
    
    private final int SMALL_TF_WIDTH = 50;
    private final int TF_WIDTH = 200;
    private final int BTN_WIDTH = 210;
    private final int BTN_HEIGHT = 60;
    private final int TF_HEIGHT = 35;
    private final int MAINFRAME_WIDTH = 700;
    private final int MAINFRAME_HEIGHT = 500;
    

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
        this.confirmBtn = new JButton("Confirm");
        this.selectBtn = new JButton("Select");
        inquireBtn = new JButton("Inquire");
        manageBtn = new JButton("Manage");
        reserveBtn = new JButton("Reserve");
        iDateBtn = new JButton("Inquire Dates");
        iHotelBtn = new JButton("Inquire Hotel");
        iResBtn = new JButton("Inquire Reservation");
        iRoomBtn = new JButton("Inquire Room");
        //westpnl
        backBtn = new JButton("Back");

        //ctrright pnl
        renameBtn = new JButton("Rename Hotel");
        addRoomBtn = new JButton("Add room");
        removeRoomBtn = new JButton("Remove room");
        
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

        centerPnl = new JPanel();

        //WEST PANEL
        this.createBtn = new JButton("CREATE A HOTEL");
        this.openBtn = new JButton("OPEN A HOTEL");
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
        this.titleLbl = new JLabel();
        this.titleLbl.setPreferredSize(new Dimension(MAINFRAME_WIDTH, TF_HEIGHT + 10));
        this.titleLbl.setFont(new Font("Arial", Font.BOLD, 30));
        this.titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        this.titleLbl.setVerticalAlignment(SwingConstants.CENTER);
        this.titleLbl.setBackground(Color.decode("#1B384B"));

        setTitleLblText("Main Menu");

        northPnl = new JPanel();
        northPnl.setLayout(new FlowLayout());
        northPnl.setPreferredSize(new Dimension(MAINFRAME_WIDTH,TF_HEIGHT+20));
        northPnl.setBackground(Color.decode("#1B384B"));
        northPnl.add(titleLbl);

        //SOUTH PANEL
        this.feedbackLbl = new JLabel();
        this.feedbackLbl.setPreferredSize(new Dimension(MAINFRAME_WIDTH, TF_HEIGHT + 10));
        this.feedbackLbl.setFont(new Font("Arial", Font.BOLD, 15));
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
        
    }

    
  // PRINT HOTEL PANEL
    public JScrollPane printHotels(String[] hotelNames){
        System.out.println("PRINT HOTELS"); // CHECKER
        int num;

        JPanel mainHotelPnl = new JPanel();
        mainHotelPnl.setLayout(new BoxLayout(mainHotelPnl, BoxLayout.Y_AXIS));
        mainHotelPnl.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));
        
        for(int i = 0; i < hotelNames.length; i++){
            num = i + 1;
            String lbl = "[" + num + "] " + hotelNames[i];

            JLabel hotel = new JLabel(lbl);
            hotel.setPreferredSize(new Dimension(BTN_WIDTH-10, 20));
            hotel.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));
            
 
            mainHotelPnl.add(hotel);
         
        }

        JScrollPane printHotelsScrPane = new JScrollPane(mainHotelPnl);
        printHotelsScrPane.setPreferredSize(new Dimension(BTN_WIDTH+5, 300));

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
       
        JLabel roomAddLbl = new JLabel("<html>Add Rooms</html>");
        JLabel standardAddLbl = new JLabel("Standard Room"); 
        JLabel deluxeAddLbl = new JLabel("Deluxe Room");
        JLabel executiveAddLbl = new JLabel("Executive Room");

        centerRightPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerRightPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 300));
        centerLeftPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerLeftPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 300));
        addRoomPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 300));
        addRoomPnl.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        headerPnl.setPreferredSize(new Dimension(BTN_WIDTH-50, 20));


        // add text fields
        
        this.hotelNameTf = new JTextField();
        this.numStandardTf = new JTextField();
        this.numDeluxeTf = new JTextField();
        this.numExecutiveTf = new JTextField();

        this.hotelNameTf.setPreferredSize(new Dimension(TF_WIDTH, TF_HEIGHT));
        this.numStandardTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));
        this.numDeluxeTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));
        this.numExecutiveTf.setPreferredSize(new Dimension(SMALL_TF_WIDTH, TF_HEIGHT));


        this.createLbl = new JLabel("Hotel Name: ");
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

        this.openLbl = new JLabel("Hotels in the system: ");
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
        JLabel selectHotelLbl = new JLabel("Indicate number [n] of  hotel: ");
       

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

        this.westPnl.remove(this.createBtn);
        this.westPnl.remove(this.openBtn);

        westPnl.add(backBtn);
        this.westPnl.add(inquireBtn);
        this.westPnl.add(manageBtn);
        this.westPnl.add(reserveBtn);
        this.revalidate();
        this.repaint();
    }

    public void inquireHotel(){
        this.remove(this.centerPnl);
        this.remove(this.southPnl);
        centerPnl = new JPanel();
        JPanel centerLeftPnl = new JPanel();
        //ctrleft panel

        centerPnl.setLayout(new BorderLayout());
        centerLeftPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerLeftPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 300));

        centerLeftPnl.add(iDateBtn);
        centerLeftPnl.add(iHotelBtn);
        centerLeftPnl.add(iResBtn);
        centerLeftPnl.add(iRoomBtn);

        this.centerPnl.add(centerLeftPnl, BorderLayout.WEST);

        this.add(westPnl, BorderLayout.WEST);
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

    public void reserveHotel(){
        this.remove(centerPnl);
        this.setTitleLblText("Hotel Reservation");

        this.westPnl.remove(this.createBtn);
        this.westPnl.remove(this.openBtn);

        this.centerPnl = new JPanel();



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
    //CLEAR ALL FIELDS
    public void clearFields() {
        this.hotelNameTf.setText("");
        setFeedbackLblText("");
    }

}