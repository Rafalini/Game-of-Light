package myshapes;

import mydisplay.Disp;
import java.lang.Math.*;

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

  public abstract int getComplexity();
  public abstract int [] [] getXoYpoints ();
  public abstract int [] [] getPointsForPrint(); //returns those points
  public abstract void sizeUpdate (int a, int b); //updates size of figure

  public int getXforXoY( int x) { return (int)( x - 0.5 * disp.getSize().getWidth());}
  public int getYforXoY( int y) { return (int)(-y + 0.5 * disp.getSize().getHeight());}

  public int getXforPrint( int x) { return (int)( x + 0.5 * disp.getSize().getWidth());}
  public int getYforPrint( int y) { return (int)(-y + 0.5 * disp.getSize().getHeight());}
  //public double getAngle() {return radians;}
}
