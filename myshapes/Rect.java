package myshapes;

import mydisplay.*;
import java.util.*;
import java.lang.Math.*;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.geom.Line2D;

public class Rect extends MyShape
{
  ArrayList<Wall> mywalls;
  ArrayList<Dimension> points;

  public Rect (Disp disp, int x, int y)
  {
    super(disp,x,y,0);  //convertion to XoY with (0,0) in the middle of the screen
    mywalls = new ArrayList<Wall>();
    points = new ArrayList<Dimension>();
    points.add(new Dimension(super.xCent, super.yCent));
    points.add(new Dimension(super.xCent, super.yCent));
    points.add(new Dimension(super.xCent, super.yCent));
    points.add(new Dimension(super.xCent, super.yCent));

    mywalls.add(new Wall(super.xCent, super.yCent, super.xCent, super.yCent));
    mywalls.add(new Wall(super.xCent, super.yCent, super.xCent, super.yCent));
    mywalls.add(new Wall(super.xCent, super.yCent, super.xCent, super.yCent));
    mywalls.add(new Wall(super.xCent, super.yCent, super.xCent, super.yCent));
  }

  public ArrayList<Dimension> getXoYpoints ()     {return points;}
  public ArrayList<Wall> wallsList()    {return mywalls;}

  public void sizeUpdate(int clickX, int clickY)
  {
    clickX = super.getXforXoY(clickX);
    clickY = super.getYforXoY(clickY);
    points.get(0).setSize(2*super.xCent - clickX, 2*super.yCent - clickY);
    points.get(1).setSize(clickX,                 2*super.yCent - clickY);
    points.get(2).setSize(clickX,                                 clickY);
    points.get(3).setSize(2*super.xCent - clickX,                 clickY);

    mywalls.get(0).updateWall( super.getPointPrint(points.get(0)), super.getPointPrint(points.get(1)));
    mywalls.get(1).updateWall( super.getPointPrint(points.get(1)), super.getPointPrint(points.get(2)));
    mywalls.get(2).updateWall( super.getPointPrint(points.get(2)), super.getPointPrint(points.get(3)));
    mywalls.get(3).updateWall( super.getPointPrint(points.get(3)), super.getPointPrint(points.get(0)));
  }

  public ArrayList<Wall> getWalls() {return mywalls;}
}
