package swiftmodeller;

import java.io.*;
import java.awt.Component;

public class LoopRefine
{
    public LoopRefine(String model, String strres, String endres, String modelnum, Component c)
    {
        File file = new File(workpath + sep + "loop_refine.py");
        File outputfile = new File(workpath + sep + "loop_refine.log");
        StringBuffer contents = new StringBuffer();
        StringBuffer exectext = new StringBuffer();
        BufferedReader reader = null;
        BufferedWriter writer = null;
        int residues = Integer.valueOf(endres) - Integer.valueOf(strres);

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null)
            {
                contents.append(text).append("\n");
            }

            contents.replace((contents.indexOf("(self):") + 7),contents.indexOf(" residue insertion"), ("\n        # " + residues));
            contents.replace((contents.indexOf("range('") + 7), contents.indexOf("m = MyLoop"), (strres + "', '" + endres + "'))\n\n"));
            contents.replace((contents.indexOf("inimodel='") + 10),contents.indexOf("', # initial model"), model);
            contents.replace((contents.indexOf("sequence='") + 10), contents.indexOf("')          # code"), title);
            contents.replace((contents.indexOf("ending_model  = ") + 16), contents.indexOf("          # index of the last"), modelnum);

            writer = new BufferedWriter(new FileWriter(file));
            writer.write(contents.toString());
            writer.close();
        }
        catch (IOException e)
        {
            new ErrorClose(c, true, "I/O Error");
        }
        finally
        {
           try
            {
                if (reader != null)
                {
                    reader.close();
                }
            } catch (IOException e)
            {
                new ErrorClose(c, true, "I/O Error");
            }
        }

        exectext.append("loop_refine.py");
        new ExecuteScript(exectext, outputfile, c);
    }

    private String workpath = SwiftModellerView.workpath;
    private String title = SwiftModellerView.title;
    private String sep = SwiftModellerView.sep;
    public static String opfile;
}