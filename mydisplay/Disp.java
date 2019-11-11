package mydisplay;
import myshapes.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class Disp extends JComponent
{
    MyShape printfun; //do wypisywania na koncole
    Menu myMenu; //drugie okno
    ArrayList<MyShape> shapeList;
    MapPanelListener clickListen;
    MapPanelDrag dragListen;

    public Disp(MyShape majn)
    {
        shapeList = new ArrayList<MyShape>();
        //shapeList.add(new Rect(10,10,0,10,10));
        printfun = majn;
        JFrame myWindow = new JFrame();
        myMenu = new Menu();
        myWindow.setSize(600,600);
        myWindow.setVisible(true);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clickListen = new MapPanelListener();
        dragListen = new MapPanelDrag();
        this.addMouseListener(clickListen);
        this.addMouseMotionListener(dragListen);
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

        for(int i=0; i<shapeList.size(); i++)
        {
          if(shapeList.get(i) instanceof Rect)
          {
            //int [] [] points = shapeList.get(i).points();
            //for(int j=0; j<4; j++)
              //g2.drawLine();
          }
        }
    }

    class MapPanelListener implements MouseListener//, MouseMotionListener         //Myszka na mapce
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
        public void mouseEntered(MouseEvent e) {printfun.print("aaa");}
        public void mouseExited(MouseEvent e) {printfun.print("bbb");}
        public void mouseMoved(MouseEvent e) {printfun.print("ccc");}
    }

    class MapPanelDrag implements MouseMotionListener         //Myszka na mapce
    {
        public void mouseDragged(MouseEvent e)
        {
          printfun.print("XDrag: "+e.getX()+" YDrag: "+e.getY());
        }
        public void mouseEntered(MouseEvent e) {printfun.print("aaa");}
        public void mouseExited(MouseEvent e) {printfun.print("bbb");}
        public void mouseMoved(MouseEvent e) {printfun.print("ccc");}
    }
}
