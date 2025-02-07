package mvcpack.custompack;

import java.awt.*;
import javax.swing.*;

/**
 * A Custom Button class.
 */
public class CButton extends JButton {
    public CButton(){
        super();
    }

    public CButton(String x){
        super(x);
        
        setBackground(Color.decode("#163020"));
        setForeground(Color.WHITE);
    }

    public CButton(String x ,int width, int height) {
        super(x);
        setFont(new Font("Verdana", Font.BOLD, 20));
        setPreferredSize(new Dimension(width, height));
        
        setBackground(Color.decode("#163020"));
        setForeground(Color.WHITE);
   }

    public CButton(String x, int width, int height, int fontSize) {
        super(x);
        setFont(new Font("Verdana", Font.BOLD, fontSize));
        setPreferredSize(new Dimension(width, height));

        setBackground(Color.decode("#163020"));
        setForeground(Color.WHITE);
    }

    public CButton(String x, int width, int height, int fontSize, Color color) {
        super(x);
        setFont(new Font("Verdana", Font.BOLD, fontSize));
        setPreferredSize(new Dimension(width, height));
        setBackground(color);
    }

    
    public CButton(String x, int width, int height, int fontSize, Color bgColor, Color fgColor) {
        super(x);
        setFont(new Font("Verdana", Font.BOLD, fontSize));
        setPreferredSize(new Dimension(width, height));
        setBackground(bgColor);
        setForeground(fgColor);
        setBorder(BorderFactory.createEmptyBorder());
    }
}