package swiftmodeller;

import java.io.*;
import java.util.zip.*;
import java.awt.Component;

public class ExtractFile
{
    public ExtractFile(String file, Component c)
    {
        try
        {
            String inFilename = workpath + sep + file;
            GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(inFilename));
            String outFilename = "";
            if(inFilename.contains("ent"))
            {
                outFilename = workpath + sep + file.substring(3, 7) + ".pdb";
            }
            else if(inFilename.contains("pir"))
            {
                outFilename = workpath + sep + file.substring(0, 10);
            }
            
            OutputStream out = new FileOutputStream(outFilename);

            byte[] buf = new byte[2048];
            int len;
            while ((len = gzipInputStream.read(buf)) > 0)
            {
                out.write(buf, 0, len);
            }
            gzipInputStream.close();
            out.close();
            while(outFilename.length() == 0)
            {
                Thread.sleep(1);
            }
        }
        catch(IOException e)
        {
            new ErrorClose(c, true, "I/O Error");
        }
        catch(InterruptedException e)
        {
            new ErrorClose(c, true, "Thread Interrupted");
        }
    }

    private String sep = SwiftModellerView.sep;
    private String workpath = SwiftModellerView.workpath;
}