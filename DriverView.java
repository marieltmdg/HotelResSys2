import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DriverView extends JFrame {
    private JLabel createLbl, feedbackLbl;
    private JTextField hotelNameTf;
    private JButton createBtn, openBtn;
    
    private final int TF_WIDTH = 150;
    private final int BTN_WIDTH = 150;

    public DriverView() {
        super("Hotel Reservation System");
        setLayout(new BorderLayout());
        setSize(450,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
        setVisible(true);
        setResizable(false);
    }

    private void init() {
        //NORTH PANEL

        //CENTER PANEL
       
        // add text fields
        this.hotelNameTf = new JTextField();
        this.hotelNameTf.setPreferredSize(new Dimension(TF_WIDTH, 30));
        this.createLbl = new JLabel("Hotel Name: ");

        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelCenter.add(createLbl);
        panelCenter.add(hotelNameTf);

        //WEST PANEL
        this.createBtn = new JButton("CREATE A HOTEL");
        this.openBtn = new JButton("OPEN A HOTEL");
        this.createBtn.setPreferredSize(new Dimension(BTN_WIDTH, 30));
        this.openBtn.setPreferredSize(new Dimension(BTN_WIDTH, 30));
        
        JPanel panelWest = new JPanel();
        panelWest.setLayout(new FlowLayout());
        panelWest.setPreferredSize(new Dimension(BTN_WIDTH+10, 200));
        panelWest.setBackground(Color.decode("#1B384B"));
        panelWest.add(createBtn);
        panelWest.add(openBtn);

        //EAST PANEL
        
        
        //SOUTH PANEL
        this.feedbackLbl = new JLabel();
        this.feedbackLbl.setPreferredSize(new Dimension(220, 50));
        this.feedbackLbl.setFont(new Font("Arial", Font.BOLD, 15));
        this.feedbackLbl.setHorizontalAlignment(SwingConstants.CENTER);
        this.feedbackLbl.setVerticalAlignment(SwingConstants.CENTER);
        
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout());
        panelSouth.setPreferredSize(new Dimension(400,50));
        panelSouth.add(feedbackLbl);
        
        //ADD EVERYTHING
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelWest, BorderLayout.WEST);
        this.add(panelSouth, BorderLayout.SOUTH);
    }

     // listen create btn
     public void setActionListener(ActionListener actionListener){
        this.createBtn.addActionListener(actionListener);
        this.openBtn.addActionListener(actionListener);
    }

    //return feedback
    public void setFeedbackLblText(String text) {
        this.feedbackLbl.setText(text);
    }

    public void setOpenBtnListener(ActionListener actionListener){
        this.openBtn.addActionListener(actionListener);
    }

    public String getHotelNameTfText() {
        return this.hotelNameTf.getText();
    }

    public void clearTextFields() {
        this.hotelNameTf.setText("");
    }

}