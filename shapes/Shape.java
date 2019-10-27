package shapes;

import java.lang.Math.*;

public class Shape
{
  int Xcent, Ycent;
  double radians;

  public Shape()
  {
    Xcent = 0;
    Ycent = 0;
    radians = 0;
  }

  public Shape(int x, int y, int rot)
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
