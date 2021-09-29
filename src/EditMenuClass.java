public class EditMenuClass {
    //initializing variables
    ui ui;

    //constructor
    public EditMenuClass(ui ui)
    {
        this.ui=ui;
    }

    //creating the undo function
    public void undo(sample tab) {
        if(tab.um.canUndo())
        {
            tab.um.undo();
        }
    }

    //creating the redo function
    public void redo(sample tab) {
        if(tab.um.canRedo())
        {
            tab.um.redo();
        }
    }
}
