package myshapes;
import lights.*;
import java.awt.geom.Line2D;
import java.awt.Dimension;
import java.lang.Math;

public class Wall
{
  Dimension point1, point2;
  Line2D wall; //always for print

  public Wall (int x1, int y1, int x2, int y2)
  {
    this(new Dimension(x1, y1), new Dimension(x2, y2));
  }
  public Wall (Dimension p1, Dimension p2)
  {
    point1 = p1;
    point2 = p2;
    wall = new Line2D.Double(p1.getWidth(), p1.getHeight(), p2.getWidth(), p2.getHeight());
  }

  public void updateWall(int x1, int y1, int x2, int y2) { this.updateWall(new Dimension(x1,y1), new Dimension(x2,y2));}
  public void updateWall(Dimension p1, Dimension p2)
  {
    point1 = p1;
    point2 = p2;
    wall = new Line2D.Double(p1.getWidth(), p1.getHeight() ,p2.getWidth(), p2.getHeight());
  }
  public void updateWall (Wall wall)
  {
    this.point1 = wall.getP1();
    this.point2 = wall.getP2();
    this.wall = wall.getDrawing();
  }

  public Line2D getDrawing() {return wall;}
  public Dimension getP1 () {return point1;}
  public Dimension getP2 () {return point2;}
}
