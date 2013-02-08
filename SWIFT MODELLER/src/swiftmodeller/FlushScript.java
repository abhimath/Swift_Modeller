package swiftmodeller;

import java.io.*;
import java.awt.Component;

public class FlushScript
{
    public FlushScript(Component c)
    {
        StringBuffer path = new StringBuffer(userdir);
        if(chkos == 0)
        {
            path.append(sep + "executescript.sh");
        }
        else
        {
            path.append(sep + "modpath");
        }
        
        File file = new File(path.toString());
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;
        BufferedWriter writer = null;
        String text = null;

        try
        {
            if(chkos != 0)
            {
                reader = new BufferedReader(new FileReader(file));

                while ((text = reader.readLine()) != null)
                {
                    contents.append(text);
                }

                file = new File(contents.toString());
                reader = new BufferedReader(new FileReader(file));
                contents = new StringBuffer();
            }

            reader = new BufferedReader(new FileReader(file));
            while ((text = reader.readLine()) != null)
            {
                contents.append(text).append("\n");
            }

            if(chkos == 0)
            {
                contents.replace(0, contents.length(), "");
            }
            else
            {
                String line[] = contents.toString().split("\n");
                contents = new StringBuffer();
                for(int i = 0; i < line.length; i++)
                {
                    if((line[i].startsWith("set")) || (line[i].startsWith("cd ")) || (line[i].startsWith("@echo")) || (line[i].startsWith("echo")))
                    {
                        if(!line[i].startsWith("cd \""))
                        {
                            contents.append(line[i]).append("\n");
                        }
                    }
                }
            }

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
    }
    private String userdir = SwiftModellerView.userdir;
    private String sep = SwiftModellerView.sep;
    private int chkos = SwiftModellerView.chkos;
}