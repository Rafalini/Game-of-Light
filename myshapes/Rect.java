package myshapes;

import java.util.*;
import java.lang.Math.*;

public class Rect extends MyShape
{
  int xLefUp1, xRigUp2;
  int xRigDo3, xLefDo4;

  int yLefUp1, yRigUp2;
  int yRigDo3, yLefDo4;

  public Rect (int x, int y, int rot, int ax, int by)
  {
    super(x,y,rot);
    int xLef = (int) (x - 0.5 * ax);
    int xRig = (int) (x + 0.5 * ax);

    int yUp =  (int) (x - 0.5 * by);
    int yDow = (int) (x + 0.5 * by);

    this.xLefUp1 = (int) ((xLef-x)*Math.cos(super.radians)-(yUp-y)*Math.sin(super.radians)+x);
    this.xRigUp2 = (int) ((xRig-x)*Math.cos(super.radians)-(yUp-y)*Math.sin(super.radians)+x);
    this.xRigDo3 = (int) ((xRig-x)*Math.cos(super.radians)-(yDow-y)*Math.sin(super.radians)+x);
    this.xLefDo4 = (int) ((xLef-x)*Math.cos(super.radians)-(yDow-y)*Math.sin(super.radians)+x);

    this.yLefUp1 = (int) ((xLef-x)*Math.sin(super.radians)+(yUp-y)*Math.cos(super.radians)+y);
    this.yRigUp2 = (int) ((xRig-x)*Math.sin(super.radians)+(yUp-y)*Math.cos(super.radians)+y);
    this.yRigDo3 = (int) ((xRig-x)*Math.sin(super.radians)+(yDow-y)*Math.cos(super.radians)+y);
    this.yLefDo4 = (int) ((xLef-x)*Math.sin(super.radians)+(yDow-y)*Math.cos(super.radians)+y);
  }

  public int [] [] points ()
  {
    int [] [] points = new int [4][2];
    points[0][0] = xLefUp1; points[0][1] = xRigUp2;
    points[1][0] = xRigUp2; points[1][1] = xRigUp2;
    points[2][0] = xRigDo3; points[2][1] = xRigUp2;
    points[3][0] = xLefDo4; points[3][1] = xRigUp2;
    return points;
  }

  public void sizeupdate(int ax, int by)
  {
    
  }
}
