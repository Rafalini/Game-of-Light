package myshapes;

import mydisplay.Disp;
import java.util.*;
import java.lang.Math.*;

public class Rect extends MyShape
{
  int xLefUp1, xRigUp2; //  1 ------- 2
  int xRigDo3, xLefDo4; //  |         |
                        //  |         | all x's and y's in XoY where middle of the screen is (0,0)
  int yLefUp1, yRigUp2; //  |         |
  int yRigDo3, yLefDo4; //  4 ------- 3

  public Rect (Disp disp, int x, int y)
  {
    super(disp,x,y,0);  //convertion to XoY with (0,0) in the middle of the screen
    xLefUp1 = super.xCent; yLefUp1 = super.yCent;
    xRigUp2 = super.xCent; yRigUp2 = super.yCent;
    xRigDo3 = super.xCent; yRigDo3 = super.yCent;
    xLefDo4 = super.xCent; yLefDo4 = super.yCent;
  }

  public int getComplexity() {return 4;} //number of points to draw a figure

  public int [] [] getXoYpoints ()
  {
    int [] [] points = new int [4][2];
    points[0][0] = xLefUp1; points[0][1] = yLefUp1;
    points[1][0] = xRigUp2; points[1][1] = yRigUp2;
    points[2][0] = xRigDo3; points[2][1] = yRigDo3;
    points[3][0] = xLefDo4; points[3][1] = yLefDo4;
    return points;
  }
  public int [] [] getPointsForPrint()
  {
    int [] [] points = new int [4][2];
    points[0][0] = super.getXforPrint(xLefUp1); points[0][1] = super.getYforPrint(yLefUp1);
    points[1][0] = super.getXforPrint(xRigUp2); points[1][1] = super.getYforPrint(yRigUp2);
    points[2][0] = super.getXforPrint(xRigDo3); points[2][1] = super.getYforPrint(yRigDo3);
    points[3][0] = super.getXforPrint(xLefDo4); points[3][1] = super.getYforPrint(yLefDo4);
    return points;
  }

  public void sizeUpdate(int clickX, int clickY)
  {
    clickX = super.getXforXoY(clickX);
    clickY = super.getYforXoY(clickY);
    xLefUp1 = 2*super.xCent - clickX; yLefUp1 = 2*super.yCent - clickY;
    xRigUp2 = clickX;                 yRigUp2 = 2*super.yCent - clickY;
    xRigDo3 = clickX;                 yRigDo3 = clickY;
    xLefDo4 = 2*super.xCent - clickX; yLefDo4 = clickY;
    /*this.xLefUp1 = (int) ((xLef-x)*Math.cos(super.radians)-(yUp-y)*Math.sin(super.radians)+x);
    this.xRigUp2 = (int) ((xRig-x)*Math.cos(super.radians)-(yUp-y)*Math.sin(super.radians)+x);
    this.xRigDo3 = (int) ((xRig-x)*Math.cos(super.radians)-(yDow-y)*Math.sin(super.radians)+x);
    this.xLefDo4 = (int) ((xLef-x)*Math.cos(super.radians)-(yDow-y)*Math.sin(super.radians)+x);

    this.yLefUp1 = (int) ((xLef-x)*Math.sin(super.radians)+(yUp-y)*Math.cos(super.radians)+y);
    this.yRigUp2 = (int) ((xRig-x)*Math.sin(super.radians)+(yUp-y)*Math.cos(super.radians)+y);
    this.yRigDo3 = (int) ((xRig-x)*Math.sin(super.radians)+(yDow-y)*Math.cos(super.radians)+y);
    this.yLefDo4 = (int) ((xLef-x)*Math.sin(super.radians)+(yDow-y)*Math.cos(super.radians)+y);*/
  }
}
