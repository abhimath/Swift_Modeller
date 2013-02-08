package swiftmodeller;

import java.io.*;
import java.util.StringTokenizer;
import java.awt.Component;

public class MUploadCompare
{
    public MUploadCompare(StringBuffer templates, Component c)
    {
        File file = new File(workpath + sep + "compare.py");
        File outputfile = new File(workpath + sep + "compare.log");
        StringBuffer contents = new StringBuffer();
        StringBuffer exectext = new StringBuffer();
        BufferedReader reader = null;
        BufferedWriter writer = null;
        String temp, chain, str;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            StringBuffer sb = new StringBuffer();
            String text = null;

            while ((text = reader.readLine()) != null)
            {
                contents.append(text).append("\n");
            }

            StringTokenizer st = new StringTokenizer(templates.toString(), "\n");

            while(st.hasMoreTokens())
            {
                str = st.nextToken();
                temp = str.substring(0,4);
                chain = str.substring((str.indexOf(":") + 1), str.indexOf(";"));
                sb.append(temp).append("', '" + chain + "'), ('");
            }

            contents.replace((contents.indexOf("for (pdb, chain) in (('") + 23),contents.indexOf("):"), (sb.substring(0, (sb.length()-4)).toString()));

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

        exectext.append("compare.py");
        new ExecuteScript(exectext, outputfile, c);
    }

    private String workpath = SwiftModellerView.workpath;
    private String sep = SwiftModellerView.sep;
}