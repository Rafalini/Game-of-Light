package display;
import shapes.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class Disp extends JComponent
{
    shapes.Shape printfun;
    Menu myMenu;
    int Xcoord, Ycoord;
    public Disp(shapes.Shape majn)
    {
        printfun = majn;
        JFrame myWindow = new JFrame();
        myMenu = new Menu();
        myWindow.setSize(600,600);
        myWindow.setVisible(true);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMouseMotionListener(new MapPanelListener());
        myWindow.add(this);
    }

    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,getWidth(),getHeight());
    }

    class MapPanelListener implements MouseMotionListener, MouseListener         //Myszka na mapce
    {
        public void mouseClicked(MouseEvent e)
        {
              printfun.print("Xcoord: "+e.getX()+" Ycoord: "+e.getY());
        }
        public void mousePressed(MouseEvent e)
        {
            printfun.print("Xpress: "+e.getX()+" Ypress: "+e.getY());
        }
        public void mouseReleased(MouseEvent e)
        {
          printfun.print("Xrelease: "+e.getX()+" Yrelease: "+e.getY());
        }
        public void mouseDragged(MouseEvent e)
        {
          printfun.print("XDrag: "+e.getX()+" YDrag: "+e.getY());
        }
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {}
    }
}
