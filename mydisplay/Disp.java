package mydisplay;
import myshapes.*;
import lights.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class Disp extends JComponent
{
    Menu myMenu; //drugie okno
    ArrayList<MyShape> shapeList;
    MapPanelListener clickListen;
    MapPanelDrag dragListen;
    LightListener lightListen;
    LightPoint myLightPoint;

    //ButtonGroup usageModes;
    //JRadioButton designMode, playMode;

    public Disp()
    {
        myLightPoint = new LightPoint(3600);
        shapeList = new ArrayList<MyShape>();
        JFrame myWindow = new JFrame();
        myMenu = new Menu();
        myWindow.setSize(600,600);
        myWindow.setVisible(true);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clickListen = new MapPanelListener(this);
        dragListen = new MapPanelDrag(this);
        lightListen = new LightListener(this);
        this.addMouseListener(clickListen);
        this.addMouseMotionListener(dragListen);
        myWindow.addKeyListener(lightListen);

        myWindow.add(this);
    }

    public int xForPrint( int x) { return (int)( x + 0.5 * this.getSize().getWidth());}
    public int yForPrint( int y) { return (int)(-y + 0.5 * this.getSize().getHeight());}

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
            ArrayList<Wall> walllist = shapeList.get(i).getWalls();
            for(int j=0; j < walllist.size(); j++)
              g2.draw( new Line2D.Double( this.getXforPrint((int)walllist.get(j).getP1().getWidth() ),
                                          this.getYforPrint((int)walllist.get(j).getP1().getHeight()),
                                          this.getXforPrint((int)walllist.get(j).getP2().getWidth() ),
                                          this.getYforPrint((int)walllist.get(j).getP2().getHeight()) ) );
        }

        g2.setColor(new Color (63,180,250));
        ArrayList<Ray> myrays = myLightPoint.getRays();
        for(int i=0; i<myrays.size(); i++)
        {
            myrays.get(i).resetHit();
            for(int j=0; j<shapeList.size(); j++)
            {
              ArrayList<Wall> mywalls = shapeList.get(j).getWalls();
              for(int k=0; k < mywalls.size(); k++)
                  myrays.get(i).casting(mywalls.get(k));
            }

            g2.fill( new Ellipse2D.Double( this.getXforPrint((int)myrays.get(i).getHit().getWidth()) -5,
                                           this.getYforPrint((int)myrays.get(i).getHit().getHeight())-5, 10, 10) );
            //MyShape.print("po X: " + myrays.get(i).getHit().getWidth()
            //              + " Y: " + myrays.get(i).getHit().getHeight());
            g2.draw( new Line2D.Double( this.getXforPrint((int)myLightPoint.getox()),
            /**/                        this.getYforPrint((int)myLightPoint.getoy()),
            /**/                        this.getXforPrint((int)myrays.get(i).getHit().getWidth()  ),
            /**/                        this.getYforPrint((int)myrays.get(i).getHit().getHeight() ) ) );
        }

        g2.setColor(Color.ORANGE);
        g2.fill(new Ellipse2D.Double( this.xForPrint(myLightPoint.getox()) - 0.5*myLightPoint.getdim(),
                                      this.yForPrint(myLightPoint.getoy()) - 0.5*myLightPoint.getdim(),
                                      myLightPoint.getdim(), myLightPoint.getdim()));
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

    class LightListener implements KeyListener
    {
        Disp disp;
        public LightListener (Disp disp) {this.disp = disp;}
        public void keyPressed(KeyEvent arg)
        {
          char c = arg.getKeyChar();
          if(c == 'w' || arg.getKeyCode() == KeyEvent.VK_UP) {myLightPoint.pressedW(); disp.repaint();}
          if(c == 'a' || arg.getKeyCode() == KeyEvent.VK_LEFT) {myLightPoint.pressedA(); disp.repaint();}
          if(c == 's' || arg.getKeyCode() == KeyEvent.VK_DOWN) {myLightPoint.pressedS(); disp.repaint();}
          if(c == 'd' || arg.getKeyCode() == KeyEvent.VK_RIGHT) {myLightPoint.pressedD(); disp.repaint();}
        }
        public void keyReleased(KeyEvent arg)  {}
        public void keyTyped(KeyEvent arg)     {}
    }
      //geom functions for printing
    public int getXforPrint( int x) { return (int)( x + 0.5 * this.getSize().getWidth());}
    public int getYforPrint( int y) { return (int)(-y + 0.5 * this.getSize().getHeight());}
    public Dimension getPointPrint (Dimension p1)
    {
      int a = (int) this.getXforPrint( (int) p1.getWidth()  );
      int b = (int) this.getYforPrint( (int) p1.getHeight() );
      return new Dimension(a,b);
    }

    public Wall getWallForPrint(Wall mywall)
    {
      Dimension p1 = this.getPointPrint(mywall.getP1());
      Dimension p2 = this.getPointPrint(mywall.getP2());
      return new Wall(p1,p2);
    }
}
