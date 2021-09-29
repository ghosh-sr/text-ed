import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class FormatMenuClass {
    ui ui;
    JSpinner sizeSpinner;
    SpinnerNumberModel model= new SpinnerNumberModel(18,8,48,2);
    JComboBox<String> fontBox;
    String[] fonts=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    //constructor
    public FormatMenuClass(ui ui) {
        this.ui=ui;
    }

    //the text color function
    public void selectColor(sample tab) {
        Color selectedColor=JColorChooser.showDialog(null, "Pick a color", Color.black);
        tab.textArea.setForeground(selectedColor);
    }

    //the text size function
    public void selectSize(sample tab) {
        sizeSpinner=new JSpinner(model);
        JOptionPane.showMessageDialog(null, sizeSpinner, "Select Text Size", JOptionPane.PLAIN_MESSAGE);
        tab.textArea.setFont(new Font(tab.textArea.getFont().getFamily(),Font.PLAIN,(int) sizeSpinner.getValue()));
    }

    //the font function
    public void selectFont(sample tab) {
        fontBox=new JComboBox<>(fonts);
        JOptionPane.showMessageDialog(null, fontBox, "Select a Font", JOptionPane.PLAIN_MESSAGE);
        tab.textArea.setFont(new Font((String) fontBox.getSelectedItem(),Font.PLAIN,tab.textArea.getFont().getSize()));
    }
}
