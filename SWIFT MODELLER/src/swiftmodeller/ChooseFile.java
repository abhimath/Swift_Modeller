package swiftmodeller;
import java.io.*;
import javax.swing.*;

public class ChooseFile extends JFrame
{
    ChooseFile()
    {
        ExtensionFilter pdbfiles = new ExtensionFilter(".pdb", "PDB files (*.pdb)");
        c.setMultiSelectionEnabled(true);
        c.addChoosableFileFilter(pdbfiles);
        c.setFileFilter(pdbfiles);
        int rVal = c.showOpenDialog(this);
        if(rVal == c.APPROVE_OPTION)
        {
            file = c.getSelectedFiles();
        }
        if(rVal == c.CANCEL_OPTION)
        {
            c.cancelSelection();
        }
    }
    public File[] file = null;
    JFileChooser c = new JFileChooser();
}