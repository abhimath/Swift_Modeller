package swiftmodeller;
import java.io.*;
import javax.swing.*;

public class PyPath extends JFrame
{
    PyPath()
    {
        ExtensionFilter exefiles;
        exefiles = new ExtensionFilter(".exe", "Executable Files (*.exe)");
        c.addChoosableFileFilter(exefiles);
        c.setFileFilter(exefiles);
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