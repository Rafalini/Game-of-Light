package myshapes;

import java.lang.Math.*;

public class MyShape
{
  int Xcent, Ycent;
  double radians;

  public MyShape()
  {
    Xcent = 0;
    Ycent = 0;
    radians = 0;
  }

  public MyShape(int x, int y, int rot)
  {
    Xcent = x;
    Ycent = y;
    radians = java.lang.Math.toRadians(rot);
  }

  public void print(String message)
  {
    System.out.println(message);
  }
}
