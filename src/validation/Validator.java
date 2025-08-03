package validation;

import javax.swing.JOptionPane;

public class Validator {

    public static boolean isEmailValid(String value, String massage, String title) {
        if (value.isBlank()) {
            JOptionPane.showMessageDialog(null,
                    massage,
                    title + " Validation",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (!value.matches(Validation.EMAIL.validate())) {
            JOptionPane.showMessageDialog(null,
                    massage,
                    title + " Validation",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean isMobileValid(String value, String massage, String title) {
        if (value.isBlank()) {
            JOptionPane.showMessageDialog(null,
                    massage,
                    title + " Validation",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (!value.matches(Validation.MOBILE.validate())) {
            JOptionPane.showMessageDialog(null,
                    massage,
                    title + " Validation",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean isDoubleNumaric(String value, String massage, String title) {
        if (value == null || value.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    massage,
                    title + " Validation",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            Double.valueOf(value);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Enter a valid Number",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

    }

    public static boolean isIntNumaric(String value, String massage, String title) {
        if (value == null || value.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    massage,
                    title + " Validation",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            Integer.valueOf(value);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    massage,
                    title + " Validation",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public static boolean isPasswordValid(String value, String massage, String title) {
        if (value.isBlank()) {
            JOptionPane.showMessageDialog(null,
                    massage,
                    title + " Validation",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (!value.matches(Validation.PASSWORD.validate())) {
            JOptionPane.showMessageDialog(null, """
                                                Password must include the following characters. 
                                                At least one lowercase, 
                                                at least one uppercase, 
                                                a special character, 
                                                and at least one digit. 
                                                The password must be greater than 4 and less than 8 characters""",
                    title + " Validation",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean isInputFieldValide(String value, String massage, String title) {
        if (value.isBlank()) {
            JOptionPane.showMessageDialog(null,
                    massage,
                    title + " Validation",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean isItemSelectedValide(int value, String massage, String title) {
        int notSelected = 0;
        if (value == notSelected) {
            JOptionPane.showMessageDialog(null,
                    massage,
                    title + " Validation",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

}
