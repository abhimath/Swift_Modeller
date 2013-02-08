package swiftmodeller;

import java.io.*;
import java.util.StringTokenizer;
import java.awt.Component;

public class MUploadAlign
{
    public MUploadAlign(StringBuffer templates, String title, Component c)
    {
        muaopfile = title + "-mult";
        File file1 = new File(workpath + sep + "salign.py");
        File file2 = new File(workpath + sep + "align2d_mult.py");
        File outputfile1 = new File(workpath + sep + "salign.log");
        File outputfile2 = new File(workpath + sep + "align2d_mult.log");

        StringBuffer contents1 = new StringBuffer();
        StringBuffer contents2 = new StringBuffer();
        StringBuffer exectext1 = new StringBuffer();
        StringBuffer exectext2 = new StringBuffer();
        BufferedReader reader1 = null;
        BufferedWriter writer1 = null;
        BufferedReader reader2 = null;
        BufferedWriter writer2 = null;


        try
        {
            reader1 = new BufferedReader(new FileReader(file1));
            reader2 = new BufferedReader(new FileReader(file2));
            StringBuffer sb = new StringBuffer();
            String text1 = null;
            String text2 = null;
            String temp, chain, str;

            while ((text1 = reader1.readLine()) != null)
            {
                contents1.append(text1).append("\n");
            }

            while ((text2 = reader2.readLine()) != null)
            {
                contents2.append(text2).append("\n");
            }

            StringTokenizer st = new StringTokenizer(templates.toString(), "\n");

            while(st.hasMoreTokens())
            {
                str = st.nextToken();
                temp = str.substring(0,4);
                chain = str.substring((str.indexOf(":") + 1), str.indexOf(";"));
                sb.append(temp).append("', '" + chain + "'), ('");
            }

            contents1.replace((contents1.indexOf("for (code, chain) in (('") + 24),contents1.indexOf("):"), (sb.substring(0, (sb.length()-4)).toString()));
            contents2.replace((contents2.indexOf("sequence(s):\naln.append(file='") + 30),contents2.indexOf("# Structure"), (title + ".ali', align_codes='" + title + "')\n\n"));
            contents2.replace((contents2.indexOf("aln.write(file='") + 16),contents2.indexOf(", alignment_format='PAP'"), (title + "-mult.ali', alignment_format='PIR')\naln.write(file='" + title + "-mult.pap'"));

            writer1 = new BufferedWriter(new FileWriter(file1));
            writer1.write(contents1.toString());
            writer1.close();
            writer2 = new BufferedWriter(new FileWriter(file2));
            writer2.write(contents2.toString());
            writer2.close();
        }
        catch (IOException e)
        {
            new ErrorClose(c, true, "I/O Error");
        }
        finally
        {
           try
            {
                if (reader1 != null)
                {
                    reader1.close();
                }
                if (reader2 != null)
                {
                    reader2.close();
                }
            } catch (IOException e)
            {
                new ErrorClose(c, true, "I/O Error");
            }
        }

        exectext1.append("salign.py\n");
        new ExecuteScript(exectext1, outputfile1, c);

        new FlushScript(c);
        
        exectext2.append("align2d_mult.py");
        new ExecuteScript(exectext2, outputfile2, c);
    }

    private String workpath = SwiftModellerView.workpath;
    private String sep = SwiftModellerView.sep;
    public static String muaopfile;
}