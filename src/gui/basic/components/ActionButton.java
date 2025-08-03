package gui.basic.components;

import javax.swing.JButton;

public class ActionButton extends JButton {

    public ActionButton() {
        putClientProperty("JComponent.arc", 150);
        setBackground(Colors.ACTION_BTN_COLOR);
    }

}
