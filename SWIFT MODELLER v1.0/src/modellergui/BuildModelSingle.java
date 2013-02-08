package modellergui;

import java.io.*;
import java.awt.Component;

public class BuildModelSingle
{
    public BuildModelSingle(String alnfile, String[] chk, String start, String modelnum, Component c)
    {
        File file = new File(workpath + sep + "model-single.py");
        File outputfile = new File(workpath + sep + "model-single.log");
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

            contents.replace((contents.indexOf("(env, alnfile='") + 15),contents.indexOf("',\n              knowns"), alnfile);
            contents.replace((contents.indexOf("knowns='") + 8), contents.indexOf("', sequence"), chk[0]);
            contents.replace((contents.indexOf("sequence='") + 10),contents.indexOf("',\n              assess"), title);
            contents.replace((contents.indexOf("a.starting_model = ") + 19), contents.indexOf("a.make()"), start + "\na.ending_model = " + modelnum + "\n");

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

        exectext.append("mod" + version + " model-single.py\n");
        exectext.append("exit");
        new ExecuteScript(exectext, outputfile, c);
    }

    private String workpath = ModellerGUIView.workpath;
    private String title = ModellerGUIView.title;
    private String sep = ModellerGUIView.sep;
    private String version = ModellerGUIView.version;
    public static String opfile;
}