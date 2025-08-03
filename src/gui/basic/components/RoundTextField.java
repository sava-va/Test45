/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.basic.components;

import javax.swing.JTextField;

/**
 *
 * @author savaa
 */
public class RoundTextField extends JTextField {

    public RoundTextField() {
        init();
    }

    private void init() {
        putClientProperty("JComponent.arc", 35);

        setSize(150, 25);
    }
}
