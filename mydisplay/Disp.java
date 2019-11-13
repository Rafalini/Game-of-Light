package mydisplay;
import myshapes.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class Disp extends JComponent
{
    Menu myMenu; //drugie okno
    ArrayList<MyShape> shapeList;
    MapPanelListener clickListen;
    MapPanelDrag dragListen;

    public Disp()
    {
        shapeList = new ArrayList<MyShape>();
        JFrame myWindow = new JFrame();
        myMenu = new Menu();
        myWindow.setSize(600,600);
        myWindow.setVisible(true);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clickListen = new MapPanelListener(this);
        dragListen = new MapPanelDrag(this);
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
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,getWidth(),getHeight());
        g2.setColor(Color.BLACK);
        g2.fillRect(5,5,getWidth()-10,getHeight()-10);
        g2.setColor(Color.WHITE);

        for(int i=0; i<shapeList.size(); i++)
        {
          int complexity = shapeList.get(i).getComplexity();
          int points [] [] = shapeList.get(i).getPointsForPrint();
          for(int j=0; j+1<complexity; j++)
              g2.drawLine(points[j][0], points[j][1], points[j+1][0], points[j+1][1]);
          g2.drawLine(points[0][0], points[0][1], points[complexity-1][0], points[complexity-1][1]);
        }
    }

    class MapPanelListener implements MouseListener//, MouseMotionListener         //Myszka na mapce
    {
        Disp disp;
        public MapPanelListener (Disp disp) { this.disp = disp; }
        public void mouseClicked(MouseEvent e)  {}
        public void mousePressed(MouseEvent e)
        {
            shapeList.add(new Rect(disp, e.getX(), e.getY()));
        }
        public void mouseReleased(MouseEvent e)
        {
            disp.repaint();
            int complexity = disp.shapeList.get(shapeList.size()-1).getComplexity();
            int points [] [] = disp.shapeList.get(shapeList.size()-1).getXoYpoints();
            for(int i=0; i<complexity; i++)
                MyShape.print(i+1+" X: "+points[i][0]+" Y: "+points[i][1]);
        }
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    class MapPanelDrag implements MouseMotionListener         //Myszka na mapce
    {
        Disp disp;
        public MapPanelDrag (Disp disp) { this.disp = disp; }
        public void mouseDragged(MouseEvent e)
        {
          shapeList.get(shapeList.size()-1).sizeUpdate(e.getX(), e.getY());
          disp.repaint();
        }
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {}
    }
}
