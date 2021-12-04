import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class sample extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JScrollPane scrollPane;
    JTextArea textArea;
    UndoManager um;
    Boolean WRAP=true;
    String filename=null;
    String fileaddress=null;

    public sample() {
        textArea=new JTextArea();
        textArea.setFont(new Font("Courier New",Font.PLAIN,18));
        textArea.setForeground(Color.BLACK);
        um=new UndoManager();
    }
    
    public void makeUndosAvailable() {
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener(){
            public void undoableEditHappened(UndoableEditEvent e) {
                um.addEdit(e.getEdit());
            }
        });
    }

    public void makeScrollPane() {
        scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void wrapUp() {
        if (WRAP==true) {
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
        } else {
            textArea.setLineWrap(false);
            textArea.setWrapStyleWord(false);
        }
    }
}
