import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultEditorKit;

public class ui implements ActionListener {
    //initializing components for the entire window
    JFrame window;      //the main window
    JTabbedPane tabbedPane;     //tabbed pane for navigation
    JMenuBar mb;        //the menu bar containing the menus
    JMenu fileMenu, viewMenu, editMenu, formatMenu;     //menus on the menu bar

    //items in the "File" menu
    JMenuItem newItem, openItem, saveItem, saveasItem, closeItem;

    //items in the "View" menu
    JMenuItem wordwrapItem;
    JMenu themeMenu;
    JMenuItem lightItem, darkItem;

    //items in the "Edit" menu
    JMenuItem cutItem, copyItem, pasteItem, undoItem, redoItem;

    //items in the "Format" menu
    JMenuItem fontItem, sizeItem, colorItem;

    //items for the tab removal menu
    JPopupMenu popup;
    JMenuItem removeItem;

    //creating the objects for the menu classes
    FileMenuClass file=new FileMenuClass(this);
    ViewMenuClass view=new ViewMenuClass(this);
    EditMenuClass edit=new EditMenuClass(this);
    FormatMenuClass format=new FormatMenuClass(this);

    //the keyhandler object
    keyhandler kHandler=new keyhandler(this);

    public static void main(String[] args) {
        new ui();
    }

    //the constructor of the class to implement non static methods
    public ui()
    {
        createWindow();
        createTabbedPane();
        createMenuBar();
        window.setVisible(true);
    }

    //creating the window to display and work on
    public void createWindow()
    {
        window=new JFrame("TextEditor");
        window.setSize(800,1000);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //creating the tabbed pane for navigation between different text areas
    public void createTabbedPane() {
        tabbedPane=new JTabbedPane();
        tabbedPane.addTab("New File", createComponent());
        tabbedPane.setFocusable(false);
        tabbedPane.addMouseListener(new java.awt.event.MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                if (SwingUtilities.isRightMouseButton(e) && tabbedPane.getUI().tabForCoordinate(tabbedPane, e.getX(), e.getY())!=-1) {
                    popup=new JPopupMenu();
                    removeItem=new JMenuItem("Close Tab(Ctrl+W)");
                    removeItem.addActionListener(new java.awt.event.ActionListener() {
    
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // TODO Auto-generated method stub
                            tabbedPane.remove(tabbedPane.getSelectedIndex());
                        }
                        
                    });
                    popup.add(removeItem);
                    popup.show(window, e.getX(), e.getY()+60);   
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
        });
        window.add(tabbedPane);
    }

    //creating the sample component to write on
    public sample createComponent() {
        sample tab=new sample();
        tab.textArea.addKeyListener(kHandler);
        tab.makeUndosAvailable();
        tab.makeScrollPane();
        tab.wrapUp();
        return tab;
    }
    
    //creating the menu bar
    public void createMenuBar() {
        mb=new JMenuBar();
        window.setJMenuBar(mb);

        fileMenu=new JMenu("File");
        mb.add(fileMenu);
        createFileMenu();

        viewMenu=new JMenu("View");
        mb.add(viewMenu);
        createViewMenu();

        editMenu=new JMenu("Edit");
        mb.add(editMenu);
        createEditMenu();

        formatMenu=new JMenu("Format");
        mb.add(formatMenu);
        createFormatMenu();
    }

    //creating the File menu
    public void createFileMenu() {
        //creating the menu items
        newItem=new JMenuItem("New(Ctrl+N)");
        newItem.addActionListener(this);
        newItem.setActionCommand("new");

        openItem=new JMenuItem("Open(Ctrl+O)");
        openItem.addActionListener(this);
        openItem.setActionCommand("open");

        saveItem=new JMenuItem("Save(Ctrl+S)");
        saveItem.addActionListener(this);
        saveItem.setActionCommand("save");

        saveasItem=new JMenuItem("Save As(Ctrl+Shift+S)");
        saveasItem.addActionListener(this);
        saveasItem.setActionCommand("savus");

        closeItem=new JMenuItem("Close All Tabs");
        closeItem.addActionListener(this);
        closeItem.setActionCommand("close");

        //adding the created items to the menu
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveasItem);
        fileMenu.add(closeItem);
    }

    //creating the view menu
    public void createViewMenu() {
        //creating the menu items
        wordwrapItem=new JMenuItem("Word Wrap");
        wordwrapItem.addActionListener(this);
        wordwrapItem.setActionCommand("wrap");

        lightItem=new JMenuItem("Light");
        lightItem.addActionListener(this);
        lightItem.setActionCommand("light");

        darkItem=new JMenuItem("Dark");
        darkItem.addActionListener(this);
        darkItem.setActionCommand("dark");

        themeMenu=new JMenu("Theme");
        themeMenu.add(lightItem);
        themeMenu.add(darkItem);


        //adding the menu items to the menu
        viewMenu.add(wordwrapItem);
        viewMenu.add(themeMenu);
    }

    //creating the edit menu
    public void createEditMenu() {
        //creating the menu items
        cutItem=new JMenuItem(new DefaultEditorKit.CutAction());
        cutItem.setText("Cut(Ctrl+X)");

        copyItem=new JMenuItem(new DefaultEditorKit.CopyAction());
        copyItem.setText("Copy(Ctrl+C)");

        pasteItem=new JMenuItem(new DefaultEditorKit.PasteAction());
        pasteItem.setText("Paste(Ctrl+V)");

        undoItem=new JMenuItem("Undo(Ctrl+Z)");
        undoItem.addActionListener(this);
        undoItem.setActionCommand("undo");

        redoItem=new JMenuItem("Redo(Ctrl+Y)");
        redoItem.addActionListener(this);
        redoItem.setActionCommand("redo");

        //adding the items to the menu
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(undoItem);
        editMenu.add(redoItem);
    }

    //creating the format menu
    public void createFormatMenu() {
        //creating the menu items
        fontItem=new JMenuItem("Font");
        fontItem.addActionListener(this);
        fontItem.setActionCommand("font");

        sizeItem=new JMenuItem("Size");
        sizeItem.addActionListener(this);
        sizeItem.setActionCommand("size");

        colorItem=new JMenuItem("Color");
        colorItem.addActionListener(this);
        colorItem.setActionCommand("color");

        //adding the items to the menu
        formatMenu.add(fontItem);
        formatMenu.add(sizeItem);
        formatMenu.add(colorItem);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String command=e.getActionCommand();
        switch (command) {
            case "new":
                file.newFile();
                break;

            case "open":
                file.openFile(createComponent());
                break;

            case "save":
                if(this.tabbedPane.getTabCount()>0) {
                file.saveFile((sample) this.tabbedPane.getComponentAt(this.tabbedPane.getSelectedIndex()));}
                break;

            case "savus":
                if(this.tabbedPane.getTabCount()>0) {
                file.saveFileAs((sample) this.tabbedPane.getComponentAt(this.tabbedPane.getSelectedIndex()));}
                break;

            case "close":
                if(this.tabbedPane.getTabCount()>0) {
                file.close();}
                break;

            case "wrap":
                if(this.tabbedPane.getTabCount()>0) {
                view.wordwrap((sample) this.tabbedPane.getComponentAt(this.tabbedPane.getSelectedIndex()));}
                break;

            case "undo":
                if(this.tabbedPane.getTabCount()>0) {
                edit.undo((sample) this.tabbedPane.getComponentAt(this.tabbedPane.getSelectedIndex()));}
                break;

            case "redo":
                if(this.tabbedPane.getTabCount()>0) {
                edit.redo((sample) this.tabbedPane.getComponentAt(this.tabbedPane.getSelectedIndex()));}
                break;
                
            case "color":
                if(this.tabbedPane.getTabCount()>0) {
                format.selectColor((sample) this.tabbedPane.getComponentAt(this.tabbedPane.getSelectedIndex()));}
                break;

            case "size":
                if(this.tabbedPane.getTabCount()>0) {
                format.selectSize((sample) this.tabbedPane.getComponentAt(this.tabbedPane.getSelectedIndex()));}
                break;

            case "font":
                if(this.tabbedPane.getTabCount()>0) {
                format.selectFont((sample) this.tabbedPane.getComponentAt(this.tabbedPane.getSelectedIndex()));}
                break;

            case "light":
                if(this.tabbedPane.getTabCount()>0) {
                view.enable_light_theme((sample) this.tabbedPane.getComponentAt(this.tabbedPane.getSelectedIndex()));}
                break;

            case "dark":
                if(this.tabbedPane.getTabCount()>0) {
                view.enable_dark_theme((sample) this.tabbedPane.getComponentAt(this.tabbedPane.getSelectedIndex()));}
                break;

            case "del":
                this.tabbedPane.remove(this.tabbedPane.getSelectedIndex());
                break;
        
            default:
                break;
        }
    }
}
