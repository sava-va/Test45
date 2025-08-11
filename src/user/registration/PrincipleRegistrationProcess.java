package user.registration;

import connection.MySQL;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;

public class PrincipleRegistrationProcess {

    private File selectedImageFile;

//    private Map<String, Integer> genderMap = new HashMap<>();
    public void loadGender(javax.swing.JComboBox<String> genderCombo) {
        genderCombo.removeAllItems();
        genderCombo.addItem("Select Gender");

        try {
            ResultSet rs = MySQL.execute("SELECT * FROM `gender`");

            while (rs.next()) {
                genderCombo.addItem(rs.getString("type"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadDrawerNo(javax.swing.JComboBox<String> drawerCombo) {
        drawerCombo.removeAllItems();
        drawerCombo.addItem("Select Drawer");

        try {
            ResultSet rs = MySQL.execute("SELECT * FROM `drawer`");

            while (rs.next()) {
                drawerCombo.addItem(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     public void loadFileNo(javax.swing.JComboBox<String> fileNoCombo) {
        fileNoCombo.removeAllItems();
        fileNoCombo.addItem("Select Drawer");

        try {
            ResultSet rs = MySQL.execute("SELECT * FROM `file`");

            while (rs.next()) {
                fileNoCombo.addItem(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadImage(JLabel imageLabel) {
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        fileChooser.setDialogTitle("Select Profile Image");

        // Only allow image files
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Image Files", "jpg", "png", "jpeg", "gif"
        ));

        int result = fileChooser.showOpenDialog(null);

        if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();

            // Scale image to fit label
            javax.swing.ImageIcon icon = new javax.swing.ImageIcon(
                    new javax.swing.ImageIcon(selectedFile.getAbsolutePath())
                            .getImage()
                            .getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), java.awt.Image.SCALE_SMOOTH)
            );

            imageLabel.setIcon(icon);
        }
    }

}
