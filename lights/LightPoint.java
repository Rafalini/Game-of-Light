package lights;

import mydisplay.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Dimension;

public class LightPoint
{
  int x, y;
  int dim;
  ArrayList<Ray> rays;

  public LightPoint()
  {
    this.x = 0;
    this.y = 0;
    this.dim = 10;
    rays = new ArrayList<Ray>();
  }

  public LightPoint(int rays_amout)
  {
    this();
    for(int i=0; i<rays_amout; i++)
      rays.add(new Ray (this.x, this.y, i*360/rays_amout));
  }

  public int getox () {return this.x ;}
  public int getoy () {return this.y ;}
  public int getdim() {return this.dim;}

  public void pressedW(Disp disp)
  {
    if(this.y < disp.getSize().getHeight() - 5)
      this.y += 5;
    rayUpdate();
  }
  public void pressedA(Disp disp)
  {
    if(this.y > -(disp.getSize().getWidth() - 5))
      this.x -= 5;
    rayUpdate();
  }
  public void pressedS(Disp disp)
  {
    if(this.y > -(disp.getSize().getHeight() - 5))
      this.y -= 5;
    rayUpdate();
  }
  public void pressedD(Disp disp)
  {
    if(this.y < disp.getSize().getWidth() - 5)
      this.x += 5;
    rayUpdate();
  }

  public ArrayList<Ray> getRays () {return rays;}

  private void rayUpdate()
  {
    for(int i=0; i<rays.size(); i++)
      rays.get(i).setPos( new Dimension(this.x, this.y));
  }
}
