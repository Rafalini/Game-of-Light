package shapes;

import java.util.*;

public class Rect extends Shape
{
  int xLefUp1, xRigUp2;
  int xRigDo3, xLefDo4;

  int yLefUp1, yRigUp2;
  int yRigDo3, yLefDo4;

  public Rect (int x, int y, int rot, int ax, int by)
  {
    super(x,y,rot);
    int xLef = x - 0.5 * ax;
    int xRig = x + 0.5 * ax;

    int Yup = x - 0.5 * by;
    int yDow = x + 0.5 * by;

    xLefUp1 = (int) (xLef-x)Math.cos(super.radians)-(Yup-y)Math.sin(super.radians)+x;
    xRigUp2 = (int) (xRig-x)Math.cos(super.radians)-(Yup-y)Math.sin(super.radians)+x;
    xRigDo3 = (int) (xRig-x)Math.cos(super.radians)-(YDow-y)Math.sin(super.radians)+x;
    xLefDo4 = (int) (xLef-x)Math.cos(super.radians)-(YDow-y)Math.sin(super.radians)+x;

    yLefUp1 = (int) (xLef-x)Math.sin(super.radians)+(Yup-y)Math.cos(super.radians)+y;
    yRigUp2 = (int) (xRig-x)Math.sin(super.radians)+(Yup-y)Math.cos(super.radians)+y;
    yRigDo3 = (int) (xRig-x)Math.sin(super.radians)+(YDow-y)Math.cos(super.radians)+y;
    yLefDo4 = (int) (xLef-x)Math.sin(super.radians)+(YDow-y)Math.cos(super.radians)+y;
  }

  public int points ()
  {
    int [] [] points = new int [4][2];
    points[0][0] = xLefUp1; points[0][1] = xRigUp2;
    points[1][0] = xRigUp2; points[1][1] = xRigUp2;
    points[2][0] = xRigDo3; points[2][1] = xRigUp2;
    points[3][0] = xLefDo4; points[3][1] = xRigUp2;
    return points;
  }
}
