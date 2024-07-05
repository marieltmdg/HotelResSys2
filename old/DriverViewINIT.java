import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DriverViewINIT {
    private JFrame mainFrame;
    private JLabel createLbl, feedbackLbl;
    private JTextField hotelNameTf;
    private JButton createBtn, openBtn;
    private final int TF_WIDTH = 150;
    private final int BTN_WIDTH = 150;

    public DriverViewINIT(){
        this.mainFrame = new JFrame("Hotel Reservation System");

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new FlowLayout());
        this.mainFrame.setSize(500, 300);

        this.createLbl = new JLabel("Enter Hotel Name: ");
        this.feedbackLbl = new JLabel();
        this.feedbackLbl.setPreferredSize(new Dimension(220, 30));

        this.hotelNameTf = new JTextField();
        this.hotelNameTf.setPreferredSize(new Dimension(TF_WIDTH, 30));

        //initialize buttons
        this.createBtn = new JButton("CREATE HOTEL");
        this.createBtn.setPreferredSize(new Dimension(BTN_WIDTH, 30));
        this.openBtn = new JButton("OPEN HOTEL");
        this.openBtn.setPreferredSize(new Dimension(BTN_WIDTH, 30));

        //panel
        JPanel buttonPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPnl.add(this.createBtn);
        buttonPnl.add(this.openBtn);
        JPanel inputPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPnl.add(this.createLbl);
        inputPnl.add(this.hotelNameTf);

        JPanel feedbackPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        feedbackPnl.add(this.feedbackLbl);

        this.mainFrame.add(buttonPnl);
        this.mainFrame.add(inputPnl);
        this.mainFrame.add(feedbackPnl);

        //make the mainframe visible
        this.mainFrame.setVisible(true);
    }

    // listen create btn
    public void setCreateBtnListener(ActionListener actionListener){
        this.createBtn.addActionListener(actionListener);
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