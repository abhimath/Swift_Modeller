package swiftmodeller;

import java.io.*;
import java.awt.*;

public class PlotProfiles
{
    public PlotProfiles(StringBuffer exectext, File outputfile, Component c)
    {
        StringBuffer path = new StringBuffer(userdir);
        StringBuffer pypath = new StringBuffer(userdir);
        pypath.append(sep + "pypath");

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
        StringBuffer errcontents = new StringBuffer();
        StringBuffer pycontents = new StringBuffer();

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

                File pyfile = new File(pypath.toString());
                BufferedReader pyreader = new BufferedReader(new FileReader(pyfile));
                text = null;

                while ((text = pyreader.readLine()) != null)
                {
                    pycontents.append(text);
                }
            }

            reader = new BufferedReader(new FileReader(file));
            while ((text = reader.readLine()) != null)
            {
                contents.append(text).append("\n");
            }

            contents.append("cd \"" + workpath + "\"\n");

            if(chkos != 0)
            {
                contents.append(pycontents.toString() + " " + exectext);
            }
            else
            {
                contents.append("python" + " " + exectext);
            }

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

            while ((inline = in.readLine()) != null)
            {
                contents.append(inline).append("\n");
            }
            while ((errline = err.readLine()) != null)
            {
                int cjk = 0;
                if((!(errline.contains("RuntimeWarning"))) && (!(errline.contains("import"))))
                {
                    System.out.println(errline + "\t" +cjk);
                    errcontents.append(errline).append("\n");
                    cjk++;
                }
            }
            if(errcontents.toString().length() > 0)
            {
                writer.write(errcontents.toString());
                writer.close();
                new ErrorClose(c, true, "Modeller Error\nKindly see jobrun.log for details");
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
}