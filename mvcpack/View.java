package mvcpack;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class View extends JFrame {
    private JLabel createLbl, openLbl, feedbackLbl;
    private JTextField hotelNameTf;
    private JButton createBtn, openBtn, confirmBtn, selectBtn;
    private JPanel northPnl, southPnl, westPnl, eastPnl, centerPnl;

    //open hotel
    //inquire
    private JButton inquireBtn, manageBtn, reserveBtn;
    
    private JButton iHotelBtn, iRoomBtn, iResBtn, iDateBtn, backBtn;
    private JButton renameBtn, addRoomBtn, removeRoomBtn;
    
    private final int TF_WIDTH = 400;
    private final int BTN_WIDTH = 220;
    private final int BTN_HEIGHT = 60;
    private final int TF_HEIGHT = 40;
    

    public View() {
        super("Hotel Reservation System");
        setLayout(new BorderLayout());
        setSize(700,500);
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
        
        //SOUTH PANEL
        this.feedbackLbl = new JLabel();
        this.feedbackLbl.setPreferredSize(new Dimension(450, TF_HEIGHT));
        this.feedbackLbl.setFont(new Font("Arial", Font.BOLD, 15));
        this.feedbackLbl.setHorizontalAlignment(SwingConstants.CENTER);
        this.feedbackLbl.setVerticalAlignment(SwingConstants.CENTER);
        
        southPnl = new JPanel();
        southPnl.setLayout(new FlowLayout());
        southPnl.setPreferredSize(new Dimension(400,TF_HEIGHT+10));
        southPnl.add(feedbackLbl);
        
        //ADD EVERYTHING
        this.add(centerPnl, BorderLayout.CENTER);
        this.add(westPnl, BorderLayout.WEST);
        this.add(southPnl, BorderLayout.SOUTH);

        this.revalidate();
        this.repaint();

    }

    // TODO BACK BUTTON
    public void home(){

        
    }

    // CREATE HOTEL PRESSED
    public void createHotel(){
        this.remove(centerPnl);
        
        centerPnl = new JPanel();
        centerPnl.setLayout(new FlowLayout(FlowLayout.CENTER));

        // add text fields
        
        this.hotelNameTf = new JTextField();
        this.hotelNameTf.setPreferredSize(new Dimension(TF_WIDTH, TF_HEIGHT));
        this.createLbl = new JLabel("Hotel Name: ");

        centerPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerPnl.add(createLbl);
        centerPnl.add(hotelNameTf);
        centerPnl.add(confirmBtn);

        this.add(centerPnl, BorderLayout.CENTER);
        centerPnl.revalidate();
        centerPnl.repaint();
    }

    // OPEN HOTEL PRESSED
    public void printHotels(String[] hotelNames){
        System.out.println("PRINT HOTELS"); // CHECKER
        int num;

        this.remove(centerPnl);
        centerPnl = new JPanel();
        this.hotelNameTf = new JTextField();
        this.hotelNameTf.setPreferredSize(new Dimension(TF_WIDTH, TF_HEIGHT));
        centerPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        this.openLbl = new JLabel("Hotels in the system: ", SwingConstants.CENTER);
        centerPnl.add(openLbl);
        
        for(int i = 0; i < hotelNames.length; i++){
            num = i + 1;
            JLabel hotel = new JLabel("[" + num + "] " + hotelNames[i]);
            centerPnl.add(hotel);
        }

        centerPnl.add(hotelNameTf);
        centerPnl.add(selectBtn);
        this.add(centerPnl);
        centerPnl.revalidate();
        centerPnl.repaint();
    }

    // there is a selected hotel
    public void openHotel(String hotelName){
        System.out.println("OPEN HOTEL");

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

    // GET TF HOTELNAME
    public String getHotelNameTfText() {
        return this.hotelNameTf.getText();
    }

    //CLEAR ALL FIELDS
    public void clearFields() {
        this.hotelNameTf.setText("");
        setFeedbackLblText("");
    }

}