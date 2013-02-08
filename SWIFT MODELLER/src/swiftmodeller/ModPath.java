package swiftmodeller;
import java.io.*;
import javax.swing.*;

public class ModPath extends JFrame
{
    ModPath()
    {
        ExtensionFilter batchfiles;
        if(chkos == 0)
        {
            batchfiles = new ExtensionFilter("", "All Files (*.*)");
        }
        else
        {
            batchfiles = new ExtensionFilter(".bat", "Batch Files (*.bat)");
        }
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
    private int chkos = SwiftModellerView.chkos;
    public static File file;
    JFileChooser c = new JFileChooser();
}