package lights;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LightPoint
{
  int ox, oy;
  int dim;

  public LightPoint()
  {
    ox = 0;
    oy = 0;
    dim = 10;
  }

  public int getox () {return ox ;}
  public int getoy () {return oy ;}
  public int getdim() {return dim;}

  public void pressedW() {oy += 5;}
  public void pressedA() {ox -= 5;}
  public void pressedS() {oy -= 5;}
  public void pressedD() {ox += 5;}
}
