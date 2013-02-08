package swiftmodeller;

import java.io.*;
import java.awt.Component;

public class EvaluateTemp
{
    public EvaluateTemp(String template, Component c)
    {
        File file = new File(workpath + sep + "evaluate_template.py");
        File outputfile = new File(workpath + sep + "evaluate_template.log");
        StringBuffer contents = new StringBuffer();
        StringBuffer exectext = new StringBuffer();
        BufferedReader reader = null;
        BufferedWriter writer = null;

        chain = template.substring(4);

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null)
            {
                contents.append(text).append("\n");
            }

            contents.replace((contents.indexOf("complete_pdb(env, '") + 19),contents.indexOf(".pdb', model_segment"), template.substring(0, 4));
            contents.replace((contents.indexOf("model_segment=('") + 16), contents.indexOf("'))"), ("FIRST:" + chain + "', 'LAST:" + chain + ""));
            contents.replace((contents.indexOf("', file='") + 9),contents.indexOf(".profile',"), template);

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

        exectext.append("evaluate_template.py");
        new ExecuteScript(exectext, outputfile, c);
    }

    private String workpath = SwiftModellerView.workpath;
    private String sep = SwiftModellerView.sep;
    private String chain;
}