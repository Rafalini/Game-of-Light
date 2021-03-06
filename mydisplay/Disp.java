package mydisplay;
import myshapes.*;
import lights.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.lang.Math;

public class Disp extends JComponent
{
    Menu myMenu; //drugie okno
    ArrayList<MyShape> shapeList;
    MapPanelListener clickListen;
    MapPanelDrag dragListen;
    LightListener lightListen;
    ArrayList<LightPoint> myLightPoint;

    //ButtonGroup usageModes;
    //JRadioButton designMode, playMode;

    public Disp()
    {
        myLightPoint = new ArrayList<LightPoint>();
        myLightPoint.add(new LightPoint(0,0,2000));
        myLightPoint.add(new LightPoint(10,0,2000));
        myLightPoint.add(new LightPoint(0,10,2000));
        myLightPoint.add(new LightPoint(-10,0,2000));
        myLightPoint.add(new LightPoint(0,-10,2000));
        //myLightPoint.add(new LightPoint(100,0,3600));
        shapeList = new ArrayList<MyShape>();
        shapeList.add(new Rect(this, 0,0));
        shapeList.get(shapeList.size()-1).sizeUpdate(600-4, 600-4);
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

        //g2.setColor(new Color (63,180,250)); //light blue
        //g2.setColor(Color.LIGHT_GRAY);
        for(int e = 0; e < myLightPoint.size(); e++)
        {
          ArrayList<Ray> myrays = myLightPoint.get(e).getRays();
          for(int i=0; i<myrays.size(); i++)
          {
              myrays.get(i).resetHit();
              for(int j=0; j<shapeList.size(); j++)
              {
                ArrayList<Wall> mywalls = shapeList.get(j).getWalls();
                for(int k=0; k < mywalls.size(); k++)
                    myrays.get(i).casting(mywalls.get(k));
              }
              g2.setColor(new Color(160,160,160, 10));
              //g2.fill( new Ellipse2D.Double( this.getXforPrint((int)myrays.get(i).getHit().getWidth()) -5,
              //                               this.getYforPrint((int)myrays.get(i).getHit().getHeight())-5, 10, 10) );
              //MyShape.print("po X: " + myrays.get(i).getHit().getWidth()
              //              + " Y: " + myrays.get(i).getHit().getHeight());
              g2.draw( new Line2D.Double( this.getXforPrint((int)myLightPoint.get(e).getox()),
              /**/                        this.getYforPrint((int)myLightPoint.get(e).getoy()),
              /**/                        this.getXforPrint((int)myrays.get(i).getHit().getWidth()  ),
              /**/                        this.getYforPrint((int)myrays.get(i).getHit().getHeight() ) ) );
          }
      }

      g2.setColor(Color.ORANGE);
      for(int e = 0; e < myLightPoint.size(); e++)
      g2.fill(new Ellipse2D.Double( this.xForPrint(myLightPoint.get(e).getox()) - 0.5*myLightPoint.get(e).getdim(),
                                    this.yForPrint(myLightPoint.get(e).getoy()) - 0.5*myLightPoint.get(e).getdim(),
                                    myLightPoint.get(e).getdim(), myLightPoint.get(e).getdim()));
    }

    class MapPanelListener implements MouseListener//, MouseMotionListener         //Myszka na mapce
    {
        Disp disp;
        public MapPanelListener (Disp disp) { this.disp = disp; }
        public void mouseClicked(MouseEvent e)  {}
        public void mousePressed(MouseEvent e)
        {
          if(shapeList.size() % 3 == 0)
            shapeList.add(new Rect(disp, e.getX(), e.getY()));
          else if(shapeList.size() % 3 == 1)
            shapeList.add(new Circ(disp, e.getX(), e.getY()));
          else if(shapeList.size() % 3 == 2)
            shapeList.add(new myshapes.Polygon(disp, e.getX(), e.getY(), ((int)(Math.random()*100))%9 ));
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
          for(int e = 0; e < myLightPoint.size(); e++)
          {
            if(c == 'w' || arg.getKeyCode() == KeyEvent.VK_UP) {myLightPoint.get(e).pressedW(disp); disp.repaint();}
            if(c == 'a' || arg.getKeyCode() == KeyEvent.VK_LEFT) {myLightPoint.get(e).pressedA(disp); disp.repaint();}
            if(c == 's' || arg.getKeyCode() == KeyEvent.VK_DOWN) {myLightPoint.get(e).pressedS(disp); disp.repaint();}
            if(c == 'd' || arg.getKeyCode() == KeyEvent.VK_RIGHT) {myLightPoint.get(e).pressedD(disp); disp.repaint();}
          }
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
