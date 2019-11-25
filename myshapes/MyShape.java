package myshapes;

import mydisplay.Disp;
import java.lang.Math.*;
import java.awt.geom.Line2D;
import java.awt.Dimension;
import java.util.ArrayList;

public abstract class MyShape
{
  Disp disp;
  int xCent, yCent;
  double radians;

  public MyShape (Disp disp, int x, int y, int rot)
  {
    this.disp=disp;
    xCent = getXforXoY(x);
    yCent = getYforXoY(y);
    radians = java.lang.Math.toRadians(rot);
    print("Utworzono Rect, x:"+xCent+" y:"+yCent);
  }

  public static void print(String message)
  {
    System.out.println(message);
  }

  public abstract ArrayList<Dimension> getXoYpoints ();
  public abstract ArrayList<Wall> wallsList(); //returns those points
  public abstract void sizeUpdate (int a, int b); //updates size of figure

  public int getXforXoY( int x) { return (int)( x - 0.5 * disp.getSize().getWidth());}
  public int getYforXoY( int y) { return (int)(-y + 0.5 * disp.getSize().getHeight());}
  public Dimension getPointXoY (Dimension p1)
  {
    int a,b;
    a = (int) this.getXforXoY( (int) p1.getWidth()  );
    b = (int) this.getYforXoY( (int) p1.getHeight() );
    return new Dimension(a,b);
  }

  public int getXforPrint( int x) { return (int)( x + 0.5 * disp.getSize().getWidth());}
  public int getYforPrint( int y) { return (int)(-y + 0.5 * disp.getSize().getHeight());}
  public Dimension getPointPrint (Dimension p1)
  {
    int a,b;
    a = (int) this.getXforPrint( (int) p1.getWidth()  );
    b = (int) this.getYforPrint( (int) p1.getHeight() );
    return new Dimension(a,b);
  }
  //public double getAngle() {return radians;}
}
