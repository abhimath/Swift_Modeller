package modellergui;

import java.io.*;
import java.awt.Component;

public class EvaluateModel
{
    public EvaluateModel(String model, Component c)
    {
        File file = new File(workpath + sep + "evaluate_model.py");
        File outputfile = new File(workpath + sep + "evaluate_model.log");
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

            contents.replace((contents.indexOf("complete_pdb(env, '") + 19),contents.indexOf("# Assess with DOPE:"), (model + "')\n\n"));
            contents.replace((contents.indexOf("', file='") + 9),contents.indexOf(".profile',"), model.substring(0, model.length()-4));

            writer = new BufferedWriter(new FileWriter(file));
            writer.write(contents.toString());
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            new ErrorClose(c, true, "File Not Found : " + file.getName());
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

        exectext.append("mod" + version + " evaluate_model.py\n");
        exectext.append("exit");
        new ExecuteScript(exectext,outputfile, c);
    }

    private String workpath = ModellerGUIView.workpath;
    private String sep = ModellerGUIView.sep;
    private String version = ModellerGUIView.version;
}