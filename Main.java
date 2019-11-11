import mydisplay.*;
import myshapes.*;

public class Main
{
  public static void main (String []args)
  {
      MyShape nju = new MyShape();
      Disp okno = new Disp(nju);
      okno.repaint();
  }
}
