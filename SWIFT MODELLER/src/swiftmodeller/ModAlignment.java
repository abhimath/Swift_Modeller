package swiftmodeller;

import java.io.*;
import java.awt.Component;

public class ModAlignment
{
    public ModAlignment(String[] profiles, Component c)
    {
        File file = new File(workpath + sep + "align_mod.py");
        File outputfile = new File(workpath + sep + "align_mod.log");
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
                if((profiles[i] != null) && (i != 1))
                {
                    sb2.append(profiles[i].substring(0, profiles[i].length()-4) + "'), ('");
                }
            }
            sb2.delete(sb2.length()-6, sb2.length());

            contents.replace((contents.indexOf("env, file='") + 11),contents.indexOf("', model_segment"), profiles[1].substring(0, 4));
            contents.replace((contents.indexOf("FIRST:") + 6),contents.indexOf("','LAST:"), profiles[1].substring(4, 5));
            contents.replace((contents.indexOf("LAST:") + 5),contents.indexOf("aln.append_model"), profiles[1].substring(4, 5) + "'))\n");
            contents.replace((contents.indexOf("align_codes='") + 13),contents.indexOf("', atom_files"), profiles[1].substring(0, 5));
            contents.replace((contents.indexOf("', atom_files='") + 15),contents.indexOf(".pdb')"), profiles[1].substring(0, 4));
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

        exectext.append("align_mod.py");
        new ExecuteScript(exectext, outputfile, c);
    }

    private String workpath = SwiftModellerView.workpath;
    private String sep = SwiftModellerView.sep;
}