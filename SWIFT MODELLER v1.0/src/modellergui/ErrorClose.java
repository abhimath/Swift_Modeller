package modellergui;

import javax.swing.JOptionPane;

public class ErrorClose {

    public ErrorClose(java.awt.Component parent, boolean modal, String disp)
    {
        int rVal = jOptionPane1.showConfirmDialog(parent, disp + "\nProgram will shut down", null, -1, 3);
        if(rVal == jOptionPane1.OK_OPTION)
        {
            System.exit(0);
        }
    }
    JOptionPane jOptionPane1 = new JOptionPane();
}