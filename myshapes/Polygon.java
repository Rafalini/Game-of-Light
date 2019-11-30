package myshapes;

import mydisplay.*;
import java.util.*;
import java.lang.Math.*;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.geom.Point2D;

public class Polygon extends MyShape
{
  ArrayList<Wall> mywalls;

  public Polygon (Disp disp, int x, int y, int n)
  {
    super(disp,x,y,0);  //convertion to XoY with (0,0) in the middle of the screen
    mywalls = new ArrayList<Wall>();
    if(n<1) n=1;
    for(int i=0; i<n; i++)
      mywalls.add(new Wall(super.xCent, super.yCent, super.xCent, super.yCent));
  }

  public void sizeUpdate(int clickX, int clickY)
  {
    clickX = super.getXforXoY(clickX);
    clickY = super.getYforXoY(clickY);
    double r = Point2D.distance(xCent, yCent, clickX, clickY);
    for(int i=0; i<mywalls.size(); i++)
    {
      double radians = Math.toRadians(i * 360 / mywalls.size());
      double x1 = r * Math.cos(radians) + xCent;
      double y1 = r * Math.sin(radians) + yCent;
      radians = Math.toRadians((i+1) * 360/ mywalls.size());
      double x2 = r * Math.cos(radians) + xCent;
      double y2 = r * Math.sin(radians) + yCent;

      mywalls.get(i).updateWall((int)x1,(int)y1,(int)x2,(int)y2);
    }

    double radians = Math.toRadians(0);
    double x1 = r * Math.cos(radians) + xCent;
    double y1 = r * Math.sin(radians) + yCent;
    radians = Math.toRadians((mywalls.size()-1) * 360/ mywalls.size());
    double x2 = r * Math.cos(radians) + xCent;
    double y2 = r * Math.sin(radians) + yCent;

    mywalls.get(mywalls.size()-1).updateWall((int)x1,(int)y1,(int)x2,(int)y2);
  }

  public ArrayList<Wall> getWalls() {return mywalls;}
}
