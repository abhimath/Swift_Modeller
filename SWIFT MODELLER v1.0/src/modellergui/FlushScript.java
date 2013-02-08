package modellergui;

import java.io.*;
import java.awt.Component;

public class FlushScript
{
    public FlushScript(Component c)
    {
        StringBuffer path = new StringBuffer(userdir);
        path.delete(path.indexOf(sep), path.length());
        path.append(sep + "executescript.bat");
        File file = new File(path.toString());
        StringBuffer contents = new StringBuffer();
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
            contents = new StringBuffer(contents.substring(1, contents.length()-2));

            file = new File(contents.toString());
            reader = new BufferedReader(new FileReader(file));
            contents = new StringBuffer();

            while ((text = reader.readLine()) != null)
            {
                contents.append(text).append("\n");
            }
            contents.delete(contents.indexOf("run Modeller.") + 13, contents.length());

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
    }
    private String userdir = ModellerGUIView.userdir;
    private String sep = ModellerGUIView.sep;
}