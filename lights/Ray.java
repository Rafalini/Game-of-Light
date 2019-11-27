package lights;

import myshapes.*;
import java.lang.Math;
import java.awt.Dimension;
import java.awt.geom.Point2D;

public class Ray
{
  Dimension pos, posRay;
  Dimension hitpoint;
  double radians;

  public Ray(int x, int y, double angle)
  {
      hitpoint = new Dimension();
      pos = new Dimension (x,y);
      posRay = new Dimension();
      radians = Math.toRadians(angle);
  }
  public void setPos(Dimension dim) {this.pos = dim;}
  public Dimension getPos    () {return pos;}
  public Dimension getposRay () {return posRay;}
  public Dimension getHit    () {return hitpoint;}
  public void resetHit() {hitpoint = pos;}

  public void casting (Wall mywall)
  {
      double x1 = mywall.getP1().getWidth(), y1 = mywall.getP1().getHeight();
      double x2 = mywall.getP2().getWidth(), y2 = mywall.getP2().getHeight();
      double r = 20;
      double x3 = this.getPos().getWidth(), y3 = this.getPos().getHeight();
      double x4 = r * Math.cos(radians) + this.getPos().getWidth(), y4 = r * Math.sin(radians) + this.getPos().getHeight();

      posRay = new Dimension((int)x4, (int)y4);

      double den = (x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4);
      if (den == 0) return;

      double t = ((x1 -x3)*(y3 - y4) - (y1 - y3)*(x3 - x4))/den;
      double u = -((x1 - x2)*(y1 - y3) - (y1 - y2)*(x1 - x3))/den;

      Dimension point = new Dimension( (int)(x1 + t*(x2 - x1)), (int)(y1 + t*(y2 - y1 )) );

      if( 0 < t && t < 1 && u > 0)
      {
        if( Point2D.distance(hitpoint.getWidth(), hitpoint.getHeight(), pos.getWidth(), pos.getHeight()) == 0)
            hitpoint = point;
        else if( Point2D.distance(hitpoint.getWidth(), hitpoint.getHeight(), pos.getWidth(), pos.getHeight() ) >
                 Point2D.distance(point.getWidth(), point.getHeight(), pos.getWidth(), pos.getHeight()       )   )
                        hitpoint = point;
          //MyShape.print("kasting sukcesful");
          //MyShape.print("po X: " + pos.getWidth() + " Y: " + pos.getHeight());
          //MyShape.print("hp X: " + hitpoint.getWidth() + " Y: " + hitpoint.getHeight());
      }
  }
}
