package mvcpack.custompack;

import javax.swing.*;
import java.awt.*;

public class CLabel extends JLabel{
    private final String DEFAULT_FONT = "Verdana";

    public CLabel(){
        super();
        this.setForeground(Color.WHITE);
    }

    public CLabel(String text){
        super(text);
        this.setForeground(Color.WHITE);
    }

    public CLabel(String text, int fontSize, int fontType) {
        super(text);

        this.setFont(new Font(DEFAULT_FONT, fontType, fontSize));
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);

        this.setForeground(Color.WHITE);
    }

    public CLabel(int width, int height, String text, int fontSize, int fontType) {
        super(text);

        this.setPreferredSize(new Dimension(width, height + 10));
        this.setFont(new Font(DEFAULT_FONT, fontType, fontSize));
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);

        this.setForeground(Color.WHITE);

    }
}
