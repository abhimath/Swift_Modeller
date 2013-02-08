package swiftmodeller;

import javax.swing.JOptionPane;
import java.io.File;

public class CloseApp {

    public CloseApp(java.awt.Frame parent, boolean modal)
    {
        int rVal = jOptionPane1.showConfirmDialog(parent, "Are You Sure You Want To Quit", null, -1, 3);
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
    JOptionPane jOptionPane1 = new JOptionPane();
}