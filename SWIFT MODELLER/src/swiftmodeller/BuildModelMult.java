package swiftmodeller;

import java.io.*;
import java.awt.Component;

public class BuildModelMult
{
    public BuildModelMult(String alnfile, String[] chk, String modelnum, Component c)
    {
        File file = new File(workpath + sep + "model_mult.py");
        File outputfile = new File(workpath + sep + "model_mult.log");
        StringBuffer contents = new StringBuffer();
        StringBuffer exectext = new StringBuffer();
        StringBuffer templist = new StringBuffer();
        BufferedReader reader = null;
        BufferedWriter writer = null;

        for(int i = 0; i < (chk.length-1); i++)
        {
            templist.append(chk[i]).append("','");
        }

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null)
            {
                contents.append(text).append("\n");
            }

            contents.replace((contents.indexOf("(env, alnfile='") + 15),contents.indexOf("',\n              knowns"), alnfile);
            contents.replace((contents.indexOf("knowns=('") + 9), contents.indexOf("'), sequence"), templist.toString().substring(0, (templist.length()-3)));
            contents.replace((contents.indexOf("sequence='") + 10),contents.indexOf("',\n              assess"), title);
            contents.replace((contents.indexOf("a.ending_model = ") + 17), contents.indexOf("a.make()"), modelnum + "\n");

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

        exectext.append("model_mult.py");
        new ExecuteScript(exectext, outputfile, c);
    }

    private String workpath = SwiftModellerView.workpath;
    private String title = SwiftModellerView.title;
    private String sep = SwiftModellerView.sep;
    public static String opfile;
}