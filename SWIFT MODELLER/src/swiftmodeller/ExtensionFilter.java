package swiftmodeller;
import javax.swing.filechooser.FileFilter;
import java.io.File;
public class ExtensionFilter extends FileFilter
{
    ExtensionFilter(String ext, String descr)
    {
        extension = ext.toLowerCase();
        description = descr;
    }
    public boolean accept(File file)
    {
        return(file.isDirectory()||file.getName().toLowerCase().endsWith(extension));
    }
    public String getDescription()
    {
        return description;
    }
    private String description;
    private String extension;
}

