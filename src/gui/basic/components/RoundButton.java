package gui.basic.components;

import java.awt.Color;
import javax.swing.JButton;

public class RoundButton extends JButton {

    public RoundButton() {
        init();
    }

    private void init() {
        putClientProperty("JComponent.arc", 15);
        setBackground(Colors.NORMAL_BTN_COLOR);
        setSize(150, 35);
    }
}
