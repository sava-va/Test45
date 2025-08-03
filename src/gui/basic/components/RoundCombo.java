/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.basic.components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author savaa
 */
public class RoundCombo extends JComboBox<Object>{
    public RoundCombo() {
        init();
    }
    private void init() {
        putClientProperty("JComponent.arc", 10);
        setSize(150, 25);
    }
}
