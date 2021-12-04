import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyhandler implements KeyListener {

    ui ui;

    public keyhandler(ui ui) {
        this.ui=ui;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_S) {
            ui.file.saveFile((sample) ui.tabbedPane.getComponentAt(ui.tabbedPane.getSelectedIndex()));
        }
        if (e.isControlDown() && e.isShiftDown() && e.getKeyCode()==KeyEvent.VK_S) {
            ui.file.saveFileAs((sample) ui.tabbedPane.getComponentAt(ui.tabbedPane.getSelectedIndex()));
        }
        if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_Z) {
            ui.edit.undo((sample) ui.tabbedPane.getComponentAt(ui.tabbedPane.getSelectedIndex()));
        }
        if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_Y) {
            ui.edit.redo((sample) ui.tabbedPane.getComponentAt(ui.tabbedPane.getSelectedIndex()));
        }
        if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_W) {
            ui.tabbedPane.remove(ui.tabbedPane.getSelectedIndex());
        }
        if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_N) {
            ui.file.newFile();            
        }
        if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_O) {
            ui.file.openFile(ui.createComponent());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
