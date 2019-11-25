package myshapes;
import lights.*;
import java.awt.geom.Line2D;
import java.awt.Dimension;

public class Wall
{
  Dimension point1, point2;
  Line2D.Double wall; //always for print

  public Wall (int x1, int y1, int x2, int y2)
  {
    point1 = new Dimension(x1, y1);
    point2 = new Dimension(x2, y2);
    //point1 = Dimension.setSize(x1,y1);
    //point2 = Dimension.setSize(x2,y2);
    wall = new Line2D.Double(x1,y1,x2,y2);
  }

  public Dimension casting (Wall mywall, Ray myray)
  {
      //double x1 = mywall, x2 = ;
      //double y1 = , y2 =
      //double x3, y3,
      //double den
      return new Dimension(0,0);
  }

  public void updateWall(Dimension p1, Dimension p2)
  {
    point1 = p1;
    point2 = p2;
    wall = new Line2D.Double(p1.getWidth(), p1.getHeight() ,p2.getWidth(), p2.getHeight());
  }
  public Line2D getDrawing() {return wall;}
}
