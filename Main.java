import display.*;
import shapes.*;

public class Main
{
  public static void main (String []args)
  {
      Shape nju = new Shape();
      Disp okno = new Disp(nju);
      okno.repaint();
  }
}
