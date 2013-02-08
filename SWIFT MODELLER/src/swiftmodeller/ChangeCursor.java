package swiftmodeller;

import java.awt.*;
import java.net.*;
import java.io.File;

public class ChangeCursor
{
    public ChangeCursor(Component comp, int c)
    {
        this.comp = comp;
        String str = new String();
        if(c >= 0 && c < 10)
        {
            str = "a000" + c;
        }
        else if(c >= 10)
        {
            str = "a00" + c;
        }
        str += ".jpg";
        file = new File(userdir + sep + "cursor" + sep + str);
        formURL();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(url);
        Point hotSpot = new Point(0,0);
        Cursor cursor = toolkit.createCustomCursor(image, hotSpot, str);
        comp.setCursor(cursor);
    }
    
    private void formURL()
    {
        try
        {
            url = file.toURI().toURL();
        }
        catch(MalformedURLException e)
        {
            new ErrorClose(comp, true, "URL Error");
        }
    }

    private String userdir = SwiftModellerView.userdir;
    private String sep = SwiftModellerView.sep;
    URL url;
    File file;
    Component comp;
}