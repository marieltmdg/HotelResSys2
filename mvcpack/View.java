package mvcpack;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class View extends JFrame {
    private JLabel createLbl, openLbl, feedbackLbl;
    private JTextField hotelNameTf;
    private JButton createBtn, openBtn, confirmBtn;
    private JPanel northPnl, southPnl, westPnl, eastPnl, centerPnl;
    
    private final int TF_WIDTH = 150;
    private final int BTN_WIDTH = 150;

    public View() {
        super("Hotel Reservation System");
        setLayout(new BorderLayout());
        setSize(450,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
        setVisible(true);
    }

    //initial values when opening
    private void init() {
        //NORTH PANEL

        //CENTER PANEL
        this.confirmBtn = new JButton("Confirm Creation");

        centerPnl = new JPanel();

        //WEST PANEL
        this.createBtn = new JButton("CREATE A HOTEL");
        this.openBtn = new JButton("OPEN A HOTEL");
        this.createBtn.setPreferredSize(new Dimension(BTN_WIDTH, 30));
        this.openBtn.setPreferredSize(new Dimension(BTN_WIDTH, 30));
        
        westPnl = new JPanel();
        westPnl.setLayout(new FlowLayout());
        westPnl.setPreferredSize(new Dimension(BTN_WIDTH+10, 200));
        westPnl.setBackground(Color.decode("#1B384B"));
        westPnl.add(createBtn);
        westPnl.add(openBtn);

        //EAST PANEL
        
        //SOUTH PANEL
        this.feedbackLbl = new JLabel();
        this.feedbackLbl.setPreferredSize(new Dimension(450, 30));
        this.feedbackLbl.setFont(new Font("Arial", Font.BOLD, 15));
        this.feedbackLbl.setHorizontalAlignment(SwingConstants.CENTER);
        this.feedbackLbl.setVerticalAlignment(SwingConstants.CENTER);
        
        southPnl = new JPanel();
        southPnl.setLayout(new FlowLayout());
        southPnl.setPreferredSize(new Dimension(400,50));
        southPnl.add(feedbackLbl);
        
        //ADD EVERYTHING
        this.add(centerPnl, BorderLayout.CENTER);
        this.add(westPnl, BorderLayout.WEST);
        this.add(southPnl, BorderLayout.SOUTH);
    }

    // CREATE HOTEL PRESSED
    public void createHotel(){
        this.remove(centerPnl);
        
        centerPnl = new JPanel();
        centerPnl.setLayout(new FlowLayout(FlowLayout.CENTER));

        // add text fields

        this.hotelNameTf = new JTextField();
        this.hotelNameTf.setPreferredSize(new Dimension(TF_WIDTH, 30));
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
        centerPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        this.openLbl = new JLabel("Hotels in the system: ", SwingConstants.CENTER);
        centerPnl.add(openLbl);
        
        for(int i = 0; i < hotelNames.length; i++){
            num = i + 1;
            JLabel hotel = new JLabel("[" + num + "] " + hotelNames[i]);
            centerPnl.add(hotel);
        }
        
        this.add(centerPnl);
        centerPnl.revalidate();
        centerPnl.repaint();
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