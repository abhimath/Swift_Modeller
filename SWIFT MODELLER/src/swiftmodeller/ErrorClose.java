package swiftmodeller;

import javax.swing.JOptionPane;
import java.io.File;

public class ErrorClose {

    public ErrorClose(java.awt.Component parent, boolean modal, String disp)
    {
        int rVal = jOptionPane1.showConfirmDialog(parent, disp + "\nProgram will shut down", null, -1, 3);
        if(rVal == jOptionPane1.OK_OPTION)
        {
            File del = new File(workpath + sep + "pdb_95.pir");
            System.gc();
            while(del.exists())
            {
                del.delete();
            }
            del = new File(workpath + sep + "pdb_95.pir.gz");
            System.gc();
            while(del.exists())
            {
                del.delete();
            }
            del = new File(workpath + sep + "pdb_95.bin");
            System.gc();
            while(del.exists())
            {
                del.delete();
            }
            System.exit(0);
        }
    }

    String workpath = SwiftModellerView.workpath;
    String sep = SwiftModellerView.sep;
    String smod = SelectModel.cmod;
    String slmod = SelectLoopModel.cmod;
    JOptionPane jOptionPane1 = new JOptionPane();
}