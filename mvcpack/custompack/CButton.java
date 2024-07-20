package mvcpack.custompack;

import java.awt.*;
import javax.swing.*;

public class CButton extends JButton {
    public CButton(){
        super();
    }

    public CButton(String x){
        super(x);
    }

    public CButton(String x ,int width, int height) {
        super(x);
        setFont(new Font("Verdana", Font.BOLD, 20));
        setPreferredSize(new Dimension(width, height));
   }

    public CButton(String x, int width, int height, int fontSize) {
        super(x);
        setFont(new Font("Verdana", Font.BOLD, fontSize));
        setPreferredSize(new Dimension(width, height));
    }
}