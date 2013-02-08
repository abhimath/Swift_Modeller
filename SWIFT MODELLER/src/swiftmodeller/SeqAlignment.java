package swiftmodeller;

import java.io.*;
import java.awt.Component;

public class SeqAlignment
{
    public SeqAlignment(String[] profiles, Component c)
    {
        File file = new File(workpath + sep + "align_seq.py");
        File outputfile = new File(workpath + sep + "align_seq.log");
        StringBuffer contents = new StringBuffer();
        StringBuffer exectext = new StringBuffer();
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null)
            {
                contents.append(text).append("\n");
            }

            StringBuffer sb1 = new StringBuffer();
            for(int i = 0; i < 4 ; i++)
            {
                if(profiles[i] != null)
                {
                    sb1.append(profiles[i].substring(0, profiles[i].length()-4)).append("-");
                }
            }
            sb1.delete(sb1.length()-1, sb1.length());

            StringBuffer sb2 = new StringBuffer();
            for(int i = 0; i < 4 ; i++)
            {
                if(profiles[i] != null)
                {
                    sb2.append(profiles[i].substring(0, profiles[i].length()-4) + "'), ('");                   
                }
            }
            sb2.delete(sb2.length()-6, sb2.length());

            contents.replace((contents.indexOf("(code) in (('") + 13),contents.indexOf("    mdl ="), (sb2.toString() + "')):\n"));
            contents.replace((contents.indexOf("write(file='") + 12),contents.indexOf(".ali', alignment_format"), sb1.toString());

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

        exectext.append("align_seq.py\n");
        new ExecuteScript(exectext, outputfile, c);
    }

    private String workpath = SwiftModellerView.workpath;
    private String sep = SwiftModellerView.sep;
}