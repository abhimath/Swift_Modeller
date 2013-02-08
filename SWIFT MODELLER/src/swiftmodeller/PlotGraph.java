package swiftmodeller;

import java.io.*;
import java.awt.Component;

public class PlotGraph
{
    public PlotGraph(String[] labels, String[] colours, String[] profiles, Component c)
    {
        File file = new File(workpath + sep + "plot_profiles.py");
        File outputfile = null;
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

            sb1 = new StringBuffer();
            for(int i = 0; i < 4 ; i++)
            {
                if(profiles[i] != null)
                {
                    sb1.append(profiles[i].substring(0, profiles[i].length()-4)).append("-");
                }
            }
            sb1.delete(sb1.length()-1, sb1.length());
            outputfile = new File(workpath + sep + sb1 + ".png");
            
            StringBuffer sb2 = new StringBuffer();
            for(int i = 0; i < 4 ; i++)
            {
                if(profiles[i] != null)
                {
                    sb2.append("\nmodel" + i + " = get_profile('" + profiles[i].substring(0, (profiles[i].length()-4)) + ".profile', a['" + profiles[i].substring(0, (profiles[i].length()-4)) + "'])");
                }
            }

            StringBuffer sb3 = new StringBuffer();
            for(int i = 0; i < 4 ; i++)
            {
                if(profiles[i] != null)
                {
                    sb3.append("\npylab.plot(model" + i + ", color='" + colours[i] + "', linewidth=2, label='" + labels[i] + "')");
                }
            }
            contents.replace((contents.indexOf("(e, file='") + 10),contents.indexOf(".ali')"), sb1.toString());
            contents.replace((contents.indexOf(".ali')") + 6), contents.indexOf("# Plot the template"), sb2.toString() + "\n\n");
            contents.replace((contents.indexOf("score')") + 7), contents.indexOf("pylab.legend()"), sb3.toString() + "\n");
            contents.replace((contents.indexOf("pylab.savefig('") + 15),contents.indexOf(".png',"), sb1.toString());

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

        exectext.append("plot_profiles.py");
        new PlotProfiles(exectext, outputfile, c);
    }

    private String workpath = SwiftModellerView.workpath;
    private String sep = SwiftModellerView.sep;
    public static StringBuffer sb1;
}