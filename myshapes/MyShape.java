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
  }

  //public static void print(String message)   {System.out.println(message);}

  public abstract ArrayList<Wall> getWalls();            //returns those points
  public abstract void sizeUpdate (int a, int b);         //updates size of figure

  public int getXforXoY( int x) { return (int)( x - 0.5 * disp.getSize().getWidth());}
  public int getYforXoY( int y) { return (int)(-y + 0.5 * disp.getSize().getHeight());}
  public Dimension getPointXoY (Dimension p1)
  {
    int a = (int) getXforXoY( (int) p1.getWidth()  );
    int b = (int) getYforXoY( (int) p1.getHeight() );
    return new Dimension(a,b);
  }

  public Wall getWallForXoY(Wall mywall)
  {
    Dimension p1 = this.getPointXoY(mywall.getP1());
    Dimension p2 = this.getPointXoY(mywall.getP2());
    return new Wall(p1,p2);
  }
}
