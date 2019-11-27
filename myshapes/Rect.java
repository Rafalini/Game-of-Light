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

  public Rect (Disp disp, int x, int y)
  {
    super(disp,x,y,0);  //convertion to XoY with (0,0) in the middle of the screen
    mywalls = new ArrayList<Wall>();

    mywalls.add(new Wall(super.xCent, super.yCent, super.xCent, super.yCent));
    mywalls.add(new Wall(super.xCent, super.yCent, super.xCent, super.yCent));
    mywalls.add(new Wall(super.xCent, super.yCent, super.xCent, super.yCent));
    mywalls.add(new Wall(super.xCent, super.yCent, super.xCent, super.yCent));
  }

  public void sizeUpdate(int clickX, int clickY)
  {
    clickX = super.getXforXoY(clickX);
    clickY = super.getYforXoY(clickY);
    mywalls.get(0).updateWall( 2*super.xCent - clickX, 2*super.yCent - clickY,                 clickX, 2*super.yCent - clickY);
    mywalls.get(1).updateWall(                 clickX, 2*super.yCent - clickY,                 clickX,                 clickY);
    mywalls.get(2).updateWall(                 clickX,                 clickY, 2*super.xCent - clickX,                 clickY);
    mywalls.get(3).updateWall( 2*super.xCent - clickX,                 clickY, 2*super.xCent - clickX, 2*super.yCent - clickY);
  }

  public ArrayList<Wall> getWalls() {return mywalls;}
}
