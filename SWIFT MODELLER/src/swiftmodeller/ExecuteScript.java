package swiftmodeller;

import java.io.*;
import java.awt.*;

public class ExecuteScript
{
    public ExecuteScript(StringBuffer exectext, File outputfile, Component c)
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
        
        BufferedReader reader = null;
        BufferedWriter writer = null;
        
        File file = new File(path.toString());
        String text = null;
        StringBuffer contents = new StringBuffer();

        try
        {
            if(chkos != 0)
            {
                reader = new BufferedReader(new FileReader(file));

                while ((text = reader.readLine()) != null)
                {
                    contents.append(text);
                }
                modpath = contents.substring(0, contents.lastIndexOf(sep)).toString();

                file = new File(contents.toString());
                contents = new StringBuffer();
            }
            
            reader = new BufferedReader(new FileReader(file));
            while ((text = reader.readLine()) != null)
            {
                contents.append(text).append("\n");
            }

            contents.append("cd \"" + workpath + "\"\n");
            contents.append("mod" + version + " " + exectext);

            writer = new BufferedWriter(new FileWriter(file));
            writer.write(contents.toString());
            writer.close();

            Process p;
            c.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            if(chkos == 0)
            {
                StringBuffer command = new StringBuffer("sh executescript.sh");
                p = Runtime.getRuntime().exec(command.toString(), null, new File(userdir));
            }
            else
            {
                Runtime rt = Runtime.getRuntime();
                p = rt.exec("cmd /C modenv.bat", null, new File(modpath));
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String inline = null;
            String errline = null;
            contents = new StringBuffer();
            writer = new BufferedWriter(new FileWriter(new File(workpath + sep + "jobrun.log"), true));
            
            while ((errline = err.readLine()) != null)
            {
                if(!errline.startsWith("'import site'"))
                {
                    contents.append(errline).append("\n");
                }
            }
            if(contents.toString().length() > 0)
            {
                writer.write(contents.toString());
                writer.close();
                new ErrorClose(c, true, "Modeller Error\nKindly see jobrun.log for details");
            }
            while ((inline = in.readLine()) != null)
            {
                contents.append(inline).append("\n");
            }

            writer.write(contents.toString());
            writer.close();
            c.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
    private int chkos = SwiftModellerView.chkos;
    private String modpath;
    private String userdir = SwiftModellerView.userdir;
    private String workpath = SwiftModellerView.workpath;
    private String sep = SwiftModellerView.sep;
    private String version = SwiftModellerView.version;
}