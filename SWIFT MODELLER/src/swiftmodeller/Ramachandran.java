package swiftmodeller;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage.*;
import javax.imageio.ImageIO;
import javax.imageio.ImageIO.*;

public class Ramachandran extends JPanel
{
    public Ramachandran(String psiphi)
    {
        data = new Vector<Point2D.Double>();
	inputData(workpath + sep + psiphi);
        imageHeight = 500;
        imageWidth = 500;
        mAxes = createAxes();
        exportToFile();
    }
    
    private void drawPoint(Graphics2D g, double x, double y)
    {
        Ellipse2D point = new Ellipse2D.Double(x - POINT_RADIUS, y - POINT_RADIUS, POINT_DIAMETER, POINT_DIAMETER);
        g.setStroke(new BasicStroke(0.2f));
        g.fill(point);
    }

    public Shape createAxes()
    {
        GeneralPath path = new GeneralPath();
        path.moveTo(-180,-180);
        path.lineTo(-180,180);
        path.lineTo(180,180);
        path.lineTo(180,-180);
        path.lineTo(-180,-180);
        path.moveTo(0,180);
        path.lineTo(0,-180);
    	path.moveTo(180,0);
        path.lineTo(-180,0);

        int[] tickPos = {-180,-150,-120,-90,-60,-30,0,30,60,90,120,150,180};
        for (int j = 0; j < tickPos.length; ++j)
        {
            path.moveTo(-180-mTick,tickPos[j]);
            path.lineTo(-180+mTick,tickPos[j]);
        }
        for (int j = 0; j < tickPos.length; ++j)
        {
            path.moveTo(tickPos[j],-180-mTick);
            path.lineTo(tickPos[j],-180+mTick);
        }
        return path;
    }
    
    private void numberAxes(Graphics2D g)
    {
        Font font = new Font("Serif", Font.PLAIN, 10);
        g.setFont(font);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int[] numPos = {-180,-90,0,90,180};
        for (int i = 0; i < numPos.length; ++i)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(numPos[i] + "\u00B0");
            g.drawString(sb.toString(), (int)(-210 + imageWidth / 2), (int)(imageHeight/2 - numPos[i] ) + 2);
        }

        g.drawString("Psi", 10,(int)(imageHeight/2.0));

        for (int i = 0; i < numPos.length; ++i)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(numPos[i] + "\u00B0");
            g.drawString(sb.toString(), (int)(imageWidth/2 + numPos[i] - 5), (int)(-50 + imageHeight ));
        }

        g.drawString("Phi", (int)imageWidth / 2 - 5, (int)(-35 + imageHeight));
    }

    private void drawPlot(Graphics2D g2d)
    {
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.translate(imageWidth/2.0,imageHeight/2.0);
	AffineTransform aftx = g2d.getTransform();
        g2d.scale(1.0,-1.0);
        defcor(g2d);

        g2d.draw(mAxes);
        for (Point2D.Double p: data)
        {
            drawPoint(g2d,p.x,p.y);
        }

	g2d.setTransform(aftx);
	Font font = new Font("Serif", Font.PLAIN, 10);
        g2d.setFont(font);

	String[] strPos = {"-180"," -90","   0","  90"," 180"};
	int[] numPosY = {180,90,0,-90,-180};
        for (int i = 0; i < numPosY.length; ++i)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(strPos[i] + "\u00B0");
            g2d.drawString(sb.toString(), -215, numPosY[i] + 5);
        }

	g2d.drawString("Psi", -235,0);
	int [] numPosX = {-180,-90,0,90,180 };

        for (int i = 0; i < numPosX.length; ++i)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(strPos[i] + "\u00B0");
            g2d.drawString(sb.toString(), numPosX[i] - 10, (int)(-55 + imageHeight/2));
        }

        g2d.drawString("Phi", -5, (int) imageHeight/2 - 40);
    }

    public void paintComponent(Graphics g)
    {
        g2d = (Graphics2D)g;
	drawPlot(g2d);
    }

    public void exportToFile()
    {
	BufferedImage  bImage = new BufferedImage((int)imageWidth,(int) imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D gtwod = bImage.createGraphics();
        gtwod.fillRect(0, 0,(int)imageWidth,(int) imageHeight);
        numberAxes(gtwod);
	drawPlot(gtwod);
        gtwod.dispose();
	try
        {
            File file;
            if(notrama == 0)
            {
                file = new File(workpath + sep + "RamaModel.png");
            }
            else
            {
                file = new File(workpath + sep + "RamaLoopModel.png");
            }
	    ImageIO.write(bImage, "png", file);
    	}
        catch (IOException e)
        {
	    System.err.println("Error saving file to png: " +e);
	}
    }

    public void defcor(Graphics2D g)
    {
	Color black = new Color(0,0,0);
	Color orange = new Color(255,111,0);
	Color gold = new Color(255,204,0);
        Color green = new Color(0,255,0);
	g.setColor(orange);
        g.setPaint(green);
	GeneralPath gpath = new GeneralPath(GeneralPath.WIND_NON_ZERO);

        gpath.moveTo(57.5f,67.5f);
	gpath.lineTo(57.5f,62.5f);
	gpath.lineTo(62.5f,62.5f);
	gpath.lineTo(62.5f,57.5f);
	gpath.lineTo(67.5f,57.5f);
	gpath.lineTo(67.5f,47.5f);
	gpath.lineTo(72.5f,47.5f);
	gpath.lineTo(72.5f,32.5f);
	gpath.lineTo(77.5f,32.5f);
	gpath.lineTo(77.5f,2.5f);
	gpath.lineTo(62.5f,2.5f);
	gpath.lineTo(62.5f,7.5f);
	gpath.lineTo(57.5f,7.5f);
	gpath.lineTo(57.5f,12.5f);
	gpath.lineTo(52.5f,12.5f);
	gpath.lineTo(52.5f,22.5f);
	gpath.lineTo(47.5f,22.5f);
	gpath.lineTo(47.5f,27.5f);
	gpath.lineTo(42.5f,27.5f);
	gpath.lineTo(42.5f,37.5f);
	gpath.lineTo(37.5f,37.5f);
	gpath.lineTo(37.5f,62.5f);
	gpath.lineTo(42.5f,62.5f);
	gpath.lineTo(42.5f,67.5f);
	gpath.lineTo(57.5f,67.5f);
	gpath.moveTo(-177.5f,-180.0f);
	gpath.lineTo(-177.5f,-177.5f);
	gpath.lineTo(-172.5f,-177.5f);
	gpath.lineTo(-172.5f,-172.5f);
	gpath.lineTo(-167.5f,-172.5f);
	gpath.lineTo(-167.5f,-167.5f);
	gpath.lineTo(-127.5f,-167.5f);
	gpath.lineTo(-127.5f,-172.5f);
	gpath.lineTo(-97.5f,-172.5f);
	gpath.lineTo(-97.5f,-167.5f);
	gpath.lineTo(-77.5f,-167.5f);
	gpath.lineTo(-77.5f,-172.5f);
	gpath.lineTo(-72.5f,-172.5f);
	gpath.lineTo(-72.5f,-177.5f);
	gpath.lineTo(-67.5f,-177.5f);
	gpath.lineTo(-67.5f,-180.0f);
	gpath.moveTo(-62.5f,180.0f);
	gpath.lineTo(-62.5f,172.5f);
	gpath.lineTo(-57.5f,172.5f);
	gpath.lineTo(-57.5f,167.5f);
	gpath.lineTo(-52.5f,167.5f);
	gpath.lineTo(-52.5f,157.5f);
	gpath.lineTo(-47.5f,157.5f);
	gpath.lineTo(-47.5f,147.5f);
	gpath.lineTo(-42.5f,147.5f);
	gpath.lineTo(-42.5f,137.5f);
	gpath.lineTo(-37.5f,137.5f);
	gpath.lineTo(-37.5f,122.5f);
	gpath.lineTo(-42.5f,122.5f);
	gpath.lineTo(-42.5f,117.5f);
	gpath.lineTo(-47.5f,117.5f);
	gpath.lineTo(-47.5f,112.5f);
	gpath.lineTo(-57.5f,112.5f);
	gpath.lineTo(-57.5f,107.5f);
	gpath.lineTo(-62.5f,107.5f);
	gpath.lineTo(-62.5f,102.5f);
	gpath.lineTo(-67.5f,102.5f);
	gpath.lineTo(-67.5f,97.5f);
	gpath.lineTo(-72.5f,97.5f);
	gpath.lineTo(-72.5f,62.5f);
	gpath.lineTo(-77.5f,62.5f);
	gpath.lineTo(-77.5f,52.5f);
	gpath.lineTo(-87.5f,52.5f);
	gpath.lineTo(-87.5f,47.5f);
	gpath.lineTo(-92.5f,47.5f);
	gpath.lineTo(-92.5f,52.5f);
	gpath.lineTo(-97.5f,52.5f);
	gpath.lineTo(-97.5f,67.5f);
	gpath.lineTo(-102.5f,67.5f);
	gpath.lineTo(-102.5f,77.5f);
	gpath.lineTo(-107.5f,77.5f);
	gpath.lineTo(-107.5f,82.5f);
	gpath.lineTo(-112.5f,82.5f);
	gpath.lineTo(-112.5f,72.5f);
	gpath.lineTo(-117.5f,72.5f);
	gpath.lineTo(-117.5f,62.5f);
	gpath.lineTo(-122.5f,62.5f);
	gpath.lineTo(-122.5f,52.5f);
	gpath.lineTo(-127.5f,52.5f);
	gpath.lineTo(-127.5f,47.5f);
	gpath.lineTo(-112.5f,47.5f);
	gpath.lineTo(-112.5f,42.5f);
	gpath.lineTo(-102.5f,42.5f);
	gpath.lineTo(-102.5f,37.5f);
	gpath.lineTo(-92.5f,37.5f);
	gpath.lineTo(-92.5f,32.5f);
	gpath.lineTo(-87.5f,32.5f);
	gpath.lineTo(-87.5f,22.5f);
	gpath.lineTo(-82.5f,22.5f);
	gpath.lineTo(-82.5f,17.5f);
	gpath.lineTo(-77.5f,17.5f);
	gpath.lineTo(-77.5f,12.5f);
	gpath.lineTo(-67.5f,12.5f);
	gpath.lineTo(-67.5f,7.5f);
	gpath.lineTo(-62.5f,7.5f);
	gpath.lineTo(-62.5f,2.5f);
	gpath.lineTo(-57.5f,2.5f);
	gpath.lineTo(-57.5f,-7.5f);
	gpath.lineTo(-52.5f,-7.5f);
	gpath.lineTo(-52.5f,-12.5f);
	gpath.lineTo(-47.5f,-12.5f);
	gpath.lineTo(-47.5f,-22.5f);
	gpath.lineTo(-42.5f,-22.5f);
	gpath.lineTo(-42.5f,-32.5f);
	gpath.lineTo(-37.5f,-32.5f);
	gpath.lineTo(-37.5f,-62.5f);
	gpath.lineTo(-42.5f,-62.5f);
	gpath.lineTo(-42.5f,-67.5f);
	gpath.lineTo(-77.5f,-67.5f);
	gpath.lineTo(-77.5f,-62.5f);
	gpath.lineTo(-117.5f,-62.5f);
	gpath.lineTo(-117.5f,-57.5f);
	gpath.lineTo(-122.5f,-57.5f);
	gpath.lineTo(-122.5f,-47.5f);
	gpath.lineTo(-127.5f,-47.5f);
	gpath.lineTo(-127.5f,-37.5f);
	gpath.lineTo(-132.5f,-37.5f);
	gpath.lineTo(-132.5f,-17.5f);
	gpath.lineTo(-137.5f,-17.5f);
	gpath.lineTo(-137.5f,2.5f);
	gpath.lineTo(-142.5f,2.5f);
	gpath.lineTo(-142.5f,32.5f);
	gpath.lineTo(-137.5f,32.5f);
	gpath.lineTo(-137.5f,52.5f);
	gpath.lineTo(-142.5f,52.5f);
	gpath.lineTo(-142.5f,57.5f);
	gpath.lineTo(-147.5f,57.5f);
	gpath.lineTo(-147.5f,67.5f);
	gpath.lineTo(-152.5f,67.5f);
	gpath.lineTo(-152.5f,77.5f);
	gpath.lineTo(-147.5f,77.5f);
	gpath.lineTo(-147.5f,87.5f);
	gpath.lineTo(-152.5f,87.5f);
	gpath.lineTo(-152.5f,97.5f);
	gpath.lineTo(-157.5f,97.5f);
	gpath.lineTo(-157.5f,112.5f);
	gpath.lineTo(-162.5f,112.5f);
	gpath.lineTo(-162.5f,122.5f);
	gpath.lineTo(-167.5f,122.5f);
	gpath.lineTo(-167.5f,132.5f);
	gpath.lineTo(-172.5f,132.5f);
	gpath.lineTo(-172.5f,142.5f);
	gpath.lineTo(-180.0f,142.5f);

	g.draw(gpath);
	g.setColor(gold);
	gpath = new GeneralPath(GeneralPath.WIND_NON_ZERO);

	gpath.moveTo(-42.5f,180.0f);
	gpath.lineTo(-42.5f,172.5f);
	gpath.lineTo(-42.5f,172.5f);
	gpath.lineTo(-37.5f,172.5f);
	gpath.lineTo(-37.5f,167.5f);
	gpath.lineTo(-32.5f,167.5f);
	gpath.lineTo(-32.5f,157.5f);
	gpath.lineTo(-27.5f,157.5f);
	gpath.lineTo(-27.5f,147.5f);
	gpath.lineTo(-22.5f,147.5f);
	gpath.lineTo(-22.5f,127.5f);
	gpath.lineTo(-17.5f,127.5f);
	gpath.lineTo(-17.5f,112.5f);
	gpath.lineTo(-22.5f,112.5f);
	gpath.lineTo(-22.5f,107.5f);
	gpath.lineTo(-27.5f,107.5f);
	gpath.lineTo(-27.5f,102.5f);
	gpath.lineTo(-32.5f,102.5f);
	gpath.lineTo(-32.5f,97.5f);
	gpath.lineTo(-47.5f,97.5f);
	gpath.lineTo(-47.5f,92.5f);
	gpath.lineTo(-52.5f,92.5f);
	gpath.lineTo(-52.5f,72.5f);
	gpath.lineTo(-57.5f,72.5f);
	gpath.lineTo(-57.5f,42.5f);
	gpath.lineTo(-62.5f,42.5f);
	gpath.lineTo(-62.5f,27.5f);
	gpath.lineTo(-57.5f,27.5f);
	gpath.lineTo(-57.5f,22.5f);
	gpath.lineTo(-52.5f,22.5f);
	gpath.lineTo(-52.5f,12.5f);
	gpath.lineTo(-47.5f,12.5f);
	gpath.lineTo(-47.5f,7.5f);
	gpath.lineTo(-42.5f,7.5f);
	gpath.lineTo(-42.5f,2.5f);
	gpath.lineTo(-37.5f,2.5f);
	gpath.lineTo(-37.5f,-7.5f);
	gpath.lineTo(-32.5f,-7.5f);
	gpath.lineTo(-32.5f,-12.5f);
	gpath.lineTo(-27.5f,-12.5f);
	gpath.lineTo(-27.5f,-27.5f);
	gpath.lineTo(-22.5f,-27.5f);
	gpath.lineTo(-22.5f,-47.5f);
	gpath.lineTo(-17.5f,-47.5f);
	gpath.lineTo(-17.5f,-67.5f);
	gpath.lineTo(-22.5f,-67.5f);
	gpath.lineTo(-22.5f,-77.5f);
	gpath.lineTo(-27.5f,-77.5f);
	gpath.lineTo(-27.5f,-82.5f);
	gpath.lineTo(-47.5f,-82.5f);
	gpath.lineTo(-47.5f,-87.5f);
	gpath.lineTo(-77.5f,-87.5f);
	gpath.lineTo(-77.5f,-92.5f);
	gpath.lineTo(-87.5f,-92.5f);
	gpath.lineTo(-87.5f,-112.5f);
	gpath.lineTo(-92.5f,-112.5f);
	gpath.lineTo(-92.5f,-122.5f);
	gpath.lineTo(-97.5f,-122.5f);
	gpath.lineTo(-97.5f,-137.5f);
	gpath.lineTo(-92.5f,-137.5f);
	gpath.lineTo(-92.5f,-142.5f);
	gpath.lineTo(-82.5f,-142.5f);
	gpath.lineTo(-82.5f,-147.5f);
	gpath.lineTo(-72.5f,-147.5f);
	gpath.lineTo(-72.5f,-152.5f);
	gpath.lineTo(-67.5f,-152.5f);
	gpath.lineTo(-67.5f,-157.5f);
	gpath.lineTo(-62.5f,-157.5f);
	gpath.lineTo(-62.5f,-162.5f);
	gpath.lineTo(-57.5f,-162.5f);
	gpath.lineTo(-57.5f,-167.5f);
	gpath.lineTo(-52.5f,-167.5f);
	gpath.lineTo(-52.5f,-172.5f);
	gpath.lineTo(-47.5f,-172.5f);
	gpath.lineTo(-47.5f,-177.5f);
	gpath.lineTo(-42.5f,-177.5f);
	gpath.lineTo(-42.5f,-180.0f);

	g.draw(gpath);
	gpath = new GeneralPath(GeneralPath.WIND_NON_ZERO);

	gpath.moveTo(-180.0f,-147.5f);
	gpath.lineTo(-177.5f,-147.5f);
	gpath.lineTo(-167.5f,-147.5f);
	gpath.lineTo(-167.5f,-142.5f);
	gpath.lineTo(-157.5f,-142.5f);
	gpath.lineTo(-157.5f,-137.5f);
	gpath.lineTo(-147.5f,-137.5f);
	gpath.lineTo(-147.5f,-132.5f);
	gpath.lineTo(-142.5f,-132.5f);
	gpath.lineTo(-142.5f,-127.5f);
	gpath.lineTo(-147.5f,-127.5f);
	gpath.lineTo(-147.5f,-97.5f);
	gpath.lineTo(-152.5f,-97.5f);
	gpath.lineTo(-152.5f,-92.5f);
	gpath.lineTo(-157.5f,-92.5f);
	gpath.lineTo(-157.5f,-82.5f);
	gpath.lineTo(-162.5f,-82.5f);
	gpath.lineTo(-162.5f,-52.5f);
	gpath.lineTo(-157.5f,-52.5f);
	gpath.lineTo(-157.5f,-37.5f);
	gpath.lineTo(-162.5f,-37.5f);
	gpath.lineTo(-162.5f,-7.5f);
	gpath.lineTo(-167.5f,-7.5f);
	gpath.lineTo(-167.5f,32.5f);
	gpath.lineTo(-172.5f,32.5f);
	gpath.lineTo(-172.5f,52.5f);
	gpath.lineTo(-177.5f,52.5f);
	gpath.lineTo(-177.5f,77.5f);
	gpath.lineTo(-180.0f,77.5f);

	g.draw(gpath);
	gpath = new GeneralPath(GeneralPath.WIND_NON_ZERO);

	gpath.moveTo(82.5f,57.5f);
	gpath.lineTo(87.5f,57.5f);
	gpath.lineTo(87.5f,42.5f);
	gpath.lineTo(92.5f,42.5f);
	gpath.lineTo(92.5f,22.5f);
	gpath.lineTo(97.5f,22.5f);
	gpath.lineTo(97.5f,-17.5f);
	gpath.lineTo(92.5f,-17.5f);
	gpath.lineTo(92.5f,-22.5f);
	gpath.lineTo(87.5f,-22.5f);
	gpath.lineTo(87.5f,-27.5f);
	gpath.lineTo(82.5f,-27.5f);
	gpath.lineTo(82.5f,-37.5f);
	gpath.lineTo(87.5f,-37.5f);
	gpath.lineTo(87.5f,-47.5f);
	gpath.lineTo(92.5f,-47.5f);
	gpath.lineTo(92.5f,-57.5f);
	gpath.lineTo(87.5f,-57.5f);
	gpath.lineTo(87.5f,-67.5f);
	gpath.lineTo(82.5f,-67.5f);
	gpath.lineTo(82.5f,-72.5f);
	gpath.lineTo(77.5f,-72.5f);
	gpath.lineTo(77.5f,-77.5f);
	gpath.lineTo(62.5f,-77.5f);
	gpath.lineTo(62.5f,-72.5f);
	gpath.lineTo(57.5f,-72.5f);
	gpath.lineTo(57.5f,-67.5f);
	gpath.lineTo(52.5f,-67.5f);
	gpath.lineTo(52.5f,-37.5f);
	gpath.lineTo(57.5f,-37.5f);
	gpath.lineTo(57.5f,-27.5f);
	gpath.lineTo(62.5f,-27.5f);
	gpath.lineTo(62.5f,-22.5f);
	gpath.lineTo(57.5f,-22.5f);
	gpath.lineTo(57.5f,-12.5f);
	gpath.lineTo(52.5f,-12.5f);
	gpath.lineTo(52.5f,-7.5f);
	gpath.lineTo(47.5f,-7.5f);
	gpath.lineTo(47.5f,-2.5f);
	gpath.lineTo(42.5f,-2.5f);
	gpath.lineTo(42.5f,2.5f);
	gpath.lineTo(37.5f,2.5f);
	gpath.lineTo(37.5f,12.5f);
	gpath.lineTo(32.5f,12.5f);
	gpath.lineTo(32.5f,22.5f);
	gpath.lineTo(27.5f,22.5f);
	gpath.lineTo(27.5f,32.5f);
	gpath.lineTo(22.5f,32.5f);
	gpath.lineTo(22.5f,47.5f);
	gpath.lineTo(17.5f,47.5f);
	gpath.lineTo(17.5f,67.5f);
	gpath.lineTo(22.5f,67.5f);
	gpath.lineTo(22.5f,77.5f);
	gpath.lineTo(27.5f,77.5f);
	gpath.lineTo(27.5f,82.5f);
	gpath.lineTo(32.5f,82.5f);
	gpath.lineTo(32.5f,87.5f);
	gpath.lineTo(47.5f,87.5f);
	gpath.lineTo(47.5f,92.5f);
	gpath.lineTo(67.5f,92.5f);
	gpath.lineTo(67.5f,87.5f);
	gpath.lineTo(72.5f,87.5f);
	gpath.lineTo(72.5f,82.5f);
	gpath.lineTo(77.5f,82.5f);
	gpath.lineTo(77.5f,77.5f);
	gpath.lineTo(82.5f,77.5f);
	gpath.lineTo(82.5f,57.5f);

	g.draw(gpath);
	gpath = new GeneralPath(GeneralPath.WIND_NON_ZERO);

	gpath.moveTo(72.5f,-102.5f);
	gpath.lineTo(72.5f,-112.5f);
	gpath.lineTo(77.5f,-112.5f);
	gpath.lineTo(77.5f,-157.5f);
	gpath.lineTo(72.5f,-157.5f);
	gpath.lineTo(72.5f,-180.0f);

	g.draw(gpath);
	gpath = new GeneralPath(GeneralPath.WIND_NON_ZERO);

	gpath.moveTo(57.5f,-180.0f);
	gpath.lineTo(57.5f,-167.5f);
	gpath.lineTo(52.5f,-167.5f);
	gpath.lineTo(52.5f,-162.5f);
	gpath.lineTo(47.5f,-162.5f);
	gpath.lineTo(47.5f,-157.5f);
	gpath.lineTo(42.5f,-157.5f);
	gpath.lineTo(42.5f,-152.5f);
	gpath.lineTo(37.5f,-152.5f);
	gpath.lineTo(37.5f,-142.5f);
	gpath.lineTo(32.5f,-142.5f);
	gpath.lineTo(32.5f,-107.5f);
	gpath.lineTo(37.5f,-107.5f);
	gpath.lineTo(37.5f,-102.5f);
	gpath.lineTo(42.5f,-102.5f);
	gpath.lineTo(42.5f,-97.5f);
	gpath.lineTo(52.5f,-97.5f);
	gpath.lineTo(52.5f,-92.5f);
	gpath.lineTo(62.5f,-92.5f);
	gpath.lineTo(62.5f,-97.5f);
	gpath.lineTo(67.5f,-97.5f);
	gpath.lineTo(67.5f,-102.5f);
	gpath.lineTo(72.5f,-102.5f);

	g.draw(gpath);
	gpath = new GeneralPath(GeneralPath.WIND_NON_ZERO);

        gpath.moveTo(77.5f,180.0f);
	gpath.lineTo(77.5f,162.5f);
	gpath.lineTo(82.5f,162.5f);
	gpath.lineTo(82.5f,147.5f);
	gpath.lineTo(72.5f,147.5f);
	gpath.lineTo(72.5f,157.5f);
	gpath.lineTo(67.5f,157.5f);
	gpath.lineTo(67.5f,167.5f);
	gpath.lineTo(62.5f,167.5f);
	gpath.lineTo(62.5f,180.0f);

	g.draw(gpath);
	gpath = new GeneralPath(GeneralPath.WIND_NON_ZERO);

	gpath.moveTo(162.5f,180.0f);
	gpath.lineTo(162.5f,147.5f);
	gpath.lineTo(167.5f,147.5f);
	gpath.lineTo(167.5f,132.5f);
	gpath.lineTo(172.5f,132.5f);
	gpath.lineTo(172.5f,117.5f);
	gpath.lineTo(177.5f,117.5f);
	gpath.lineTo(177.5f,77.5f);
	gpath.lineTo(180.0f,77.5f);

	g.draw(gpath);
	gpath = new GeneralPath(GeneralPath.WIND_NON_ZERO);

	gpath.moveTo(162.5f,-180.0f);
	gpath.lineTo(162.5f,-177.5f);
	gpath.lineTo(167.5f,-177.5f);
	gpath.lineTo(167.5f,-167.5f);
	gpath.lineTo(172.5f,-167.5f);
	gpath.lineTo(172.5f,-157.5f);
	gpath.lineTo(177.5f,-157.5f);
	gpath.lineTo(177.5f,-147.5f);
	gpath.lineTo(180.0f,-147.5f);

	g.draw(gpath);
	g.setColor(black);
    }

    public double defcor2degreeX(int x)
    {
        return (10 * (x-1)) - 180.0;
    }

    public double defcor2degreeY(int y)
    {
        return (10 * (y-1)) - 180.0;
    }

    private void inputData(String filename)
    {
        File file1 = null;
	try
        {
	    file1 = new File(filename);
	    BufferedReader InputFile = new BufferedReader(new FileReader(file1));
	    String currentRecord = InputFile.readLine();
	    int count = 1;
	    while(currentRecord != null)
            {
		String[] stArray = currentRecord.split("\t");

		double d1 = Double.parseDouble(stArray[0]);
		double d2 = Double.parseDouble(stArray[1]);
		this.data.add(new Point.Double(d1,d2));
		count++;
		currentRecord = InputFile.readLine();
	    }
	}
        catch(IOException e)
        {
            new ErrorClose(this, true, "I/O Error");
        }
    }

    private String workpath = SwiftModellerView.workpath;
    private String sep = SwiftModellerView.sep;
    private int notrama = EvaluationGraph.notrama;
    Shape mAxes;
    int mLength = 180;
    int mTick = 4;
    double imageHeight;
    double imageWidth;
    Vector<Point2D.Double> data;
    private static final float POINT_RADIUS = 0.7f;
    private static final float POINT_DIAMETER = POINT_RADIUS * 2;
    private Graphics2D g2d;
    final static BasicStroke stroke = new BasicStroke(2.0f);
    final static BasicStroke wideStroke = new BasicStroke(8.0f);
    final static float dash1[] = {1.0f};
    final static BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, new float[] {3, 4}, 0.0f);
}