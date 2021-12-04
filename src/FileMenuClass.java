import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FileMenuClass {
    //initializing components
    ui ui;

    //constructor
    public FileMenuClass(ui ui)
    {
        this.ui=ui;
    }

    //creating the "New" function
    public void newFile() {
        ui.tabbedPane.addTab("New File", ui.createComponent());
    }

    //creating the "Open" function
    public void openFile(sample tab){
        FileDialog fileDialog=new FileDialog(ui.window,"Open",FileDialog.LOAD);
        fileDialog.setVisible(true);
        if (fileDialog.getFile()!=null) {
            ui.tabbedPane.addTab(fileDialog.getFile(), tab);
            tab.filename=fileDialog.getFile();
            tab.fileaddress=fileDialog.getDirectory();

            try {
                BufferedReader br= new BufferedReader(new FileReader(tab.fileaddress+tab.filename));
                tab.textArea.setText("");
                String line=null;
                while ((line = br.readLine())!=null) {
                    tab.textArea.append(line+"\n");
                }
                br.close();
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("IO probs");
            }
        }
        else {
            System.out.println("No File Opened");
        }
    }

    //creating the "Save" function
    public void saveFile(sample tab) {
        if(tab.filename==null)
        {
            saveFileAs(tab);
        }
        else
        {
            try {
                FileWriter fw=new FileWriter(tab.fileaddress+tab.filename);
                fw.write(tab.textArea.getText());
                fw.close();
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("IO probs");
            }
        }
    }

    //creating the "Save As" function
    public void saveFileAs(sample tab) {
        FileDialog fileDialog=new FileDialog(ui.window,"Save As",FileDialog.SAVE);
        fileDialog.setVisible(true);
        if (fileDialog.getFile()!=null) {
            tab.filename=fileDialog.getFile();
            tab.fileaddress=fileDialog.getDirectory();
        
        try {
            FileWriter fw=new FileWriter(tab.fileaddress+tab.filename);
            fw.write(tab.textArea.getText());
            ui.tabbedPane.setTitleAt(ui.tabbedPane.getSelectedIndex(), tab.filename);
            fw.close();
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("File Error");
        }
    }
    else
    {
        System.out.println("No file name entered");
    }
    }

    //creating the "Close" function
    public void close() {
        ui.tabbedPane.removeAll();
    }
}
