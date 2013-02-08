package modellergui;
import java.io.*;
import javax.swing.*;

public class ModPath extends JFrame
{
    ModPath()
    {
        ExtensionFilter batchfiles = new ExtensionFilter(".bat", "Batch files (*.bat)");
        c.addChoosableFileFilter(batchfiles);
        c.setFileFilter(batchfiles);
        int rVal = c.showOpenDialog(this);
        if(rVal == c.APPROVE_OPTION)
        {
            file = c.getSelectedFile();
        }
        if(rVal == c.CANCEL_OPTION)
        {
            c.cancelSelection();
        }
    }
    public static File file;
    JFileChooser c = new JFileChooser();
}