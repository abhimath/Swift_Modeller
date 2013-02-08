package swiftmodeller;

import java.io.*;
import java.awt.Component;

public class SUploadAlign
{
    public SUploadAlign(StringBuffer template, String title, Component c)
    {
        File file = new File(workpath + sep + "align2d.py");
        File outputfile = new File(workpath + sep + "align2d.log");
        StringBuffer contents = new StringBuffer();
        StringBuffer exectext = new StringBuffer();
        BufferedReader reader = null;
        BufferedWriter writer = null;
        String temp = template.substring(0,4);
        String chain = template.substring((template.indexOf(":") + 1), template.indexOf(";"));
        opfile = title + "-" + temp + chain;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null)
            {
                contents.append(text).append("\n");
            }

            contents.replace((contents.indexOf("(env, file='") + 12),contents.indexOf(", model_segment="), temp + "'");
            contents.replace((contents.indexOf("model_segment=('") + 16), contents.indexOf("'))"), ("FIRST:" + chain + "','LAST:" + chain + ""));
            contents.replace((contents.indexOf("mdl, align_codes='") + 18),contents.indexOf(", atom_files="), temp + chain + "'");
            contents.replace((contents.indexOf(", atom_files='") + 14),contents.indexOf(".pdb')"), temp);
            contents.replace((contents.indexOf("aln.append(file='") + 17),contents.indexOf(".ali', align_codes="), (title));
            contents.replace((contents.indexOf("', align_codes='") + 16),contents.indexOf("aln.align2d()"), (title + "')\n"));
            contents.replace((contents.indexOf("aln.write(file='") + 16),contents.indexOf(", alignment_format='PAP'"), (opfile + ".ali', alignment_format='PIR')\naln.write(file='" + opfile + ".pap'"));

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

        exectext.append("align2d.py");
        new ExecuteScript(exectext, outputfile, c);
    }
    
    private String workpath = SwiftModellerView.workpath;
    private String sep = SwiftModellerView.sep;
    public static String opfile;
}