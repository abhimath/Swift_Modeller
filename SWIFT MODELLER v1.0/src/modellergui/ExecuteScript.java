package modellergui;

import java.io.*;
import java.util.StringTokenizer;
import java.awt.Component;

public class ExecuteScript
{
    public ExecuteScript(StringBuffer exectext, File outputfile, Component c)
    {
        StringBuffer path = new StringBuffer(userdir);
        path.delete(path.indexOf(sep), path.length());
        path.append(sep + "executescript.bat");
        BufferedReader reader = null;
        BufferedWriter writer = null;
        File file = new File(path.toString());

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            StringBuffer contents = new StringBuffer();

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

            contents.append("cd " + workpath + "\n");
            contents.append(exectext);

            writer = new BufferedWriter(new FileWriter(file));
            writer.write(contents.toString());
            writer.close();

            StringBuffer command = new StringBuffer("cmd /C start ");
            command.append(path);
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(command.toString());

            int i = 0;
            while(outputfile.length() == 0)
            {
                new ChangeCursor(c, i);
                Thread.sleep(10);
                i++;
                if(i == 81)
                {
                    i = 0;
                }
            }
            text = null;            
            int counter = 0;

            while(counter == 0)
            {
                reader = new BufferedReader(new FileReader(outputfile));
                while ((text = reader.readLine()) != null)
                {
                    StringTokenizer st = new StringTokenizer(text);
                    while(st.hasMoreTokens())
                    {
                        if(st.nextToken().equals("Total") && st.nextToken().equals("CPU"))
                        {
                            counter++;
                        }
                    }
                }
                Thread.sleep(1000);
            }
            Thread.sleep(1000);
        }
        catch (FileNotFoundException e)
        {
            new ErrorClose(c, true, "File Not Found : " + file.getName());
        }
        catch (IOException e)
        {
            new ErrorClose(c, true, "I/O Error");
        }
        catch (InterruptedException e)
        {
            new ErrorClose(c, true, "Thread Interrupted");
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
    private String workpath = ModellerGUIView.workpath;
    private String sep = ModellerGUIView.sep;
}