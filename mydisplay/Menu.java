package mydisplay;
import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame
{
    JCheckBox designMode;
    public Menu()
    {
        this.setLocation(800,0);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        designMode = new JCheckBox("Design Mode:");
        this.add(designMode);

        this.setVisible(true);

    }
}
