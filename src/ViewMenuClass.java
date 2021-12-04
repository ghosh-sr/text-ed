import java.awt.Color;

public class ViewMenuClass {
    //initializing the necessary
    ui ui;

    //constructor
    public ViewMenuClass(ui ui) {
        this.ui=ui;
    }

    //wordwrap function
    public void wordwrap(sample tab) {
        if (tab.WRAP==true) {
            tab.WRAP=false;
            tab.wrapUp();
        } else {
            tab.WRAP=true;
            tab.wrapUp();
        }
    }

    //light theme enabling function
    public void enable_light_theme(sample tab) {
        tab.textArea.setBackground(Color.WHITE);
        if (tab.textArea.getForeground()==Color.ORANGE) {
            tab.textArea.setForeground(Color.BLACK);
        }
    }

    //dark theme enabling function
    public void enable_dark_theme(sample tab) {
        tab.textArea.setBackground(Color.DARK_GRAY);
        if (tab.textArea.getForeground()==Color.BLACK) {
            tab.textArea.setForeground(Color.ORANGE);   
        }
    }
}
